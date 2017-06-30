package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.utils.MessagesWrapper;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 30.06.2017.
 */
public class ExceptionsCreator {
    private MessagesWrapper messagesWrapper;
    private static ExceptionsCreator exceptionsCreator;

    private ExceptionsCreator() {
        this.messagesWrapper = new MessagesWrapper();
    }

    public static ExceptionsCreator getInstance() {
        if (exceptionsCreator == null) {
            exceptionsCreator = new ExceptionsCreator();
        }
        return exceptionsCreator;
    }

    public IllegalStateException createBoardFullException() {
        return new IllegalStateException(
                messagesWrapper.getString("BOARD_FULL_EXCEPTION_MESSAGE"));
    }

    public MalformedParametersException createFieldNumberConversionException() {
        return new MalformedParametersException
                (messagesWrapper.getString("NUMBER_FIELD_CONVERSION_EXCEPTION_MESSAGE"));
    }

    public MalformedParametersException createFieldTakenException() {
        return new MalformedParametersException(
                messagesWrapper.getString("FIELD_TAKEN_EXCEPTION_MESSAGE"));
    }

    public MalformedParametersException createBoardTooSmallException() {
        return new MalformedParametersException(
                messagesWrapper.getString("BOARD_NOT_THAT_BIG"));
    }

    public MalformedParametersException createIncorrectBoardSizeException() {
        return new MalformedParametersException(
                messagesWrapper.getString("SPECIFY_CORRECT_BOARD_SIZE_EXCEPTION_MESSAGE"));
    }

    public MalformedParametersException createIncorrectDimensionsException() {
        return new MalformedParametersException(
                messagesWrapper.getString("INCORRECT_BOARD_DIMENSIONS_EXCEPTION_MESSAGE"));
    }

    public MalformedParametersException createSpecifyYesNoException() {
        return new MalformedParametersException(
                messagesWrapper.getString("PLEASE_SPECIFY_YES_NO"));
    }

    public MalformedParametersException createBoardDimensionsExceedException(int min, int max) {
        throw new MalformedParametersException(
                String.format(
                        messagesWrapper.getString("BOARD_PARAMS_EXCEED_ACCEPTABLE_EXCEPTION_MESSAGE"), min, max));
    }
}
