package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * snake class.
 */
public class Snake {
    final int speed;
    private final Field field;
    Fruits fruits;
    public final List<Coordinates> snakeBody;
    public final Coordinates movement;
    Color color;
    public int len;
    Level level;
    public Coordinates head;
    public Coordinates tail;

    public Snake(Coordinates head, int len, int speed,Field field, Fruits fruits, Level level, Color color) {
        snakeBody = new ArrayList<>();
        this.head = head;
        snakeBody.add(head);
        tail = snakeBody.getLast();
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

    public void initSnake() {
        for (int i = 1; i < len; i++) {
            Coordinates snakePart = new Coordinates(head.x, head.y+1);
            snakeBody.add(snakePart);
            field.setAsTaken(snakePart, color);
        }
    }
    public void addLen() {
        len++;
    }

    public void updateMovement(int x, int y) {
        this.movement.x = x;
        this.movement.y = y;
    }
    public Coordinates getMovement() {
        return movement;
    }
    
}