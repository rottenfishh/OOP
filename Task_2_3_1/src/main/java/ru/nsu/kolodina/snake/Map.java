package ru.nsu.kolodina.snake;

public class Map {
    int n,m;
    int[][] arr; //0 - free, 1 - wall
    public void createDefaultMap() {
        n = 20;
        m = 30;
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (8 <= i && i <= 12 && j == 2) {
                    arr[i][j] = 1;
                }
                if (i == 2 && 3 <= j && j<= 5) {
                    arr[i][j] = 1;
                }
                if (7 <= i && i <= 12 && j ==8) {
                    arr[i][j] = 1;
                }
                if (i == 5 && 15 <= j && j <= 17){
                    arr[i][j] = 1;
                }
                if (5 <= i && i <= 9 && j == 17) {
                    arr[i][j] = 1;
                }
                if (i == 16 &&  22 <= j && j <= 24) {
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
