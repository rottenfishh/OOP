package ru.nsu.kolodina.snake;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class Controller {
    private final Snake snake;
    private final Field field;
    private final Scene scene;
    private final Level level;
    Fruits fruits;
    long currTime = System.currentTimeMillis();
    boolean flag = false;
    Controller(Scene scene, Snake snake, Field field, Level level, Fruits fruits) {
        this.snake = snake;
        this.field = field;
        this.scene = scene;
        this.level = level;
        this.fruits = fruits;
    }

    public void start() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if (!(snake.getMovement().x == 1)) {
                    snake.updateMovement(-1, 0);
                }
            }
            if (event.getCode() == KeyCode.RIGHT) {
                if (!(snake.getMovement().x == -1)) {
                    snake.updateMovement(1, 0);
                }
            }
            if (event.getCode() == KeyCode.UP) {
                if (!(snake.getMovement().y == 1)) {
                    snake.updateMovement(0, -1);
                }
            }
            if (event.getCode() == KeyCode.DOWN) {
                if (!(snake.getMovement().y == -1)) {
                    snake.updateMovement(0, 1);
                }
            }
        });

    }
    public void lose(Stage stage) {
        Platform.runLater(() -> {
            Stage popupStage = new Stage();
            popupStage.initOwner(stage);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Game Over");

            Label popupLabel = new Label("You lose!");
            popupLabel.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            popupLabel.setMinSize(120, 50);
            GridPane newS = new GridPane();
            newS.getChildren().addAll(popupLabel);
            Scene popupScene = new Scene(newS, 200, 100);
            popupStage.setScene(popupScene);

            popupStage.setAlwaysOnTop(true);
            popupStage.show();
        });
    }

    public void updateSnake() {
        if (System.currentTimeMillis() - currTime > snake.speed) {
            currTime = System.currentTimeMillis();
            Coordinates newHead = new Coordinates(snake.head.x + snake.movement.x, snake.head.y + snake.movement.y);
            snake.snakeBody.addFirst(newHead);
            snake.head = newHead;
            if (newHead.x < 0 || newHead.x == field.m || newHead.y < 0
                    || newHead.y == field.n
                    || field.getType(newHead) == Pixel.pixelType.WALL
                    || field.getType(newHead) == Pixel.pixelType.SNAKE) {
                System.out.println("you died!");
                flag = true;
                return;
            }
            if (level.victoryCheck(snake)) {
                System.out.println("You won!");
                flag = true;
                return;
            }

            if (field.getType(newHead) == Pixel.pixelType.FRUIT) {
                fruits.eatFruit(newHead, snake.color);
                snake.addLen();
            } else {
                field.setAsFree(snake.tail);
                snake.snakeBody.remove(snake.tail);
                snake.tail = snake.snakeBody.getLast();
            }

            field.setAsTaken(snake.head, snake.color);
        }
    }
}
