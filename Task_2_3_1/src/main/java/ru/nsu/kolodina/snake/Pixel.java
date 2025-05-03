package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a single pixel in the game field. Each pixel can be of different types:
 * wall, snake, fruit, or free space. The pixel is represented by a rectangle in the UI.
 */
public class Pixel {
    /**
     * Indicates whether the pixel has a fruit.
     */
    public boolean hasFruit = false;

    /**
     * The fruit that the pixel contains, if any.
     */
    public Fruits.Fruit fruit;

    /**
     * The type of the pixel (e.g., WALL, SNAKE, FRUIT, FREE).
     */
    public pixelType type;

    /**
     * The rectangle representing the pixel in the UI.
     */
    Rectangle pixel;

    /**
     * The x-coordinate of the pixel in the grid.
     */
    int x, y;

    /**
     * Indicates whether the pixel is currently occupied (taken) by a snake or fruit.
     */
    boolean taken = false;

    /**
     * Indicates whether the pixel is a wall.
     */
    boolean wall;

    /**
     * Constructor for creating a pixel at a specific position with a specified wall status.
     *
     * @param x    The x-coordinate of the pixel.
     * @param y    The y-coordinate of the pixel.
     * @param wall Indicates if the pixel is a wall.
     */
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

    /**
     * Adds a fruit to the pixel and changes its type to FRUIT.
     *
     * @param fruit The fruit to add to the pixel.
     */
    public void addFruit(Fruits.Fruit fruit) {
        this.type = Pixel.pixelType.FRUIT;
        this.fruit = fruit;
    }

    /**
     * Enum representing the possible types of a pixel.
     */
    public enum pixelType {
        WALL,  // The pixel is a wall.
        SNAKE, // The pixel is occupied by a snake.
        FRUIT, // The pixel contains a fruit.
        FREE   // The pixel is free space.
    }
}
