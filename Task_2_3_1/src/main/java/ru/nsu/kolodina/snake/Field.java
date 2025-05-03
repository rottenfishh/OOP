package ru.nsu.kolodina.snake;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Represents the game field consisting of pixels.
 */
public class Field {
    public Pixel[][] field;
    int n, m;
    Random rand;
    Map map;

    /**
     * Creates a field with a given map.
     *
     * @param map map containing the layout
     */
    public Field(Map map) {
        this.n = map.n;
        this.m = map.m;
        rand = new Random();
        this.map = map;
    }

    /**
     * Initializes the field grid and adds it to the UI.
     *
     * @param root the GridPane to attach the field to
     */
    public void createField(GridPane root) {
        field = new Pixel[n][m];
        boolean flag;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flag = map.arr[i][j] == 1;
                field[i][j] = new Pixel(j, i, flag);
                root.addRow(i, field[i][j].pixel);
            }
        }
    }

    /**
     * Sets the color of a pixel.
     *
     * @param pixel the coordinates of the pixel
     * @param color the color to set
     */
    public void setColor(Coordinates pixel, Color color) {
        field[pixel.y][pixel.x].pixel.setFill(color);
    }

    /**
     * Sets the type of a pixel.
     *
     * @param coords the coordinates of the pixel
     * @param type   the pixel type to set
     */
    public void setType(Coordinates coords, Pixel.pixelType type) {
        field[coords.y][coords.x].type = type;
    }

    /**
     * Gets the type of a pixel.
     *
     * @param coords the coordinates of the pixel
     * @return the pixel type
     */
    public Pixel.pixelType getType(Coordinates coords) {
        return field[coords.y][coords.x].type;
    }

    /**
     * Marks a pixel as taken by the snake and sets its color.
     *
     * @param pixel the coordinates of the pixel
     * @param color the color of the snake
     */
    public void setAsTaken(Coordinates pixel, Color color) {
        field[pixel.y][pixel.x].type = Pixel.pixelType.SNAKE;
        setColor(pixel, color);
    }

    /**
     * Marks a pixel as free and sets it to the default color.
     *
     * @param pixel the coordinates of the pixel
     */
    public void setAsFree(Coordinates pixel) {
        field[pixel.y][pixel.x].type = Pixel.pixelType.FREE;
        setColor(pixel, Color.rgb(181, 230, 29));
    }

    /**
     * Places a fruit on a pixel.
     *
     * @param pixel the coordinates of the pixel
     * @param fruit the fruit to place
     */
    public void putFruit(Coordinates pixel, Fruits.Fruit fruit) {
        field[pixel.y][pixel.x].addFruit(fruit);
    }

    /**
     * Gets the fruit from a pixel.
     *
     * @param pixel the coordinates of the pixel
     * @return the fruit at the pixel
     */
    public Fruits.Fruit getFruit(Coordinates pixel) {
        return field[pixel.y][pixel.x].fruit;
    }
}
