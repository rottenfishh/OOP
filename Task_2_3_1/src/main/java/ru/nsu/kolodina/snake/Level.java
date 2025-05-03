package ru.nsu.kolodina.snake;

/**
 * Class representing a level in the Snake game.
 * It holds information about the map, speed, level number, and the required snake length for victory.
 */
public class Level {
    Map map;
    int speed;
    int number;
    int victoryLen;

    /**
     * Constructor for initializing a level.
     *
     * @param map        The map associated with the level.
     * @param speed      The speed of the snake in this level.
     * @param number     The level number.
     * @param victoryLen The length the snake must reach to win the level.
     */
    public Level(Map map, int speed, int number, int victoryLen) {
        this.map = map;
        this.speed = speed;
        this.number = number;
        this.victoryLen = victoryLen;
    }

    /**
     * Checks if the snake has reached the required length to win the level.
     *
     * @param snake The current snake object.
     * @return True if the snake has reached the required length, otherwise false.
     */
    public boolean victoryCheck(Snake snake) {
        return snake.len == victoryLen;
    }
}
