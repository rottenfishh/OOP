package ru.nsu.kolodina.pizza;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final int capacity;
    private final List<Order> storage;

    public Storage(int capacity) {
        this.capacity = capacity;
        storage = new ArrayList<>(capacity);
    }

    public synchronized void putInStorage(Order order) {
        try {
            while (storage.size() == capacity) {
                wait();
            }
        } catch (InterruptedException e) {
            throw (new RuntimeException());
        }
        storage.add(order);
        notifyAll();
    }

    public synchronized List<Order> getFromStorage(int count) {
        List<Order> orders = new ArrayList<>();
        int num;
        try {
            while (storage.isEmpty()) {
                wait();
            }
            if (storage.size() >= count) {
                num = count;
            } else {
                num = storage.size();
            }
        } catch (InterruptedException e) {
            throw (new RuntimeException());
        }
        for (int i = 0; i < num; i++) {
            orders.add(storage.removeFirst());
        }
        notifyAll();
        return orders;
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }
}
