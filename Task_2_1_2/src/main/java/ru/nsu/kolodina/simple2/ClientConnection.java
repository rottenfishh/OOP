package ru.nsu.kolodina.simple2;

import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    int id;
    @Setter
    JSONArray arr;
    
    public ClientConnection(Socket clientSocket, int id) {
        this.id = id;
        this.clientSocket = clientSocket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}