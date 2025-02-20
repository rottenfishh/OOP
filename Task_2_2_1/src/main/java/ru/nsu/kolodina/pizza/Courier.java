package ru.nsu.kolodina.pizza;

import java.util.List;

import static java.lang.Thread.sleep;

public class Courier implements Runnable{
    int id;
    int backpackCap;
    int fullSlots;
    Storage storage;
    public Courier(Storage storage, int id, int backpackCap) {
        this.storage = storage;
        this.id = id;
        this.backpackCap = backpackCap;
    }
    public void run(){
        while(!Pizzeria.workDayEnded || storage.takenSlots.availablePermits() != 0) {
            List<Order> orders;
            try {
                orders = storage.getFromStorage(backpackCap);
                for (Order order : orders) {
                    order.status = "DELIVERING";
                    order.printStatus();
                    sleep(1000);
                    order.status = "DELIVERED";
                    order.printStatus();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
