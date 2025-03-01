package ru.nsu.kolodina.pizza;

import static java.lang.Thread.sleep;

public class Baker implements Runnable {
    int id;
    int speed;
    Storage storage;
    Storage orders;

    public Baker(Storage storage, Storage orders, int id, int speed) {
        this.storage = storage;
        this.orders = orders;
        this.id = id;
        this.speed = speed;
    }

    @Override
    public void run() {
        while (!Pizzeria.workDayEnded || !orders.isEmpty()) {
            boolean haveOrder = false;
            Order toBake = orders.getFromStorage(1).getFirst();
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
        order.status = "BAKING";
        order.printStatus();
        sleep(1000 / speed);
        order.status = "READY";
        order.printStatus();
        storage.putInStorage(order);
    }
}
