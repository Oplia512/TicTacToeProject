package com.mprtcz.tictactoeproject.game.board;

import com.mprtcz.tictactoeproject.game.Sign;

import java.lang.reflect.MalformedParametersException;

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

    public Sign getValueOnPosition(int height, int width) throws IndexOutOfBoundsException {
        try {
            return this.boardArray[height][width];
        } catch (IndexOutOfBoundsException e) {
            throw new MalformedParametersException("The board is not that big!");
        }
    }

    public String getFieldStringValue(int height, int width) {
        if (this.boardArray[height][width].getValue() == 0) {
            int fieldIndex = calculateFieldIndex(height, width);
            return formatNumberAccordingToBoardSize(fieldIndex);
        } else {
            return formatSignAccordingToBoardSize(this.boardArray[height][width]);
        }
    }

    public int calculateFieldIndex(int height, int width) {
        return this.boardArray[0].length * height + width;
    }

    private String formatNumberAccordingToBoardSize(int number) {
        int maxNumber = boardArray.length * boardArray[0].length;
        if (maxNumber > 100) {
            return String.format("%03d", number);
        } else if (maxNumber > 10) {
            return String.format("%02d", number);
        } else {
            return String.valueOf(number);
        }
    }

    private String formatSignAccordingToBoardSize(Sign sign) {
        int maxNumber = boardArray.length * boardArray[0].length;
        if (maxNumber > 100) {
            return " " +sign.getRepresentation() + " ";
        } else if (maxNumber > 10) {
            return sign.getRepresentation() + " ";
        } else {
            return sign.getRepresentation();
        }
    }

    public boolean isFieldEmpty(Field field) throws IndexOutOfBoundsException {
        Sign signOnPosition = getValueOnPosition(field.getYCoordinate(), field.getXCoordinate());
        return signOnPosition == Sign.EMPTY;
    }

}
