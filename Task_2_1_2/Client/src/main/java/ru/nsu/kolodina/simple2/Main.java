package ru.nsu.kolodina.simple2;

/**
 * run worker.
 */
public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker("127.0.0.1", 4444, Integer.parseInt(args[0]));
        worker.run();
    }
}