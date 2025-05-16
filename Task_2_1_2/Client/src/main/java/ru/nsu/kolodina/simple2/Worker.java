package ru.nsu.kolodina.simple2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONArray;

/**
 * worker class logic.
 */
public class Worker {

    private final int id;
    SimpleNumbers numbers = new SimpleNumbers();
    boolean connectionClosed = false;
    int port;
    String host;
    private Socket workerSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * constructor.
     *
     * @param ip   to connect to
     * @param port to connect to
     * @param id   of worker
     */
    public Worker(String ip, int port, int id) {
        this.host = ip;
        this.port = port;
        this.id = id;
    }

    /**
     * connecting to server for a given number of tries.
     *
     * @param retries number
     * @return created socket of connection
     */
    public Socket connectToServer(int retries) {
        for (int i = 0; i < retries; i++) {
            System.out.println("Trying to connect to " + host + ":" + port);
            try {
                return new Socket(this.host, this.port);
            } catch (IOException e) {
                System.out.println("Error in connecting to " + this.host + ":" + this.port);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    System.out.println("bububububu");
                }
            }
        }
        return null;
    }

    /**
     * start connecting to server.
     */
    public void startConnection() {
        int retries = 10;
        this.workerSocket = connectToServer(retries);
        if (workerSocket == null) {
            return;
        }
        try {
            out = new PrintWriter(workerSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected to " + this.host + ":" + this.port);
        out.println(id);
    }

    /**
     * get data from server.
     *
     * @return json with task array
     */
    public JSONArray getData() {
        JSONArray numbersArr = null;
        try {
            String line = in.readLine();
            if (line == null) {
                stopConnection();
                return null;
            }
            numbersArr = new JSONArray(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return numbersArr;
    }

    /**
     * calculate the result on given array.
     *
     * @param arr task
     * @return result
     */
    public boolean calculate(JSONArray arr) {
        long startTime = System.currentTimeMillis();
        long prevTime = startTime;
        for (int i = 0; i < arr.length(); i++) {
            int num = arr.getInt(i);
            if (!numbers.isSimple(num)) { // если есть непростое = true
                long endTime = System.currentTimeMillis();
                System.out.println("Worker" + id + " Time taken: " + (endTime - startTime) / 1000.0);
                return true;
            }
            long time = System.currentTimeMillis();
            if (time - prevTime > 10000) {
                System.out.println("calculating");
                out.println("STILL ALIVE");
                out.flush();
                prevTime = time;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Worker" + id + " Time taken: " + (endTime - startTime) / 1000.0);
        return false;
    }

    /**
     * stop connection with server.
     *
     * @throws IOException sadly
     */
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        workerSocket.close();
        connectionClosed = true;
    }

    /**
     * run the worker.
     */
    public void run() {
        startConnection();
        System.out.println("Connection established");
        do {
            JSONArray arr = getData();
            if (arr == null) {
                break;
            }
            System.out.println(arr.length() + " numbers received");
            boolean res = calculate(arr);
            System.out.println("calculated: " + res);
            out.println(res);
        } while (!connectionClosed);
        System.out.println("Worker stopped");
    }
}
