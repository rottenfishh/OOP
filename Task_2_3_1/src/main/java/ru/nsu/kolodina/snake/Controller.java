package ru.nsu.kolodina.snake;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class Controller extends baseController{

    Fruits fruits;
    long currTime = System.currentTimeMillis();
    boolean flag = false;

    Controller(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        super(scene, snake, field, level, fruits);
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
