package com.mprtcz.tictactoeproject.ui_elements;

import javafx.scene.control.TextField;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class PlayerNamesDialog {
    private String firstPlayerName;
    private String secondPlayerName;

    public PlayerNamesDialog(TextField firstPlayerTextField, TextField secondPlayerTextField) {
        this.firstPlayerName = firstPlayerTextField.getText();
        this.secondPlayerName = secondPlayerTextField.getText();
    }


    public String getFirstPlayerName() {
        if(this.firstPlayerName.isEmpty()) {
            return "Player1";
        }
        return this.firstPlayerName;
    }

    public String getSecondPlayerName() {
        if(this.secondPlayerName.isEmpty()) {
            return "Player2";
        }
        return this.secondPlayerName;
    }
}
