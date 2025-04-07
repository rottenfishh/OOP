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

public class MainApp extends Application {

    public static void main(String[] args) {
        launch();
    }

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
        Coordinates ourHead = new Coordinates(0,0);
        Coordinates otherHead = new Coordinates(3,4);
        Snake snake = new Snake(ourHead, 1, firstLevel.speed, field, fruits, firstLevel, ourSnake);
        Snake enemySnake = new Snake(otherHead, 1, firstLevel.speed, field, fruits, firstLevel, otherSnake);
        Controller controller = new Controller(scene, snake, field, firstLevel, fruits);
        ControllerOtherSnake controllerOtherSnake = new ControllerOtherSnake(scene, enemySnake, field, firstLevel, fruits);
        controller.start();
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.setScene(scene);
        stage.show();
        TickTimer timer = new TickTimer(controller, controllerOtherSnake);
        timer.start();
    }

}