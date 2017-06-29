package com.mprtcz.tictactoeproject.ui_elements;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
public class InputValidator {

    public boolean validateAnotherGameAnswer(String anotherGameAnswer) {
        if(anotherGameAnswer.toLowerCase().equals("y")) {
            return true;
        } else if (anotherGameAnswer.toLowerCase().equals("n")) {
            return false;
        } else {
            throw new MalformedParametersException("Please specify yes or no: y/n");

        }
    }
}
