package com.mprtcz.tictactoeproject.ui_elements;

import com.mprtcz.tictactoeproject.game.Board;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class GameButton extends javafx.scene.control.Button {

    private ButtonPosition buttonPosition;
    private Board board;

    public GameButton(ButtonPosition buttonPosition, Board board) {
        this.buttonPosition = buttonPosition;
        this.board = board;
    }

    int getVerticalCoordinate() {
        return buttonPosition.getVertical();
    }

    int getHorizontalCoordinate() {
        return buttonPosition.getHorizontal();
    }

    static GameButton getGameButton(ButtonPosition buttonPosition, Board board) {
        GameButton gameButton = new GameButton(buttonPosition, board);
        gameButton.setText("   ");
        gameButton.setWidth(20);
        gameButton.setHeight(20);
        gameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO handling
            }
        });
        return gameButton;
    }

    static class ButtonPosition {
        int vertical;
        int horizontal;

        ButtonPosition(int vertical, int horizontal) {
            this.vertical = vertical;
            this.horizontal = horizontal;
        }

        int getVertical() {
            return vertical;
        }

        int getHorizontal() {
            return horizontal;
        }
    }
}
