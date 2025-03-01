package ru.nsu.kolodina.pizza;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Pizzeria dominos = new Pizzeria();
        dominos.startWork();
        dominos.endWork();
    }
}