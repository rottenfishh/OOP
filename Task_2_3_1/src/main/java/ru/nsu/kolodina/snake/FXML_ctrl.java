package ru.nsu.kolodina.snake;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FXML_ctrl {

    @FXML
    private GridPane root;
    @FXML
    private Text gameText; // Matches fx:id="gameText" in FXML

    @FXML
    public void initialize() {

        root.setBackground(new Background(
                new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)
        ));

    }

}
