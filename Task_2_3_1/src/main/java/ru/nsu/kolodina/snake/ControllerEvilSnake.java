package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

import java.util.Random;

import static java.lang.Math.abs;

public class ControllerEvilSnake extends baseController{
    Snake playerSnake;
    Random rand;
    private final Coordinates[] movementChoice;
    ControllerEvilSnake(Scene scene, Snake snake, Field field, Level level, Fruits fruits, Snake playerSnake) {
        super(scene, snake, field, level, fruits);
        this.playerSnake = playerSnake;
        this.movementChoice = new Coordinates[4];
        movementChoice[0] = new Coordinates(0, 1);
        movementChoice[1] = new Coordinates(0, -1);
        movementChoice[2] = new Coordinates(1, 0);
        movementChoice[3] = new Coordinates(-1, 0);
        rand = new Random();
    }
    public void changeMovement(){
        int diffX = playerSnake.head.x - snake.head.x;
        int diffY = playerSnake.head.y - snake.head.y;
        if (abs(diffX) > abs(diffY)) {
            if (diffX > 0) {
                snake.updateMovement(1, 0);
            } else {
                snake.updateMovement(-1, 0);
            }
        }
        else {
            if (diffY > 0) {
                snake.updateMovement(0, 1);
            } else {
                snake.updateMovement(0, -1);
            }
        }
        Coordinates newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
        int tries = 0;
        while (checkDeath(newHead) && tries < 10){
            int choice = rand.nextInt(4);
            tries++;
            snake.updateMovement(movementChoice[choice].x, movementChoice[choice].y);
            newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
        }
    }
}
