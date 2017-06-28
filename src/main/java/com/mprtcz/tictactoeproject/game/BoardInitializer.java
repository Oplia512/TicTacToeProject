package com.mprtcz.tictactoeproject.game;

/**
 * Created by Michal_Partacz on 28.06.2017.
 */
public class BoardInitializer {

    private int boardHeight = 0;
    private int boardWidth = 0;

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public BoardInitializer(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
    }

    public void initializeBoard(Board board) {
        board.boardArray = new Sign[this.boardHeight][this.boardWidth];
    }
}
