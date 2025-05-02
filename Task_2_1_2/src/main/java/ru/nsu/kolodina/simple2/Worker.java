package ru.nsu.kolodina.simple2;

import org.json.JSONArray;

import java.io.*;
import java.net.*;

public class Worker implements Runnable {
    private final Socket workerSocket;
    private PrintWriter out;
    private BufferedReader in;
    SimpleNumbers numbers = new SimpleNumbers();
    private final int id;
    boolean connectionClosed = false;

    public Worker(String ip, int port, int id) {
        try {
            this.workerSocket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.id = id;
    }

    public void startConnection(){
        try {
            out = new PrintWriter(workerSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.println(id);
    }

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
    public boolean calculate(JSONArray arr) {
        for (int i = 0; i < arr.length(); i++) {
            int num = arr.getInt(i);
            if (!numbers.isSimple(num)) { // если есть непростое = true
                return true;
            }
        }
        return false;
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        workerSocket.close();
        connectionClosed = true;
    }

    @Override
    public void run() {
        startConnection();
        do {
            JSONArray arr = getData();
            if (arr == null) {
                break;
            }
            boolean res = calculate(arr);
            try {
                sendMessage(String.valueOf(res));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!connectionClosed);
    }
}
