package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * snake class.
 */
public class Snake implements Runnable {
    private final int fieldN;
    private final int fieldM;
    private final int speed;
    private final Field field;
    Fruits fruits;
    private final List<Coordinates> snakeBody;
    private final Coordinates movement;
    Color color;
    public int len;
    Level level;
    private Coordinates head;
    private Coordinates tail;

    public Snake(int len, int speed,Field field, Fruits fruits, Level level) {
        snakeBody = new ArrayList<>();
        head = new Coordinates(0, 0);
        snakeBody.add(head);
        tail = snakeBody.getLast();
        this.len = len;
        this.speed = speed;
        this.movement = new Coordinates(0, 1);
        this.fieldM = field.m;
        this.fieldN = field.n;
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
    @Override
    public void run() {
        while (true) {
            Coordinates newHead = new Coordinates(head.x + movement.x, head.y + movement.y);
            snakeBody.addFirst(newHead);
            head = newHead;
            if (newHead.x < 0 || newHead.x == fieldN || newHead.y < 0
                    || newHead.y == fieldM
                    || field.getType(newHead) == Pixel.pixelType.WALL
                    || field.getType(newHead) ==  Pixel.pixelType.SNAKE) {
                System.out.println("you died!");
                break;
            }
            if (level.victoryCheck(this)) {
                System.out.println("You won!");
                break;
            }

            if (field.getType(newHead) == Pixel.pixelType.FRUIT) {
                fruits.eatFruit(newHead, color);
                addLen();
            } else {
                field.setAsFree(tail);
                snakeBody.remove(tail);
                tail = snakeBody.getLast();
            }

            field.setAsTaken(head, color);
            try {
                sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}