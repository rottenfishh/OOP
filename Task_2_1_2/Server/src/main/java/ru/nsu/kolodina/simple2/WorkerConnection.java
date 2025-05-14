package ru.nsu.kolodina.simple2;

import lombok.Setter;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerConnection {
    Socket workerSocket;
    BufferedReader in;
    PrintWriter out;
    @Setter
    int id;
    @Setter
    JSONArray currTask;

    public WorkerConnection(Socket workerSocket, int id, BufferedReader in, PrintWriter out) {
        this.id = id;
        this.workerSocket = workerSocket;
        this.in = in;
        this.out = out;
    }

}