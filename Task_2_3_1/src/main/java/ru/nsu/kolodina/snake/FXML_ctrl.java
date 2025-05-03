package ru.nsu.kolodina.snake;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Controller class for handling the FXML layout of the Snake game.
 */
public class FXML_ctrl {

    @FXML
    private GridPane root;

    @FXML
    private Text gameText; // Matches fx:id="gameText" in FXML

    /**
     * Initializes the controller by setting the background of the game.
     * This method is called automatically after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        root.setBackground(new Background(
                new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)
        ));
    }
}
