package ru.nsu.kolodina.snake;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

/**
 * Controller class for handling Snake game input and movement.
 */
public class Controller extends baseController {

    Fruits fruits;
    long currTime = System.currentTimeMillis();

    /**
     * Create Controller.
     *
     * @param scene  scene of game
     * @param snake  snake object
     * @param field  game field
     * @param level  game level
     * @param fruits fruits manager
     */
    Controller(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        super(scene, snake, field, level, fruits);
    }

    /**
     * Set up key event handlers for controlling the snake.
     */
    public void start() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if (!(snake.getMovement().x == 1)) {
                    snake.updateMovement(-1, 0);
                }
            }
            if (event.getCode() == KeyCode.RIGHT) {
                if (!(snake.getMovement().x == -1)) {
                    snake.updateMovement(1, 0);
                }
            }
            if (event.getCode() == KeyCode.UP) {
                if (!(snake.getMovement().y == 1)) {
                    snake.updateMovement(0, -1);
                }
            }
            if (event.getCode() == KeyCode.DOWN) {
                if (!(snake.getMovement().y == -1)) {
                    snake.updateMovement(0, 1);
                }
            }
        });
    }

    /**
     * Move snake by updating its position.
     */
    @Override
    public void moveSnake() {
        updateSnake();
    }
}
