package ru.nsu.kolodina.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Storage {
    List<Order> storage;
    int capacity;
    Semaphore takenSlots;
    Semaphore emptySlots;
    public Storage(int capacity) {
        this.capacity = capacity;
        storage = new ArrayList<>(capacity);
        emptySlots = new Semaphore(capacity);
        takenSlots = new Semaphore(0);
    }
    public void putInStorage(Order order) {
        try {
            emptySlots.acquire();
            synchronized (storage) {
                storage.add(order);
            }
            takenSlots.release();
        } catch (InterruptedException e){
            throw(new RuntimeException());
        }
    }
    public List<Order> getFromStorage(int capacity) {
        List<Order> orders = new ArrayList<>();
        int num;
        try {
        if ((num = takenSlots.availablePermits()) < capacity) {
            if (num != 0) {
                    takenSlots.acquire(num);
            }
            else {
                takenSlots.acquire();
                num = 1;
            }
        } else {
            takenSlots.acquire(capacity);
            num = capacity;
        }
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        System.out.println("he");
        System.out.println(num + " num");
        synchronized(storage) {
            for (int i = 0; i < num; i ++) {
                orders.add(storage.removeFirst());
                System.out.println("sooo");
            }
        }
        emptySlots.release(num);
        return orders;
    }
}
