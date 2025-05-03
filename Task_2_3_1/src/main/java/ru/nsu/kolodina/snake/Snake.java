package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a snake in the game. The snake is made up of a list of coordinates that represent its body,
 * and its movement is controlled by updating its direction.
 */
public class Snake {
    /**
     * A list that holds the coordinates of the snake's body.
     */
    public final List<Coordinates> snakeBody;
    /**
     * The current direction of movement (x and y coordinates).
     */
    public final Coordinates movement;
    /**
     * The speed of the snake, which determines how fast it moves.
     */
    final int speed;
    /**
     * The field on which the snake is drawn and interacts with.
     */
    private final Field field;
    /**
     * The length of the snake.
     */
    public int len;
    /**
     * The coordinates of the snake's head.
     */
    public Coordinates head;
    /**
     * The coordinates of the snake's tail.
     */
    public Coordinates tail;
    /**
     * The fruits object that handles the fruits in the game.
     */
    Fruits fruits;
    /**
     * The color of the snake.
     */
    Color color;
    /**
     * The level the snake is currently playing.
     */
    Level level;

    /**
     * Constructs a new snake.
     *
     * @param head   The starting coordinates of the snake's head.
     * @param len    The initial length of the snake.
     * @param speed  The speed at which the snake moves.
     * @param field  The field on which the snake exists.
     * @param fruits The fruits in the game.
     * @param level  The level in which the snake is playing.
     * @param color  The color of the snake.
     */
    public Snake(Coordinates head, int len, int speed, Field field, Fruits fruits, Level level, Color color) {
        snakeBody = new ArrayList<>();
        this.head = head;
        snakeBody.add(head);
        tail = snakeBody.get(snakeBody.size() - 1);
        this.len = len;
        this.speed = speed;
        this.movement = new Coordinates(0, 1);
        this.color = color;
        this.field = field;
        this.fruits = fruits;
        this.level = level;
        field.setAsTaken(head, color);
        initSnake();
    }

    /**
     * Initializes the snake by creating its body parts starting from the head.
     */
    public void initSnake() {
        for (int i = 1; i < len; i++) {
            Coordinates snakePart = new Coordinates(head.x, head.y + 1);
            snakeBody.add(snakePart);
            field.setAsTaken(snakePart, color);
        }
    }

    /**
     * Increases the length of the snake by one.
     */
    public void addLen() {
        len++;
    }

    /**
     * Updates the movement direction of the snake.
     *
     * @param x The new x-direction of movement.
     * @param y The new y-direction of movement.
     */
    public void updateMovement(int x, int y) {
        this.movement.x = x;
        this.movement.y = y;
    }

    /**
     * Gets the current movement direction of the snake.
     *
     * @return The current direction of the snake's movement as a Coordinates object.
     */
    public Coordinates getMovement() {
        return movement;
    }
}
