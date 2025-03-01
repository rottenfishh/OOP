package ru.nsu.kolodina.pizza;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.Map;

public class Pizzeria {
    static volatile boolean workDayEnded = false;
    public int startWorkTime;
    public int endWorkTime;
    int storageCapacity;
    int numBakers;
    int numCouriers;
    Storage storage;
    Storage orders;
    Thread[] bakers;
    Thread[] couriers;

    public Pizzeria() {
        JsonReader reader = new JsonReader();
        reader.read();
        orders = new Storage(storageCapacity);
        storage = new Storage(storageCapacity);
        bakers = new Thread[numBakers];
        for (int i = 0; i < numBakers; i++) {
            bakers[i] = new Thread(new Baker(storage, orders, i, (int) reader.bakers.get(Integer.toString(i))));
        }
        couriers = new Thread[numCouriers];
        for (int i = 0; i < numCouriers; i++) {
            couriers[i] = new Thread(new Courier(storage, i, (int) reader.couriers.get(Integer.toString(i))));
        }
    }

    public void startWork() {
        System.out.println("Work day started!");
        for (Thread i : bakers) {
            i.start();
        }
        for (Thread j : couriers) {
            j.start();
        }
        for (int i = startWorkTime; i < endWorkTime; i++) {
            Thread newClient = new Thread(new Client(orders, i));
            newClient.start();
        }
    }

    public void endWork() throws InterruptedException {
        workDayEnded = true;
        for (Thread i : bakers) {
            i.join();
        }
        for (Thread j : couriers) {
            j.join();
        }
        System.out.println("Work day ended!");
    }

    public class JsonReader {
        Map<String, Object> bakers;
        Map<String, Object> couriers;

        public void read() {
            String resourceName = "JSON.json";
            InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
            if (is == null) {
                throw new NullPointerException("Cannot find resource file " + resourceName);
            }
            JSONTokener tokener = new JSONTokener(is);
            JSONObject object = new JSONObject(tokener);
            startWorkTime = object.getInt("start time");
            endWorkTime = object.getInt("end time");
            storageCapacity = object.getInt("storage capacity");
            numBakers = object.getInt("number of bakers");
            JSONObject bakersJson = object.getJSONObject("speed of bakers");
            bakers = bakersJson.toMap();
            numCouriers = object.getInt("number of couriers");
            JSONObject couriersJson = object.getJSONObject("capacity of couriers");
            couriers = couriersJson.toMap();
        }
    }
}
