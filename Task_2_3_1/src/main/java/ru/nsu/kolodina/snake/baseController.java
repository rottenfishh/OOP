package ru.nsu.kolodina.snake;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public abstract class baseController {
    public final Snake snake;
    private final Field field;
    public final Scene scene;
    private final Level level;
    Fruits fruits;
    long currTime = System.currentTimeMillis();
    public boolean flag = false;

    baseController(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        this.snake = snake;
        this.field = field;
        this.scene = scene;
        this.level = level;
        this.fruits = fruits;
    }

    public void updateSnake() {
        if (flag) {
            return;
        }
        if (System.currentTimeMillis() - currTime > snake.speed) {
            currTime = System.currentTimeMillis();
            Coordinates newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
            snake.snakeBody.addFirst(newHead);
            snake.head = newHead;
            if (checkDeath(newHead)) {
                System.out.println("you died");
                flag = true;
                return;
            }
            if (level.victoryCheck(snake)) {
                System.out.println("You won!");
                flag = true;
                return;
            }

            if (field.getType(newHead) == Pixel.pixelType.FRUIT) {
                fruits.eatFruit(newHead, snake.color);
                snake.addLen();
            } else {
                field.setAsFree(snake.tail);
                snake.snakeBody.remove(snake.tail);
                snake.tail = snake.snakeBody.getLast();
            }

            field.setAsTaken(snake.head, snake.color);
        }
    }
    public boolean checkDeath(Coordinates newHead) {
        if (newHead.x < 0 || newHead.x == field.m || newHead.y < 0
                || newHead.y == field.n
                || field.getType(newHead) == Pixel.pixelType.WALL
                || field.getType(newHead) == Pixel.pixelType.SNAKE) {
            return true;
        }
        return false;
    }
}
