package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;

public class Fruit {
    Coordinates coords;
    int type = 0;
    Color color;
    boolean eaten = false;

    public Fruit(Coordinates coords) {
        this.coords = coords;
        color = Color.rgb(216, 53, 39);
    }
}
