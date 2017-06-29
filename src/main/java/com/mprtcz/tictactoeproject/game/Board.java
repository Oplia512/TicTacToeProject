package com.mprtcz.tictactoeproject.game;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Board {
    Sign[][] boardArray;

    public int getBoardHeight() {
        return boardArray.length;
    }

    public int getBoardWidth() {
        return boardArray[0].length;
    }

    public Sign getValueOnPosition(int height, int width) {
        return this.boardArray[height][width];
    }

    public String getFieldStringValue(int height, int width) {
        if (this.boardArray[height][width].getValue() == 0) {
            int fieldIndex = calculateFieldIndex(height, width);
            return formatNumberAccordingToMaxValue(boardArray.length * boardArray[0].length, fieldIndex);
        } else {
            return this.boardArray[height][width].getRepresentation();
        }
    }

    public int calculateFieldIndex(int height, int width) {
        return this.boardArray[0].length * height + width;
    }

    private String formatNumberAccordingToMaxValue(int maxNumber, int number) {
        if (maxNumber > 99) {
            return String.format("%03d", number);
        } else if (maxNumber > 9) {
            return String.format("%02d", number);
        } else {
            return String.valueOf(number);
        }
    }

}
