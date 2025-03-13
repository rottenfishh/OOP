package ru.nsu.kolodina.snake;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        int height = 20;
        int width = 30;
        GridPane root = new GridPane();
        root.setHgap(1);
        root.setVgap(1);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(128, 128, 128), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, 800, 600);
        Field field = new Field(height,width);
        field.createField(root);
        Snake snake = new Snake(3, 1, height, width, field);
        Thread threadSnake = new Thread(snake);
        Controller controller = new Controller(scene, snake, field);
        controller.start();
        threadSnake.start();
        field.spawnFruit();
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}