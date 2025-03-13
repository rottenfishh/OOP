package ru.nsu.kolodina.snake;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static java.lang.Thread.sleep;

public class Controller {
    private Snake snake;
    private Field field;
    private Scene scene;
    Controller(Scene scene, Snake snake, Field field) {
        this.snake = snake;
        this.field = field;
        this.scene = scene;
    }
    public void start() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                snake.pressLeft();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                snake.pressRight();
            }
            if (event.getCode() == KeyCode.UP) {
                snake.pressUp();
            }
            if (event.getCode() == KeyCode.DOWN) {
                snake.pressDown();
            }
        });
    }
}
