package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.game.board.Board;
import com.mprtcz.tictactoeproject.game.board.Field;

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
        } catch (NumberFormatException e) {
            throw new MalformedParametersException("A problem with field number conversion");
        }
        return field;
    }

    public void validateIfFieldIsTaken(Board board, Field chosenField) {
        if(!board.isFieldEmpty(chosenField)){
            throw new MalformedParametersException("Field is already taken!");
        }
    }
}
