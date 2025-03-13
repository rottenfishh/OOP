package ru.nsu.kolodina.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        Text text = new Text();
        Field field = new Field(height,width, text);
        field.createField(root);
        root.addRow(0, text);

        Snake snake = new Snake(3, 1, height, width, field);
        Thread threadSnake = new Thread(snake);
        Controller controller = new Controller(scene, snake, field);

        controller.start();
        threadSnake.start();
        field.spawnFruit();

        Stage primaryStage = new Stage();
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}