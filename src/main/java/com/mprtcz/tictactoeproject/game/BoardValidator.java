package com.mprtcz.tictactoeproject.game;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class BoardValidator {


    public BoardSize convertAndValidateDimensions(String dimensionsString) {
        String[] parts = dimensionsString.split(",");
        String heightString = parts[0];
        String widthString = parts[1];

        BoardSize boardSize;
        try {
            Integer height = Integer.valueOf(heightString);
            Integer width = Integer.valueOf(widthString);
            boardSize = new BoardSize(width, height);
        } catch (NumberFormatException e) {
            throw new MalformedParametersException("String int conversion failed");
        }
        return boardSize;
    }
}
