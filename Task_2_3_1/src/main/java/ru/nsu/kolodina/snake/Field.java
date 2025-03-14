package ru.nsu.kolodina.snake;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class Field {
    public Pixel[][] field;
    int n, m;
    int fruitsEaten = 0;
    Random rand;
    Map map;
    public Field(Map map) {
        this.n = map.n;
        this.m = map.m;
        rand = new Random();
        this.map = map;
    }

    public void createField(GridPane root) {
        field = new Pixel[n][m];
        boolean flag;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map.arr[i][j] == 1) {
                    flag = true;
                } else {
                    flag = false;
                }
                field[i][j] = new Pixel(j, i, flag);
                root.addRow(i, field[i][j].pixel);
            }
        }
    }
    public void setColor(Coordinates pixel, Color color) {
        field[pixel.y][pixel.x].pixel.setFill(color);
    }
    public void setType(Coordinates coords, Pixel.pixelType type) {
        field[coords.y][coords.x].type = type;
    }
    public Pixel.pixelType getType(Coordinates coords) {
        return field[coords.y][coords.x].type;
    }
    public void setAsTaken(Coordinates pixel, Color color) {
        field[pixel.y][pixel.x].type = Pixel.pixelType.SNAKE;
        setColor(pixel, color);
    }

    public void setAsFree(Coordinates pixel) {
        field[pixel.y][pixel.x].type = Pixel.pixelType.FREE;
        setColor(pixel, Color.rgb(181, 230, 29));
    }

    public void putFruit(Coordinates pixel, Fruits.Fruit fruit) {
        field[pixel.y][pixel.x].addFruit(fruit);
    }

    public Fruits.Fruit getFruit(Coordinates pixel) {
        return field[pixel.y][pixel.x].fruit;
    }
}
