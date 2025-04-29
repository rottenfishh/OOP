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
    List<ClientConnection> activeClients = new ArrayList<>();
    int[] arr;
    int numOfClients;
    List<JSONArray> taskArrs = new ArrayList<>();

    public Server(int port, int numOfClients) {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.numOfClients = numOfClients;
    }
    private JSONArray createArray(int id) {
        int start = id * arr.length / numOfClients;
        int end = (id + 1) * arr.length / numOfClients;
        int[] newArr = Arrays.copyOfRange(arr, start, end);
        System.out.println("newArr" + Arrays.toString(newArr));
        return JsonWriter.createJson(newArr);
    }

    private void splitArrIntoTasks(int[] arr) {
        for (int i = 0; i < numOfClients; i++) {
            JSONArray taskArr = createArray(i);
            taskArrs.add(taskArr);
        }
    }

    private void sendData() {
        int i = 0;
        for (ClientConnection c : activeClients) {
            if (i == arr.length) {
                break;
            }
            JSONArray taskArr = taskArrs.get(i);
            c.out.println(taskArr.toString());
            c.setArr(taskArrs.get(i));
            i++;
        }
    }

    private void acceptClients() throws IOException {
        for (int i = 0; i < numOfClients; i++) {
            Socket clientSocket = serverSocket.accept();
            ClientConnection clientConnection = new ClientConnection(clientSocket, i);
            activeClients.add(clientConnection);
        }
    }

    private boolean collectResults() {
        boolean res = false;
        String input;
        for (ClientConnection client : activeClients) {
            input = null;
            try {
                input  = client.in.readLine();
                if (input.equals("true")) {
                    res = true;
                }
            }
            catch (SocketTimeoutException e) {
                activeClients.remove(client);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input != null) {
                taskArrs.remove(client.arr);
            }
        }
        return res;
    }

    private void closeClients() {
        for (ClientConnection client : activeClients) {
            try {
                client.clientSocket.close();
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
        splitArrIntoTasks(arr);
        acceptClients();
        while (!taskArrs.isEmpty() && !activeClients.isEmpty()) {
            sendData();
            res = collectResults();
        }
        closeClients();
        stop();
        return res;
    }

}
