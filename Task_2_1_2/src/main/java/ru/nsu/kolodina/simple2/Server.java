package ru.nsu.kolodina.simple2;

// Demonstrating Server-side Programming
import lombok.Setter;
import org.json.JSONArray;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    List<ClientConnection> clients = new ArrayList<>();
    int[] arr;
    int numOfClients;

    public Server(int port, int numOfClients) {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.numOfClients = numOfClients;
    }

    private JSONArray sendData(ClientConnection client) {
        System.out.println("len" + arr.length);
        int start = client.id * arr.length / numOfClients;
        int end = (client.id + 1) * arr.length / numOfClients;
        int[] newArr = Arrays.copyOfRange(arr, start, end);
        System.out.println("newArr" + Arrays.toString(newArr));
        JSONArray ja = JsonWriter.createJson(newArr);
        client.out.println(ja.toString());
        System.out.println(ja.toString());
        return ja;
    }

    private void acceptClients() throws IOException {
        for (int i = 0; i < numOfClients; i++) {
            Socket clientSocket = serverSocket.accept();
            ClientConnection clientConnection = new ClientConnection(clientSocket, i);
            clients.add(clientConnection);
            JSONArray ar = sendData(clientConnection);
            clientConnection.setArr(ar);
        }

    }
    private boolean collectResults() {
        String res = "false";
        for (ClientConnection client : clients) {
            try {
                res = client.in.readLine();
                client.out.println("ok");
                if (res.equals("true")) {
                    for (ClientConnection c : clients) {
                        c.out.println("ok");
                    }
                    return true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private void stop() throws IOException {
        serverSocket.close();
    }

    public boolean runServer(int[] numbersToCheck) throws IOException {
        this.arr = numbersToCheck;
        acceptClients();
        boolean res = collectResults();
        stop();
        return res;
    }

}
