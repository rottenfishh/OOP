package ru.nsu.kolodina.pizza;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable{
    Order order;
    Storage queue;
    public Client(Storage queue, int id) {
        this.queue = queue;
        order = new Order(id);
    }
    public void run() {
        queue.putInStorage(order);
    }
}
