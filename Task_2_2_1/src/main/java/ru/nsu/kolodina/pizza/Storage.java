package ru.nsu.kolodina.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Storage {
    List<Order> storage;
    int capacity;
    Semaphore takenSlots;
    Semaphore emptySlots;
    volatile boolean workDayEnded;
    public Storage(int capacity, boolean workDayEnded) {
        this.capacity = capacity;
        storage = new ArrayList<>(capacity);
        emptySlots = new Semaphore(capacity);
        takenSlots = new Semaphore(0);
        this.workDayEnded = workDayEnded;
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
                if (workDayEnded) { //finishing
                    return null;
                }
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
        synchronized(storage) {
            for (int i = 0; i < num; i ++) {
                orders.add(storage.removeFirst());
            }
        }
        emptySlots.release(num);
        return orders;
    }
}
