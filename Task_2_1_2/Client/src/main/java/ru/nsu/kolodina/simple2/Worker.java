package ru.nsu.kolodina.simple2;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
     * calculate the result on given array
     *
     * @param arr task
     * @return result
     */
    public boolean calculate(JSONArray arr) {
        for (int i = 0; i < arr.length(); i++) {
            int num = arr.getInt(i);
            if (!numbers.isSimple(num)) { // если есть непростое = true
                return true;
            }
            if (i % 1000 == 0) {
                out.println("STILL ALIVE");
                System.out.println("ys");
            }
        }
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
            System.out.println("got data: " + arr);
            if (arr == null) {
                break;
            }
            boolean res = calculate(arr);
            System.out.println("calculated: " + res);
            out.println(res);
        } while (!connectionClosed);
    }
}
