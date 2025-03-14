package ru.nsu.kolodina.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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

        Level firstLevel = new Level(map, 200, 1, 50);

        Field field = new Field(firstLevel.map);
        field.createField(root);

        Text text = new Text();
        root.addRow(0, text);

        Fruits fruits = new Fruits(field, text);
        fruits.spawnFruit();

        Snake snake = new Snake(1, firstLevel.speed, field, fruits, firstLevel);
        Thread threadSnake = new Thread(snake);
        threadSnake.start();

        Controller controller = new Controller(scene, snake, field);
        controller.start();

        Stage primaryStage = new Stage();
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}