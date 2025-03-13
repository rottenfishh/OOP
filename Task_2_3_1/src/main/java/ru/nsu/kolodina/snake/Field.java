package ru.nsu.kolodina.snake;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Field {
    int n, m;
    public Pixel[][] field;
    Random rand;

    public Field(int n, int m) {
        this.n = n;
        this.m = m;
        rand = new Random();
    }

    public class Pixel {
        Rectangle pixel;
        int x, y;
        boolean taken = false;
        boolean wall;
        boolean fruit;
        public Pixel(int x, int y, boolean wall, boolean fruit) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.fruit = fruit;
            if (wall) {
                pixel = new Rectangle(20, 20, Color.rgb(11, 64, 27));
            } else {
                pixel = new Rectangle(20, 20, Color.rgb(181, 230, 29));
            }
        }

    }
    public void createField(GridPane root) {
        field = new Pixel[n][m];
        boolean flag;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j ++) {
                if (i == 10 && 1 <= j  && j < 5) {
                    flag = true;
                } else {
                    flag = false;
                }
                field[i][j] = new Pixel(j, i, flag, false);
                root.addRow(i, field[i][j].pixel);
            }
        }
    }
    public void setAsTaken(Coordinates pixel, Color color) {
        field[pixel.y][pixel.x].taken = true;
        field[pixel.y][pixel.x].pixel.setFill(color);
    }
    public void setAsFree(Coordinates pixel) {
        field[pixel.y][pixel.x].taken = false;
        field[pixel.y][pixel.x].pixel.setFill( Color.rgb(181, 230, 29));
    }
    public boolean hasFruit(Coordinates pixel) {
        return field[pixel.y][pixel.x].fruit;
    }
    public void eatFruit(Coordinates pixel, Color color) {
        field[pixel.y][pixel.x].fruit = false;
        field[pixel.y][pixel.x].pixel.setFill(color);
        spawnFruit();
    }
    public void spawnFruit() {
        int y = rand.nextInt(n);
        int x = rand.nextInt(m);
        Fruit fruit = new Fruit(new Coordinates(x,y));
        field[y][x].fruit = true;
        field[y][x].pixel.setFill(Color.rgb(216,53,39));
    }
}
