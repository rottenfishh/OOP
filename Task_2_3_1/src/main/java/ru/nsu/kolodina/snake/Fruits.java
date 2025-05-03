package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Manages fruits on the game field, including spawning and tracking eaten fruits.
 */
public class Fruits {
    public Text text;
    List<Fruit> fruits;
    int numFruits;
    Field field;
    int n, m;
    int fruitsEaten = 0;
    Random rand;

    /**
     * Constructs a Fruits manager for the game field.
     *
     * @param field the game field
     * @param text  the UI text to display the number of fruits eaten
     */
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

    /**
     * Spawns a new fruit on a random free cell of the field.
     */
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

    /**
     * Handles eating a fruit at a given position, updates counters, and spawns a new fruit.
     *
     * @param pixel the coordinates where the fruit was eaten
     * @param color the color to apply to the cell after eating
     */
    public void eatFruit(Coordinates pixel, Color color) {
        field.setType(pixel, Pixel.pixelType.FREE);
        field.setColor(pixel, color);
        fruitsEaten++;
        spawnFruit();
        Fruit eaten = field.getFruit(pixel);
        fruits.remove(eaten);
        text.setText("Fruits eaten " + fruitsEaten);
    }

    /**
     * Represents a fruit on the field.
     */
    public class Fruit {
        Coordinates coords;
        int type = 0;
        int effect;
        Color color;
        boolean eaten = false;

        /**
         * Constructs a fruit at the specified coordinates.
         *
         * @param coords the location of the fruit
         */
        public Fruit(Coordinates coords) {
            this.coords = coords;
            color = Color.rgb(216, 53, 39);
        }
    }
}
