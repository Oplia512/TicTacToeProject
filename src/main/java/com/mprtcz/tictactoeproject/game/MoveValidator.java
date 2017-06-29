package com.mprtcz.tictactoeproject.game;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class MoveValidator {
    public Field validateUserFieldInput(String userInput, Board board) {
        Field field = new Field();
        try {
            int numberChosen = Integer.valueOf(userInput);
            field.convertFieldIndexToCoordinates(numberChosen, board.getBoardWidth());
            //TODO deeper validation (if there are no collisions)
        } catch (NumberFormatException e) {
            throw new MalformedParametersException("A problem with field number conversion");
        }
        return field;
    }
}
