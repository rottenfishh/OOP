package ru.nsu.kolodina.pizza;

import java.util.List;

import static java.lang.Thread.sleep;

public class Courier implements Runnable{
    int id;
    int backpackCap;
    int fullSlots;
    Storage storage;
    volatile boolean workDayEnded;
    public Courier(Storage storage, int id, int backpackCap, boolean workDayEnded) {
        this.storage = storage;
        this.id = id;
        this.backpackCap = backpackCap;
        this.workDayEnded = workDayEnded;
    }
    public void run(){
        while(!workDayEnded || storage.takenSlots.availablePermits() != 0) {
            List<Order> orders;
            try {
                orders = storage.getFromStorage(backpackCap);
                if (orders == null) {
                    return;
                }
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
