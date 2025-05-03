package ru.nsu.kolodina.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class of the Snake game application.
 * It initializes the game, creates the level, snakes, and sets up the GUI.
 */
public class MainApp extends Application {

    /**
     * The main entry point for the JavaFX application.
     *
     * @param args Command-line arguments (unused in this application).
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initializes the game, creates a scene, sets up the stage, and starts the game.
     *
     * @param stage The primary stage for this application, onto which the scene is set.
     */
    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLdoc.fxml"));
        GridPane root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 800, 420);

        /**
         * Creates and sets up the game components.
         */
        Map map = new Map();
        map.createDefaultMap();

        Level firstLevel = new Level(map, 200, 1, 30);

        Field field = new Field(firstLevel.map);
        field.createField(root);

        Text text = new Text();
        root.addRow(0, text);

        Fruits fruits = new Fruits(field, text);
        fruits.spawnFruit();

        Color ourSnake = Color.rgb(185, 111, 35);
        Color otherSnake = Color.rgb(228, 18, 18);
        Color evilSnakeColor = Color.rgb(166, 19, 198);
        Color parasiteSnakeColor = Color.rgb(250, 141, 72);

        Coordinates ourHead = new Coordinates(5, 5);
        Coordinates otherHead = new Coordinates(3, 4);
        Coordinates evilHead = new Coordinates(10, 7);
        Coordinates parasiteHead = new Coordinates(15, 8);

        Snake snake = new Snake(ourHead, 1, firstLevel.speed, field, fruits, firstLevel, ourSnake);
        Snake enemySnake = new Snake(otherHead, 3, firstLevel.speed, field, fruits, firstLevel, otherSnake);
        Snake evilSnake = new Snake(evilHead, 4, firstLevel.speed + 50, field, fruits, firstLevel, evilSnakeColor);
        Snake parasiteSnake = new Snake(parasiteHead, 5, firstLevel.speed + 100, field, fruits, firstLevel, parasiteSnakeColor);

        ControllerEvilSnake controllerEvilSnake = new ControllerEvilSnake(scene, evilSnake, field, firstLevel, fruits, snake);
        Controller controller = new Controller(scene, snake, field, firstLevel, fruits);
        ControllerStupidSnake controllerStupidSnake = new ControllerStupidSnake(scene, enemySnake, field, firstLevel, fruits);
        ControllerParasiteSnake controllerParasiteSnake = new ControllerParasiteSnake(scene, parasiteSnake, field, firstLevel, fruits);
        controller.start();

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.setScene(scene);
        stage.show();

        TickTimer timer = new TickTimer(controller, controllerStupidSnake, controllerEvilSnake, controllerParasiteSnake);
        timer.start();
    }

}
