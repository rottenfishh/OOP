package oop.kolodina;

public class Main {
    public static int wins;
    public static int losses;
    public static int draws;

    public static Boolean stopGame;

    public static void main(String[] args) {
        stopGame = false;
        Game game = new Game();
        wins = 0;
        losses = 0;
        draws = 0;
        int rounds = 1;
        System.out.println("Добро пожаловать в Блэкджек! Введите q, чтобы выйти из игры.");
        while (true) {
            System.out.println("----------");
            System.out.println("Раунд " + rounds);
            game.startGame();
            rounds++;
            if (stopGame) {
                break;
            }
        }
    }
}
