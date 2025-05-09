package ru.nsu.kolodina.simple2;

public class WorkerPool {
    public Worker createWorker(String ip, int port, int id){
        Worker worker = new Worker(ip, port, id);
        Thread newWorker = new Thread(worker);
        newWorker.start();
        return worker;
    }

    public void runWorkers(int numOfClients) {
        for (int i = 0; i < numOfClients; i++) {
            createWorker("127.0.0.1", 4444, i);
        }
    }
}
