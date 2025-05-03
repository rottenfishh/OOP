package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

/**
 * Controller for the parasite snake that targets fruits.
 */
public class ControllerParasiteSnake extends baseController {

    /**
     * Create ControllerParasiteSnake.
     *
     * @param scene  scene of game
     * @param snake  parasite snake
     * @param field  game field
     * @param level  game level
     * @param fruits fruits manager
     */
    ControllerParasiteSnake(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        super(scene, snake, field, level, fruits);
    }

    /**
     * Change movement direction toward the nearest fruit and avoid death.
     */
    public void changeMovement() {
        calculateDirection(fruits.fruits.getFirst().coords);
        tryNotToDie();
    }

    /**
     * Move parasite snake by changing direction and updating position.
     */
    @Override
    public void moveSnake() {
        changeMovement();
        updateSnake();
    }
}
