package ru.nsu.kolodina.simple2;

import lombok.Setter;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * worker connection.
 */
public class WorkerConnection {
    Socket workerSocket;
    BufferedReader in;
    PrintWriter out;
    @Setter
    int id;
    @Setter
    JSONArray currTask;

    /**
     * constructor.
     *
     * @param workerSocket created for worker
     * @param id its id
     * @param in its input part of socket
     * @param out output part of socket
     */
    public WorkerConnection(Socket workerSocket, int id, BufferedReader in, PrintWriter out) {
        this.id = id;
        this.workerSocket = workerSocket;
        this.in = in;
        this.out = out;
    }

}