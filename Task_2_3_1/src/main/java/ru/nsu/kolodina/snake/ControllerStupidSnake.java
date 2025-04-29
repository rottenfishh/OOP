package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

import java.util.Random;

public class ControllerStupidSnake extends baseController{
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
            tryNotToDie();
        }
    }
    @Override
    public void moveSnake() {
        changeMovement();
        updateSnake();
    }
}
