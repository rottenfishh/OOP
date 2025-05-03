package ru.nsu.kolodina.snake;

/**
 * Represents the game map, which includes the dimensions and wall placements.
 * The map is represented as a 2D array where 0 indicates free space and 1 indicates a wall.
 */
public class Map {
    /**
     * The number of rows in the map.
     */
    int n, m;

    /**
     * The 2D array representing the map.
     * 0 - free space, 1 - wall.
     */
    int[][] arr;

    /**
     * Creates the default map by initializing the 2D array with specific wall placements.
     * The map is defined with walls placed at specific coordinates, while the rest is free space.
     */
    public void createDefaultMap() {
        n = 20;
        m = 30;
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (8 <= i && i <= 12 && j == 2) {
                    arr[i][j] = 1;
                }
                if (i == 2 && 3 <= j && j <= 5) {
                    arr[i][j] = 1;
                }
                if (7 <= i && i <= 12 && j == 8) {
                    arr[i][j] = 1;
                }
                if (i == 5 && 15 <= j && j <= 17) {
                    arr[i][j] = 1;
                }
                if (5 <= i && i <= 9 && j == 17) {
                    arr[i][j] = 1;
                }
                if (i == 16 && 22 <= j && j <= 24) {
                    arr[i][j] = 1;
                }
                if (i == 17 && 21 <= j && j <= 24) {
                    arr[i][j] = 1;
                }
                if (18 <= i && i <= 19 && j == 24) {
                    arr[i][j] = 1;
                }
                if (i == 0 && 26 <= j && j <= 29) {
                    arr[i][j] = 1;
                }
                if (i == 1 && j == 29) {
                    arr[i][j] = 1;
                }
            }
        }
    }
}
