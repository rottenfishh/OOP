package ru.nsu.kolodina.pizza;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable{
    Order order;
    public Client(int id) {
        order = new Order(id);
    }
    public void run() {

    }
}
