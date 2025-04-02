package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * snake class.
 */
public class Snake {
    private final int fieldN;
    private final int fieldM;
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

    public Snake(int len, int speed,Field field, Fruits fruits, Level level) {
        snakeBody = new ArrayList<>();
        head = new Coordinates(5, 5);
        snakeBody.add(head);
        tail = snakeBody.getLast();
        this.len = len;
        this.speed = speed;
        this.movement = new Coordinates(0, 1);
        this.fieldM = field.n;
        this.fieldN = field.m;
        this.color = Color.rgb(185, 111, 35);
        this.field = field;
        this.fruits = fruits;
        this.level = level;
        field.setAsTaken(head, color);
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