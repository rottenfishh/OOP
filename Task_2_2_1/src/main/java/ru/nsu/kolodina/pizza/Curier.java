package ru.nsu.kolodina.pizza;

import java.util.List;

import static java.lang.Thread.sleep;

public class Curier implements Runnable{
    int id;
    int backpackCap;
    int fullSlots;
    Storage storage;
    volatile boolean workDayEnded;
    public Curier(Storage storage, int id, int backpackCap, boolean workDayEnded) {
        this.storage = storage;
        this.id = id;
        this.backpackCap = backpackCap;
        this.workDayEnded = workDayEnded;
    }
    public void run(){
        while(!workDayEnded || storage.takenSlots.availablePermits() != 0) {
            List<Order> orders = null;
            try {
                System.out.println("trying to take");
                orders = storage.getFromStorage(backpackCap);
                System.out.println("takenn");
                for (Order order : orders) {
                    System.out.println("ord");
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
