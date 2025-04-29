package ru.nsu.kolodina.simple2;

// Demonstrating Client-side Programming
import org.json.JSONArray;

import java.io.*;
import java.net.*;

public class Client implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    SimpleNumbers numbers = new SimpleNumbers();
    //private final int[] numbersArray;
    private final int id;
    boolean connectionClosed = false;

    public Client(String ip, int port, int id) {
        try {
            this.clientSocket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.id = id;
    }

    public void startConnection(){
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public boolean calculate() {
        JSONArray arr = getData();
        if (arr == null) {
            return false;
        }
        System.out.println("oh" + arr.toString());
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
        clientSocket.close();
        connectionClosed = true;
    }

    @Override
    public void run() {
        startConnection();
        do {
            boolean res = calculate();
            try {
                sendMessage(String.valueOf(res));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!connectionClosed);
    }
}
