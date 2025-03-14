package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fruits {
    public Text text;
    List<Fruit> fruits;
    int numFruits;
    Field field;
    int n, m;
    int fruitsEaten = 0;
    Random rand;

    public Fruits(Field field, Text text) {
        this.fruits = new ArrayList<>();
        numFruits = fruits.size();
        this.text = text;
        text.setText("Fruits eaten " + numFruits);
        this.n = field.n;
        this.m = field.m;
        this.field = field;
        rand = new Random();
    }

    public void spawnFruit() {
        int y = rand.nextInt(n);
        int x = rand.nextInt(m);
        Coordinates coords = new Coordinates(x, y);
        while (field.getType(coords) == Pixel.pixelType.SNAKE
                || field.getType(coords) == Pixel.pixelType.WALL) {
            y = rand.nextInt(n);
            x = rand.nextInt(m);
            coords = new Coordinates(x, y);
        }
        Fruit newFruit = new Fruit(coords);
        fruits.add(newFruit);
        field.putFruit(coords, newFruit);
        field.setColor(coords, newFruit.color);
    }

    public void eatFruit(Coordinates pixel, Color color) {
        field.setType(pixel, Pixel.pixelType.FREE);
        field.setColor(pixel, color);
        fruitsEaten++;
        spawnFruit();
        Fruit eaten = field.getFruit(pixel);
        fruits.remove(eaten);
        text.setText("Fruits eaten " + fruitsEaten);
    }

    public class Fruit {
        Coordinates coords;
        int type = 0;
        int effect;
        Color color;
        boolean eaten = false;

        public Fruit(Coordinates coords) {
            this.coords = coords;
            color = Color.rgb(216, 53, 39);
        }
    }
}
