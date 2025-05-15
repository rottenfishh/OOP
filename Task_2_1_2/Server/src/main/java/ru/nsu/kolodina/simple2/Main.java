package ru.nsu.kolodina.simple2;

import org.json.JSONArray;

/**
 * run the server.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server(4444);
        JSONArray arr = JsonReader.read(args[0]);
        boolean res = server.runServer(arr);
        System.out.println("Result = " + res);
    }
}