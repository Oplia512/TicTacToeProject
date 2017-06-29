package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.ui_elements.BoardGUI;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Board {
    Sign[][] boardArray;
    BoardGUI boardGUI;

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
}
