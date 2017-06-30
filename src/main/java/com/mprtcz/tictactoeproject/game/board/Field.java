package com.mprtcz.tictactoeproject.game.board;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class Field {
    private int xCoordinate;
    private int yCoordinate;

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void convertFieldIndexToCoordinates(int fieldIndex, int boardWidth) {
        xCoordinate = fieldIndex % boardWidth;
        yCoordinate = fieldIndex / boardWidth;
    }
}
