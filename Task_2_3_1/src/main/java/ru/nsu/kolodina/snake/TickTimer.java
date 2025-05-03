package ru.nsu.kolodina.snake;

import javafx.animation.AnimationTimer;

/**
 * A custom timer that controls the movement of the snakes in the game.
 * It uses the {@link AnimationTimer} to repeatedly call the move methods
 * for the various snake controllers at each frame.
 */
public class TickTimer extends AnimationTimer {
    /**
     * The controller responsible for moving the main player snake.
     */
    Controller controller;

    /**
     * The controller responsible for moving the "stupid" snake.
     */
    ControllerStupidSnake controllerStupidSnake;

    /**
     * The controller responsible for moving the "evil" snake.
     */
    ControllerEvilSnake controllerEvilSnake;

    /**
     * The controller responsible for moving the "parasite" snake.
     */
    ControllerParasiteSnake controllerParasiteSnake;

    /**
     * A counter used to keep track of the number of animation frames.
     */
    int count = 0;

    /**
     * Constructs a new {@code TickTimer} with the provided controllers for each snake.
     *
     * @param controller              The controller for the main player snake.
     * @param controllerStupidSnake   The controller for the "stupid" snake.
     * @param controllerEvilSnake     The controller for the "evil" snake.
     * @param controllerParasiteSnake The controller for the "parasite" snake.
     */
    public TickTimer(Controller controller, ControllerStupidSnake controllerStupidSnake,
                     ControllerEvilSnake controllerEvilSnake, ControllerParasiteSnake controllerParasiteSnake) {
        this.controller = controller;
        this.controllerStupidSnake = controllerStupidSnake;
        this.controllerEvilSnake = controllerEvilSnake;
        this.controllerParasiteSnake = controllerParasiteSnake;
    }

    /**
     * This method is called on each frame by the {@link AnimationTimer}.
     * It moves all snakes using their respective controllers and stops the timer
     * if the main snake reaches a certain condition.
     *
     * @param l The timestamp of the current frame.
     */
    @Override
    public void handle(long l) {
        count++;

        // Move the main player snake
        controller.moveSnake();

        // Move the "stupid" snake if its flag is false
        if (!controllerStupidSnake.flag) {
            controllerStupidSnake.moveSnake();
        }

        // Move the "evil" snake if its flag is false
        if (!controllerEvilSnake.flag) {
            controllerEvilSnake.moveSnake();
        }

        // Move the "parasite" snake if its flag is false
        if (!controllerParasiteSnake.flag) {
            controllerParasiteSnake.moveSnake();
        }

        // If the main snake's flag is true, stop the timer
        if (controller.flag) {
            System.out.println("what");
            this.stop();
        }
    }
}
