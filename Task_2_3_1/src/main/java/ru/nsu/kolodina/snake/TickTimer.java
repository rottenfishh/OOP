package ru.nsu.kolodina.snake;

import javafx.animation.AnimationTimer;

public class TickTimer extends AnimationTimer {
    Controller controller;
    ControllerOtherSnake controllerOtherSnake;
    public TickTimer(Controller controller, ControllerOtherSnake controllerOtherSnake) {
        this.controller = controller;
        this.controllerOtherSnake = controllerOtherSnake;
    }
    @Override
    public void handle(long l) {
        controller.updateSnake();
        controllerOtherSnake.changeMovement();
        controllerOtherSnake.updateSnake();
        if (controller.flag) {
            stop();
        }
        if (controllerOtherSnake.flag) {

        }
    }
}
