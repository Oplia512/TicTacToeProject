package com.mprtcz.tictactoeproject.ui_elements;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class GameButton extends javafx.scene.control.Button {

    private ButtonPosition buttonPosition;

    public GameButton(ButtonPosition buttonPosition) {
        this.buttonPosition = buttonPosition;
    }

    class ButtonPosition {
        int vertical;
        int horizontal;

        public ButtonPosition(int vertical, int horizontal) {
            this.vertical = vertical;
            this.horizontal = horizontal;
        }

        public int getVertical() {
            return vertical;
        }

        public int getHorizontal() {
            return horizontal;
        }
    }
}
