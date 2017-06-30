package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.game.board.Board;
import com.mprtcz.tictactoeproject.game.board.Field;

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
            throw ExceptionsCreator.getInstance().createFieldNumberConversionException();
        }
        return field;
    }

    public void validateIfFieldIsTaken(Board board, Field chosenField) {
        if(!board.isFieldEmpty(chosenField)){
            throw ExceptionsCreator.getInstance().createFieldTakenException();
        }
    }
}
