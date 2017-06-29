package com.mprtcz.tictactoeproject.ui_elements;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class BoardGUI {
    private List<GameButton> gameButtons;

    public BoardGUI(int width, int height) {
        this.gameButtons = new ArrayList<>();
        populateButtonsList(width, height);
    }

    private void populateButtonsList(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                GameButton gameButton = GameButton.getGameButton(new GameButton.ButtonPosition(i, j), " ");
                this.gameButtons.add(gameButton);
            }
        }
    }

    public void drawButtons(GridPane gridPane) {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        for (GameButton gameButton: this.gameButtons) {
            gameButton.setMaxSize(100, 100);
            gridPane.add(gameButton, gameButton.getHorizontalCoordinate(), gameButton.getVerticalCoordinate());
        }

    }
}
