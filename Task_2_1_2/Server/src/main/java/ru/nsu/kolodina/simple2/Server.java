package ru.nsu.kolodina.simple2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.json.JSONArray;

/**
 * server implementation.
 * is created with a set task, which it
 * distributes between clients
 */
public class Server {

    private final ServerSocket serverSocket;
    int numOfWorkers = 0;
    JSONArray JsonArray;
    Map<Integer, WorkerConnection> workerMap = new HashMap<>();
    Queue<WorkerConnection> activeWorkers = new LinkedList<>();
    Queue<JSONArray> activeTasks = new LinkedList<>();
    boolean res = false;
    int collectedResults = 0;
    int numberOfTasks = 0;

    /**
     * constructor.
     *
     * @param port to listen on
     */
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(30000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * split task array in parts based on id.
     *
     * @param id of client
     * @return chunk of array
     */
    private JSONArray createArray(int id) {
        JSONArray taskArr = new JSONArray();
        int start = id * JsonArray.length() / numberOfTasks;
        int end = (id + 1) * JsonArray.length() / numberOfTasks;
        for (int i = start; i < end; i++) {
            taskArr.put(JsonArray.get(i));
        }
        return taskArr;
    }

    /**
     * split task array.
     */
    private void splitArrIntoTasks() {
        numberOfTasks = numOfWorkers * 2;
        for (int i = 0; i < numberOfTasks; i++) {
            JSONArray taskArr = createArray(i);
            activeTasks.add(taskArr);
        }
        numberOfTasks = activeTasks.size();
    }

    /**
     * accept workers and establish connection.
     */
    private void acceptWorkers() {
        while (true) {
            Socket workerSocket = null;
            try {
                workerSocket = serverSocket.accept();
                workerSocket.setSoTimeout(10000);
            } catch (IOException e) {
                break;
            }
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader
                        (workerSocket.getInputStream()));
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

    /**
     * send data to all active workers.
     */
    private void sendData() {
        for (WorkerConnection worker : activeWorkers) {
            if (activeTasks.isEmpty()) {
                System.out.println("No data available");
                break;
            }
            JSONArray taskArr = activeTasks.remove();
            worker.out.println(taskArr.toString());
            worker.setCurrTask(taskArr);
        }
    }

    /**
     * collect results from all workers.
     *
     * @return result
     */
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
                input = worker.in.readLine();
                System.out.println("Got message " + input + " from worker " + id);
                switch (input) {
                    case null:
                        death = true;
                        break;
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
            } catch (IOException e) {
                death = true;
            }
            if (death) {
                System.out.println("Worker " + id + " died!");
                activeTasks.add(worker.currTask);
                numOfWorkers--;
                iterator.remove();
            }
        }
        return res;
    }

    /**
     * close connections with workers.
     */
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

    /**
     * close server.
     */
    private void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * main function to run server.
     *
     * @param numbersToCheck task array
     * @return result of checking
     */
    public boolean runServer(JSONArray numbersToCheck) {
        this.JsonArray = numbersToCheck;
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
        System.out.println("Time taken: " + (endTime - startTime) / 1000.0 + " seconds");
        closeWorkers();
        stop();
        return res;
    }
}
