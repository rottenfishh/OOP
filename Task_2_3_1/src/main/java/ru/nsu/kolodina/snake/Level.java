package ru.nsu.kolodina.snake;

import java.util.HashMap;
import java.util.List;

public class Level {
    Map map;
    int speed;
    int number;
    public Level(Map map, int speed, int number) {
        this.map = map;
        this.speed = speed;
        this.number = number;
    }
}
