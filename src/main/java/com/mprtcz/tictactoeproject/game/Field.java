package com.mprtcz.tictactoeproject.game;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class Field {
    private int xCoordinate;
    private int yCoordinate;

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void convertFieldIndexToCoordinates(int fieldIndex, int boardWidth) {
        xCoordinate = fieldIndex % boardWidth;
        yCoordinate = fieldIndex / boardWidth;
    }
}
