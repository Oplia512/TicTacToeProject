package com.mprtcz.tictactoeproject.game;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class BoardInitializer {
    private static int MIN_VALUE = 2;
    private static int MAX_VALUE = 20;

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

    public BoardInitializer(BoardSize boardSize) {
        System.out.println("boardSize = " + boardSize);
        this.boardHeight = boardSize.getHeight();
        this.boardWidth = boardSize.getWidth();
    }

    public void initializeBoard(Board board) throws MalformedParametersException {
        if (this.boardWidth < MIN_VALUE || this.boardWidth > MAX_VALUE || this.boardHeight > MAX_VALUE || this.boardHeight < MIN_VALUE) {
            throw new MalformedParametersException("Parameters exceed acceptable parameters: min = " + MIN_VALUE + ", max = " + MAX_VALUE);
        }
        board.boardArray = new Sign[this.boardHeight][this.boardWidth];
        for (int i = 0; i < this.boardHeight; i++) {
            for (int j = 0; j < this.boardWidth; j++) {
                board.boardArray[i][j] = Sign.EMPTY;
            }
        }
    }
}
