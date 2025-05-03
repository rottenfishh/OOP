package ru.nsu.kolodina.snake;

import javafx.scene.Scene;

import java.util.Random;

import static java.lang.Math.abs;

/**
 * Base controller class for Snake game.
 */
public abstract class baseController {
    public final Snake snake;
    public final Scene scene;
    private final Field field;
    private final Level level;
    private final Coordinates[] movementChoice;
    public boolean flag = false;
    Fruits fruits;
    long currTime = System.currentTimeMillis();
    Random rand;

    /**
     * Create baseController.
     *
     * @param scene  scene of game
     * @param snake  snake object
     * @param field  game field
     * @param level  game level
     * @param fruits fruits manager
     */
    baseController(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        this.snake = snake;
        this.field = field;
        this.scene = scene;
        this.level = level;
        this.fruits = fruits;
        this.movementChoice = new Coordinates[4];
        movementChoice[0] = new Coordinates(0, 1);
        movementChoice[1] = new Coordinates(0, -1);
        movementChoice[2] = new Coordinates(1, 0);
        movementChoice[3] = new Coordinates(-1, 0);
        rand = new Random();
    }

    /**
     * Update snake position and check game status.
     */
    public void updateSnake() {
        if (flag) {
            return;
        }
        if (System.currentTimeMillis() - currTime > snake.speed) {
            currTime = System.currentTimeMillis();
            Coordinates newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
            snake.snakeBody.addFirst(newHead);
            snake.head = newHead;
            if (checkDeath(newHead)) {
                System.out.println("you died");
                flag = true;
                return;
            }
            if (level.victoryCheck(snake)) {
                System.out.println("You won!");
                flag = true;
                return;
            }

            if (field.getType(newHead) == Pixel.pixelType.FRUIT) {
                fruits.eatFruit(newHead, snake.color);
                snake.addLen();
            } else {
                field.setAsFree(snake.tail);
                snake.snakeBody.remove(snake.tail);
                snake.tail = snake.snakeBody.getLast();
            }

            field.setAsTaken(snake.head, snake.color);
        }
    }

    /**
     * Calculate movement direction toward target coordinates.
     *
     * @param coords target coordinates
     */
    public void calculateDirection(Coordinates coords) {
        int diffX = coords.x - snake.head.x;
        int diffY = coords.y - snake.head.y;
        if (abs(diffX) > abs(diffY)) {
            if (diffX > 0) {
                snake.updateMovement(1, 0);
            } else {
                snake.updateMovement(-1, 0);
            }
        } else {
            if (diffY > 0) {
                snake.updateMovement(0, 1);
            } else {
                snake.updateMovement(0, -1);
            }
        }
    }

    /**
     * Try to avoid death by choosing random directions.
     */
    public void tryNotToDie() {
        Coordinates newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
        int tries = 0;
        while (checkDeath(newHead) && tries < 10) {
            tries++;
            int choice = rand.nextInt(4);
            snake.updateMovement(movementChoice[choice].x, movementChoice[choice].y);
            newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
        }
    }

    /**
     * Check if snake would die at given coordinates.
     *
     * @param newHead coordinates to check
     * @return true if dead, false otherwise
     */
    public boolean checkDeath(Coordinates newHead) {
        return newHead.x < 0 || newHead.x == field.m || newHead.y < 0
                || newHead.y == field.n
                || field.getType(newHead) == Pixel.pixelType.WALL
                || field.getType(newHead) == Pixel.pixelType.SNAKE;
    }

    /**
     * Abstract method to move snake (to be implemented by subclass).
     */
    public abstract void moveSnake();
}
