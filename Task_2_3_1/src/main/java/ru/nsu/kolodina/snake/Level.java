package ru.nsu.kolodina.snake;

public class Level {
    Map map;
    int speed;
    int number;
    int victoryLen;

    public Level(Map map, int speed, int number, int victoryLen) {
        this.map = map;
        this.speed = speed;
        this.number = number;
        this.victoryLen = victoryLen;
    }

    public boolean victoryCheck(Snake snake) {
        return snake.len == victoryLen;
    }
}
