package ru.nsu.kolodina.pizza;

import java.util.List;

import static java.lang.Thread.sleep;

public class Baker implements Runnable{
    int id;
    int speed;
    Storage storage;
    Storage orders;
    volatile boolean workDayEnded;
    public Baker(Storage storage, Storage orders, int id, int speed, boolean workDayEnded) {
        this.storage = storage;
        this.orders = orders;
        this.id = id;
        this.speed = speed;
        this.workDayEnded = workDayEnded;
    }
    @Override
    public void run(){
        while(!workDayEnded || orders.takenSlots.availablePermits() != 0) {
            boolean haveOrder = false;
            List<Order> currOrder = orders.getFromStorage(1);
            if (currOrder == null) {
                return;
            }
            Order toBake = currOrder.getFirst();
            if (toBake.status.equals("ORDERED")) {
                toBake.status = "TAKEN";
                toBake.printStatus();
                haveOrder = true;
            }
            if (haveOrder) {
                try {
                    bake(toBake);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void bake(Order order) throws InterruptedException {
        sleep(1000/speed);
        order.status = "READY";
        order.printStatus();
        storage.putInStorage(order);
    }
}
