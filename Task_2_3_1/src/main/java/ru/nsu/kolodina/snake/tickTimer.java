package ru.nsu.kolodina.snake;

import javafx.animation.AnimationTimer;

public class tickTimer extends AnimationTimer {
    Controller controller;
    public tickTimer(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void handle(long l) {
        controller.updateSnake();
        if (controller.flag) {
            stop();
        }
    }
}
