package com.mprtcz.tictactoeproject.ui_elements;

import com.mprtcz.tictactoeproject.game.ExceptionsCreator;

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
            throw ExceptionsCreator.getInstance().createSpecifyYesNoException();
        }
    }
}
