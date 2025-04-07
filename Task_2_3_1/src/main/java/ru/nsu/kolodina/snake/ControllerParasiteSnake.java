package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

public class ControllerParasiteSnake extends baseController {
    ControllerParasiteSnake(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        super(scene, snake, field, level, fruits);
    }
    public void changeMovement() {
        calculateDirection(fruits.fruits.getFirst().coords);
        tryNotToDie();
    }
    @Override
    public void moveSnake() {
        changeMovement();
        updateSnake();
    }
}
