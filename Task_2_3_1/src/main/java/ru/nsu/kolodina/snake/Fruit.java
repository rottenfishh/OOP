package ru.nsu.kolodina.snake;

public class Fruit {
    Coordinates coords;
    int type = 0;
    boolean eaten = false;
    public Fruit(Coordinates coords) {
        this.coords = coords;
    }
}
