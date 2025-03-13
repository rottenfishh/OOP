package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static javafx.application.Platform.exit;

public class Snake implements Runnable{
    private Coordinates fieldCoords;
    private int fieldN, fieldM;
    private int len;
    private int speed;
    Color color;
    private Field field;
    private List<Coordinates> snakeBody;
    private Coordinates head;
    private Coordinates tail;
    private Coordinates movement;

    public Snake(int len, int speed, int fieldM, int fieldN, Field field) {
        snakeBody = new ArrayList<>();
        head = new Coordinates(0, 2);
        tail = new Coordinates(0, 0);
        snakeBody.add(head);
        snakeBody.add(new Coordinates(0, 1));
        snakeBody.add(tail);
        this.len = len;
        this.speed = speed;
        this.movement = new Coordinates(0, speed);
        this.fieldM = fieldM;
        this.fieldN = fieldN;
        this.color = Color.rgb(185,111,35);
        this.field = field;
        field.setAsTaken(head, color);
        field.setAsTaken(new Coordinates(head.x, head.y-1), color);
        field.setAsTaken(tail, color);
    }
    public void addLen() {

    }

    public void pressLeft() {
        movement.x = - speed;
        movement.y = 0;
    }
    public void pressRight() {
        movement.x = + speed;
        movement.y = 0;
    }
    public void pressUp() {
        movement.y = - speed;
        movement.x = 0;
    }
    public void pressDown() {
        movement.y = + speed;
        movement.x = 0;
    }

    @Override
    public void run() {
        while(true) {
            field.setAsFree(tail);
            snakeBody.remove(tail);
            tail = snakeBody.getLast();
            Coordinates newHead = new Coordinates(head.x + movement.x, head.y + movement.y);
            snakeBody.addFirst(newHead);
            head = newHead;
            field.setAsTaken(head, color);
            if (newHead.x < 0 || newHead.x == fieldN || newHead.y < 0 ||  newHead.y  == fieldM || field.field[newHead.y][newHead.x].wall) {
                System.out.println("you died!");
                exit();
                break;
            }
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}