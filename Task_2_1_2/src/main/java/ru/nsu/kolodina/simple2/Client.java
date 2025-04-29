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
            numbersArr = new JSONArray(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return numbersArr;
    }
    public boolean calculate() {
        JSONArray arr = getData();
        System.out.println(arr.toString());
        for (int i = 0; i < arr.length(); i++) {
            int num = arr.getInt(i);
            if (!numbers.isSimple(num)) { // если есть непростое = true
                return true;
            }
        }
        return false;
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    @Override
    public void run() {
        startConnection();
        boolean res = calculate();
        try {
            String resp = sendMessage(String.valueOf(res));
            if (resp != null) {
                stopConnection();
            }
        } catch (IOException e) {
            throw  new RuntimeException();
        }
    }
}
