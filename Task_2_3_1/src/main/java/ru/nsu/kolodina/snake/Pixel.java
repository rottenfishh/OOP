package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pixel {
    Rectangle pixel;
    int x, y;
    boolean taken = false;
    boolean wall;
    public boolean hasFruit = false;
    public Fruits.Fruit fruit;
    public pixelType type;

    public Pixel(int x, int y, boolean wall) {
        this.x = x;
        this.y = y;
        this.wall = wall;
        if (wall) {
            this.type = Pixel.pixelType.WALL;
            pixel = new Rectangle(20, 20, Color.rgb(115, 147, 19));
        } else {
            this.type = Pixel.pixelType.FREE;
            pixel = new Rectangle(20, 20, Color.rgb(181, 230, 29));
        }
    }

    public void addFruit(Fruits.Fruit fruit) {
        this.type = Pixel.pixelType.FRUIT;
        this.fruit = fruit;
    }
    public enum pixelType{
        WALL,
        SNAKE,
        FRUIT,
        FREE;
    }
}

