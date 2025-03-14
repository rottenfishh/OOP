package ru.nsu.kolodina.snake;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Controller {
    private final Snake snake;
    private final Field field;
    private final Scene scene;

    Controller(Scene scene, Snake snake, Field field) {
        this.snake = snake;
        this.field = field;
        this.scene = scene;
    }

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
}
