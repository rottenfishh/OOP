package ru.nsu.kolodina.snake;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static javafx.application.Platform.exit;

public class Snake implements Runnable{
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
        snakeBody.add(head);
        tail = snakeBody.getLast();
        this.len = len;
        this.speed = 200;
        this.movement = new Coordinates(0, speed);
        this.fieldM = fieldM;
        this.fieldN = fieldN;
        this.color = Color.rgb(185,111,35);
        this.field = field;
        field.setAsTaken(head, color);
    }
    public void addLen() {
        len++;
    }
    public void updateMovement(int x, int y) {
        this.movement.x = x;
        this.movement.y = y;
    }

    @Override
    public void run() {
        while(true) {
            Coordinates newHead = new Coordinates(head.x + movement.x, head.y + movement.y);
            snakeBody.addFirst(newHead);
            head = newHead;
            if (newHead.x < 0 || newHead.x == fieldN || newHead.y < 0
                    ||  newHead.y  == fieldM
                    || field.field[newHead.y][newHead.x].wall
                    || field.field[newHead.y][newHead.x].taken) {
                System.out.println("you died!");
                break;
            }
            field.setAsTaken(head, color);
            if (field.hasFruit(newHead)) {
                field.eatFruit(newHead, color);
                addLen();
            } else {
                field.setAsFree(tail);
                snakeBody.remove(tail);
                tail = snakeBody.getLast();
            }
            try {
                sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}