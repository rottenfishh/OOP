package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

import java.util.Random;

public class ControllerEvilSnake extends baseController{
    Snake playerSnake;
    Random rand;
    ControllerEvilSnake(Scene scene, Snake snake, Field field, Level level, Fruits fruits, Snake playerSnake) {
        super(scene, snake, field, level, fruits);
        this.playerSnake = playerSnake;
        rand = new Random();
    }
    public void changeMovement(){
        calculateDirection(playerSnake.head);
        tryNotToDie();
    }
    @Override
    public void moveSnake() {
        changeMovement();
        updateSnake();
    }
}
