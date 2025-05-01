package ru.nsu.kolodina.simple2;

// Demonstrating Server-side Programming
import org.json.JSONArray;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    int[] arr;
    int numOfWorkers;
    List<JSONArray> taskArrs = new ArrayList<>();
    Map<Integer, WorkerConnection> workerMap = new HashMap<>();

    public Server(int port, int numOfworkers) {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.numOfWorkers = numOfworkers;
    }

    private JSONArray createArray(int id) {
        int start = id * arr.length / numOfWorkers;
        int end = (id + 1) * arr.length / numOfWorkers;
        int[] newArr = Arrays.copyOfRange(arr, start, end);
        return JsonWriter.createJson(newArr);
    }

    private void splitArrIntoTasks() {
        for (int i = 0; i < numOfWorkers; i++) {
            JSONArray taskArr = createArray(i);
            taskArrs.add(taskArr);
        }
    }

    private void acceptWorkers() throws IOException {
        for (int i = 0; i < numOfWorkers; i++) {
            Socket workerSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()));
            PrintWriter out = new PrintWriter(workerSocket.getOutputStream(), true);
            int id = Integer.parseInt(in.readLine());
            WorkerConnection workerConnection = new WorkerConnection(workerSocket, id, in, out);
            if (workerMap.containsKey(id)) {
                System.out.println("Reconnecting.." + id);
                workerMap.get(id).workerSocket.close();
            }
            workerMap.put(id, workerConnection);
        }
    }

    private void sendData() {
        int i = 0;
        for (var entry: workerMap.entrySet()) {
            if (i == arr.length) {
                break;
            }
            WorkerConnection worker = entry.getValue();
            JSONArray taskArr = taskArrs.get(i);
            worker.out.println(taskArr.toString());
            worker.setArr(taskArr);
            i++;
        }
    }

    private boolean collectResults() {
        boolean res = false;
        String input;
        for (var entry: workerMap.entrySet()) {
            WorkerConnection worker = entry.getValue();
            int id = entry.getKey();
            input = null;
            try {
                input  = worker.in.readLine();
                if (input.equals("true")) {
                    res = true;
                }
            }
            catch (SocketTimeoutException e) {
                System.out.println("worker died " + id);
                workerMap.remove(id);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input != null) {
                taskArrs.remove(worker.arr);
            }
        }
        return res;
    }

    private void closeWorkers() {
        for (var entry : workerMap.entrySet()) {
            WorkerConnection worker = entry.getValue();
            try {
                worker.workerSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void stop() throws IOException {
        serverSocket.close();
    }

    public boolean runServer(int[] numbersToCheck) throws IOException {
        this.arr = numbersToCheck;
        boolean res = false;
        splitArrIntoTasks();
        acceptWorkers();
        while (!taskArrs.isEmpty() && !workerMap.isEmpty()) {
            sendData();
            res = collectResults();
        }
        if (workerMap.isEmpty()) {
            System.out.println("EVERYONE DIED");
        }
        closeWorkers();
        stop();
        return res;
    }

}
