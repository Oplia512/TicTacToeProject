package com.mprtcz.tictactoeproject.game;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Board {
    Sign[][] boardArray;

    public void setBoardArray(Sign[][] boardArray) {
        this.boardArray = boardArray;
    }

    public Sign[][] getBoardArray() {
        return boardArray;
    }

}
