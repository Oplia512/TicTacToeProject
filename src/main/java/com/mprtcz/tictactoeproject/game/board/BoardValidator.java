package com.mprtcz.tictactoeproject.game.board;

import com.mprtcz.tictactoeproject.game.ExceptionsCreator;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class BoardValidator {

    public BoardSize convertAndValidateDimensions(String dimensionsString) {
        String[] parts = dimensionsString.split(",");
        String heightString;
        String widthString;
        try {
            heightString = parts[0];
            widthString = parts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ExceptionsCreator.getInstance().createIncorrectBoardSizeException();
        }

        BoardSize boardSize;
        try {
            Integer height = Integer.valueOf(heightString);
            Integer width = Integer.valueOf(widthString);
            boardSize = new BoardSize(width, height);
        } catch (NumberFormatException e) {
            throw ExceptionsCreator.getInstance().createIncorrectDimensionsException();
        }
        return boardSize;
    }
}
