package ru.nsu.kolodina.simple2;

// Demonstrating Server-side Programming
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.*;

import static java.lang.Thread.sleep;

public class Server {
    private final ServerSocket serverSocket;
    int numOfWorkers = 0;
    List<JSONArray> tasks = new ArrayList<>();
    JSONArray jArray;
    Map<Integer, WorkerConnection> workerMap = new HashMap<>();
    Queue<WorkerConnection> activeWorkers = new LinkedList<>();
    Queue<JSONArray> activeTasks = new LinkedList<>();
    boolean res = false;
    int collectedResults = 0;
    int numberOfTasks = 0;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(30000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONArray createArray(int id) {
        JSONArray taskArr = new JSONArray();
        int start = id * jArray.length() / numOfWorkers;
        int end = (id + 1) * jArray.length() / numOfWorkers;
        for (int i = start; i < end; i++) {
            taskArr.put(jArray.get(i));
        }
        return taskArr;
    }

    private void splitArrIntoTasks() {
        for (int i = 0; i < numOfWorkers; i++) {
            JSONArray taskArr = createArray(i);
            activeTasks.add(taskArr);
        }
        numberOfTasks = activeTasks.size();
    }

    private void acceptWorkers() throws IOException {
        while (true){
            Socket workerSocket = null;
            try {
                workerSocket = serverSocket.accept();
            }
            catch (SocketTimeoutException e) {
                break;
            }
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()));
                PrintWriter out = new PrintWriter(workerSocket.getOutputStream(), true);
                int id = Integer.parseInt(in.readLine());
                System.out.println("Connected worker with id: " + id);
                WorkerConnection workerConnection = new WorkerConnection(workerSocket, id, in, out);
                if (workerMap.containsKey(id)) {
                    System.out.println("Reconnecting.." + id);
                    activeWorkers.remove(workerMap.get(id));
                    activeWorkers.add(workerConnection);
                    workerMap.get(id).workerSocket.close();
                    workerMap.replace(id, workerConnection);
                } else {
                    workerMap.putIfAbsent(id, workerConnection);
                    activeWorkers.add(workerConnection);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error while accepting workers");
                break;
            }
        }
        numOfWorkers = activeWorkers.size();
    }

    private void sendData() {
        for (WorkerConnection worker: activeWorkers) {
            if (activeTasks.isEmpty()) {
                System.out.println("No data available");
                break;
            }
            JSONArray taskArr = activeTasks.remove();
            worker.out.println(taskArr.toString());
            worker.setCurrTask(taskArr);
        }
    }

    private boolean collectResults() {
        boolean death = false;
        String input;
        Iterator<WorkerConnection> iterator = activeWorkers.iterator();
        while (iterator.hasNext()) {
            death = false;
            WorkerConnection worker = iterator.next();
            int id = worker.id;
            input = null;
            try {
                input  = worker.in.readLine();
                System.out.println("Got message " + input + " from worker " + id);
                switch (input) {
                    case null : death = true; break;
                    case "true":
                        res = true;
                        collectedResults++;
                        break;
                    case "false":
                        collectedResults++;
                        break;
                    default:
                        continue;
                }
            }
            catch (IOException e) {
                death = true;
            }
            if (death) {
                System.out.println("Worker "+ id + " died!");
                activeTasks.add(worker.currTask);
                numOfWorkers--;
                iterator.remove();
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

    public boolean runServer(JSONArray numbersToCheck) throws IOException {
        this.jArray = numbersToCheck;
        acceptWorkers();
        splitArrIntoTasks();
        long startTime = System.currentTimeMillis();
        while ((collectedResults < numberOfTasks) && !activeWorkers.isEmpty()) {
            if (!activeTasks.isEmpty()) {
                sendData();
            }
            if (collectResults()) {
                res = true;
            }
            acceptWorkers();
        }
        if (activeWorkers.isEmpty()) {
            System.out.println("EVERYONE DIED");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime)/1000 + " seconds");
        closeWorkers();
        stop();
        return res;
    }
}
