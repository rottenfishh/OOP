package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

import java.util.Random;

/**
 * Controller for the stupid snake that moves randomly.
 */
public class ControllerStupidSnake extends baseController {
    private final Coordinates[] movementChoice;
    boolean flag = false;
    Random rand;
    int i = 0;

    /**
     * Create ControllerStupidSnake.
     *
     * @param scene  scene of game
     * @param snake  stupid snake
     * @param field  game field
     * @param level  game level
     * @param fruits fruits manager
     */
    ControllerStupidSnake(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        super(scene, snake, field, level, fruits);
        this.movementChoice = new Coordinates[4];
        movementChoice[0] = new Coordinates(0, 1);
        movementChoice[1] = new Coordinates(0, -1);
        movementChoice[2] = new Coordinates(1, 0);
        movementChoice[3] = new Coordinates(-1, 0);
        rand = new Random();
    }

    /**
     * Change movement direction randomly every few steps and avoid death.
     */
    public void changeMovement() {
        i++;
        if (i % 5 == 0) {
            int choice = rand.nextInt(4);
            snake.updateMovement(movementChoice[choice].x, movementChoice[choice].y);
            tryNotToDie();
        }
    }

    /**
     * Move stupid snake by changing direction and updating position.
     */
    @Override
    public void moveSnake() {
        changeMovement();
        updateSnake();
    }
}
