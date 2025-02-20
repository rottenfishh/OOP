package ru.nsu.kolodina.pizza;

import java.util.ArrayList;
import java.util.List;

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
    Thread[] curiers;
    static volatile boolean workDayEnded = false;
    public Pizzeria() {
        orders = new Storage(10000, workDayEnded);
        currTime = startWorkTime;
        storage = new Storage(storageCapacity, workDayEnded);
        bakers = new Thread[numBakers];
        for (int i = 0; i < numBakers; i++) {
            bakers[i] = new Thread(new Baker(storage, orders, 0, 10, workDayEnded));
        }
        curiers = new Thread[numCuriers];
        for (int i = 0; i < numCuriers; i++) {
            curiers[i] = new Thread(new Curier(storage, 0, 10, workDayEnded));
        }
        for (Thread i: bakers) {
            i.start();
        }
        for (Thread j: curiers) {
            j.start();
        }
    }
    public void startWork() {
        for (int i = 0; i < 10; i++) {
            Order ord = new Order(i);
            orders.putInStorage(ord);
        }
    }
    public void endWork() throws InterruptedException {
        workDayEnded = true;
        orders.workDayEnded = true;
        storage.workDayEnded = true;
        for (Thread i: bakers) {
            i.join();
        }
        for (Thread j: curiers) {
            j.join();
        }
    }
}
