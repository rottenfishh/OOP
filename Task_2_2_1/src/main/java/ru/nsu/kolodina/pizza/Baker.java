package ru.nsu.kolodina.pizza;

import static java.lang.Thread.sleep;

/**
 * class implementing baker.
 */
public class Baker implements Runnable {
    int id;
    int speed;
    Storage storage;
    Storage orders;

    /**
     * init everything baker needs.
     *
     * @param storage to put orders in
     * @param orders queue of orders
     * @param id of baker
     * @param speed of baker
     */
    public Baker(Storage storage, Storage orders, int id, int speed) {
        this.storage = storage;
        this.orders = orders;
        this.id = id;
        this.speed = speed;
    }

    /**
     * run our thread.
     */
    @Override
    public void run() {
        while (!Pizzeria.workDayEnded || !orders.isEmpty()) {
            boolean haveOrder = false;
            Order toBake = orders.getFromStorage(1).getFirst();
            if (toBake.status.equals(Order.Status.ORDERED)) {
                toBake.status = Order.Status.TAKEN;
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

    /**
     * bake the order.
     *
     * @param order order
     * @throws InterruptedException happens
     */
    public void bake(Order order) throws InterruptedException {
        order.status = Order.Status.BAKING;
        order.printStatus();
        sleep(1000 / speed);
        order.status = Order.Status.READY;
        order.printStatus();
        storage.putInStorage(order);
    }
}
