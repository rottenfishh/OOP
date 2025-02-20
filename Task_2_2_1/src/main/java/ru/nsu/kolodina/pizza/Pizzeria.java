package ru.nsu.kolodina.pizza;

public class Pizzeria {
    public int startWorkTime = 0;
    public int currTime;
    public int endWorkTime = 10;
    int storageCapacity = 100;
    int numBakers = 3;
    int numCuriers = 3;
    Storage storage;
    Storage orders;
    Thread[] bakers;
    Thread[] couriers;
    static volatile boolean workDayEnded = false;
    public Pizzeria() {
        orders = new Storage(1000);
        currTime = startWorkTime;
        storage = new Storage(storageCapacity);
        bakers = new Thread[numBakers];
        for (int i = 0; i < numBakers; i++) {
            bakers[i] = new Thread(new Baker(storage, orders, 0, 10));
        }
        couriers = new Thread[numCuriers];
        for (int i = 0; i < numCuriers; i++) {
            couriers[i] = new Thread(new Courier(storage, 0, 10));
        }
    }
    public void startWork() throws InterruptedException {
        System.out.println("Work day started!");
        for (Thread i: bakers) {
            i.start();
        }
        for (Thread j: couriers) {
            j.start();
        }
        for (int i = startWorkTime; i < endWorkTime; i++) {
            Thread newClient = new Thread(new Client(orders, i));
            newClient.start();
        }
    }
    public void endWork() throws InterruptedException {
        workDayEnded = true;
        for (Thread i: bakers) {
            i.join();
        }
        for (Thread j: couriers) {
            j.join();
        }
        System.out.println("Work day ended!");
    }
}
