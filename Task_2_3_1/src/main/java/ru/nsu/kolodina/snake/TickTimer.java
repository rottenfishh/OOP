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
        controller.moveSnake();
        if (!controllerStupidSnake.flag) {
            controllerStupidSnake.moveSnake();
        }
        if (!controllerEvilSnake.flag) {
            controllerEvilSnake.moveSnake();
        }
        if (!controllerParasiteSnake.flag) {
            controllerParasiteSnake.moveSnake();
        }
        if (controller.flag) {
            System.out.println("what");
            this.stop();
        }
    }
}
