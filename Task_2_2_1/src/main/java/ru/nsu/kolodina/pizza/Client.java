package ru.nsu.kolodina.pizza;

public class Client implements Runnable {
    Order order;
    Storage queue;

    public Client(Storage queue, int id) {
        this.queue = queue;
        order = new Order(id);
    }

    public void run() {
        queue.putInStorage(order);
        order.printStatus();
    }
}
