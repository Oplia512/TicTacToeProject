package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.player.Player;
import com.mprtcz.tictactoeproject.ui_elements.BoardGUI;
import javafx.scene.layout.GridPane;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Board {
    Sign[][] boardArray;
    BoardGUI boardGUI;
    Player currentPlayer;

    public void setBoardArray(Sign[][] boardArray) {
        this.boardArray = boardArray;
    }

    public Sign[][] getBoardArray() {
        return boardArray;
    }

    public BoardGUI getBoardGUI() {
        return boardGUI;
    }

    public void setBoardGUI(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public void redraw(GridPane gridPane) {
        this.boardGUI.redraw(gridPane);
    }
}
