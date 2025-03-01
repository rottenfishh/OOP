package ru.nsu.kolodina.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * the storage.
 */
public class Storage {
    private final int capacity;
    private final List<Order> storage;

    /**
     * init with a given capacity.
     *
     * @param capacity yes
     */
    public Storage(int capacity) {
        this.capacity = capacity;
        storage = new ArrayList<>(capacity);
    }

    /**
     * put order in buffer.
     *
     * @param order to put
     */
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

    /**
     * get orders from buffer.
     *
     * @param count num of orders
     * @return the list of orders
     */
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

    /**
     * check if buffer is empty.
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return storage.isEmpty();
    }
}
