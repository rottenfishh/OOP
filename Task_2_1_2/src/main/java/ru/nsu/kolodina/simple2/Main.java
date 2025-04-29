package ru.nsu.kolodina.simple2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server(4444, 2);
        ClientPool pool = new ClientPool();
        pool.runClients(2);
        int[] arr = new int[] {1,2,3, 4};
        boolean res = server.runServer(arr);
        System.out.println(res);
    }
}
