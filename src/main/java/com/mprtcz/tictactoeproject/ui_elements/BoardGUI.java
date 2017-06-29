package com.mprtcz.tictactoeproject.ui_elements;

import com.mprtcz.tictactoeproject.game.Board;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class BoardGUI {
    private List<GameButton> gameButtons;
    private Board board;

    public BoardGUI(int width, int height, Board board) {
        this.gameButtons = new ArrayList<>();
        this.board = board;
        populateButtonsList(width, height);
    }

    private void populateButtonsList(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                GameButton gameButton = GameButton.getGameButton(new GameButton.ButtonPosition(i, j), this.board);
                this.gameButtons.add(gameButton);
            }
        }
    }

    public void drawButtons(GridPane gridPane) {
        gridPane.getChildren().clear();
        for (GameButton gameButton: this.gameButtons) {
            gridPane.add(gameButton, gameButton.getHorizontalCoordinate(), gameButton.getVerticalCoordinate());
        }
    }

    public void redraw(GridPane gridPane) {
        System.out.println("RedrawingBoard");
        drawButtons(gridPane);
    }
}
