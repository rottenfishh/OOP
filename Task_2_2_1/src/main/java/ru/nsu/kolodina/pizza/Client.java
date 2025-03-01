package ru.nsu.kolodina.pizza;

/**
 * class of client.
 */
public class Client implements Runnable {
    Order order;
    Storage queue;

    /**
     * doesnt need much parameters.
     *
     * @param queue of orders
     * @param id of client
     */
    public Client(Storage queue, int id) {
        this.queue = queue;
        order = new Order(id);
    }

    @Override
    public void run() {
        queue.putInStorage(order);
        order.printStatus();
    }
}
