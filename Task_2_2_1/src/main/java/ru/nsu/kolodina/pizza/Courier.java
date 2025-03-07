package ru.nsu.kolodina.pizza;

import static java.lang.Thread.sleep;

import java.util.List;

/**
 * class implementing courier work.
 */
public class Courier implements Runnable {
    int id;
    int backpackCap;
    Storage storage;

    /**
     * initialize courier with everything he needs.
     *
     * @param storage courier gets orders
     * @param id of courier
     * @param backpackCap capacity of backpack
     */
    public Courier(Storage storage, int id, int backpackCap) {
        this.storage = storage;
        this.id = id;
        this.backpackCap = backpackCap;
    }

    /**
     * run the thread. make courier work
     */
    @Override
    public void run() {
        while (!Pizzeria.workDayEnded || !storage.isEmpty()) {
            List<Order> orders;
            try {
                orders = storage.getFromStorage(backpackCap);
                for (Order order : orders) {
                    order.status = Order.Status.DELIVERING;
                    order.printStatus();
                    sleep(1000);
                    order.status = Order.Status.DELIVERED;
                    order.printStatus();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
