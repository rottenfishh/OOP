package ru.nsu.kolodina.snake;

import javafx.animation.AnimationTimer;

public class TickTimer extends AnimationTimer {
    Controller controller;
    ControllerStupidSnake controllerStupidSnake;
    ControllerEvilSnake controllerEvilSnake;
    ControllerParasiteSnake controllerParasiteSnake;
    int count = 0;
    public TickTimer(Controller controller, ControllerStupidSnake controllerStupidSnake,
                     ControllerEvilSnake controllerEvilSnake, ControllerParasiteSnake controllerParasiteSnake) {
        this.controller = controller;
        this.controllerStupidSnake = controllerStupidSnake;
        this.controllerEvilSnake = controllerEvilSnake;
        this.controllerParasiteSnake = controllerParasiteSnake;
    }
    @Override
    public void handle(long l) {
        count++;
        controller.updateSnake();
        if (!controllerStupidSnake.flag) {
            controllerStupidSnake.changeMovement();
            controllerStupidSnake.updateSnake();
        }
        if (!controllerEvilSnake.flag) {
            controllerEvilSnake.changeMovement();
            controllerEvilSnake.updateSnake();
        }
        if (!controllerParasiteSnake.flag) {
            controllerParasiteSnake.changeMovement();
            controllerParasiteSnake.updateSnake();
        }
        if (controller.flag) {
            System.out.println("what");
            this.stop();
        }
    }
}
