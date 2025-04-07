package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

import java.util.Random;

public class ControllerStupidSnake extends baseController{
    Fruits fruits;
    long currTime = System.currentTimeMillis();
    boolean flag = false;
    Random rand;
    private final Coordinates[] movementChoice;
    int i = 0;
    ControllerStupidSnake(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        super(scene, snake, field, level, fruits);
        this.movementChoice = new Coordinates[4];
        movementChoice[0] = new Coordinates(0, 1);
        movementChoice[1] = new Coordinates(0, -1);
        movementChoice[2] = new Coordinates(1, 0);
        movementChoice[3] = new Coordinates(-1, 0);
        rand = new Random();
    }
    public void changeMovement() {
        i++;
        if (i % 5 == 0) {
            int choice = rand.nextInt(4);
            snake.updateMovement(movementChoice[choice].x, movementChoice[choice].y);
            Coordinates newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
            int tries = 0;
            while (checkDeath(newHead) && tries < 10){
                choice = rand.nextInt(4);
                tries++;
                snake.updateMovement(movementChoice[choice].x, movementChoice[choice].y);
                newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
            }
        }
    }
}
