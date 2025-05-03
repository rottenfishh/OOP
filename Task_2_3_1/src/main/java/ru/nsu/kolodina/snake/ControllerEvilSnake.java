package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

import java.util.Random;

/**
 * Controller for the evil snake that chases the player.
 */
public class ControllerEvilSnake extends baseController {
    Snake playerSnake;
    Random rand;

    /**
     * Create ControllerEvilSnake.
     *
     * @param scene       scene of game
     * @param snake       evil snake
     * @param field       game field
     * @param level       game level
     * @param fruits      fruits manager
     * @param playerSnake player's snake to chase
     */
    ControllerEvilSnake(Scene scene, Snake snake, Field field, Level level, Fruits fruits, Snake playerSnake) {
        super(scene, snake, field, level, fruits);
        this.playerSnake = playerSnake;
        rand = new Random();
    }

    /**
     * Change movement direction toward the player and avoid death.
     */
    public void changeMovement() {
        calculateDirection(playerSnake.head);
        tryNotToDie();
    }

    /**
     * Move evil snake by changing direction and updating position.
     */
    @Override
    public void moveSnake() {
        changeMovement();
        updateSnake();
    }
}
