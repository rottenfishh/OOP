package ru.nsu.kolodina.simple2;

import java.io.IOException;

public class ClientPool {

    public void runClients(int numOfClients) {
        for (int i = 0; i < numOfClients; i++) {
            createClient("127.0.0.1", 4444, i);
        }
    }
    private void createClient(String ip, int port, int id){
        Thread newClient = new Thread(new Client(ip, port, id));
        newClient.start();
    }
}
