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

    public GameButton(ButtonPosition buttonPosition, String text) {
        this.setText(text);
        this.buttonPosition = buttonPosition;
    }

    public int getVerticalCoordinate() {
        return buttonPosition.getVertical();
    }

    public int getHorizontalCoordinate() {
        return buttonPosition.getHorizontal();
    }

    public static GameButton getGameButton(ButtonPosition buttonPosition, String s) {
        GameButton gameButton = new GameButton(buttonPosition, s);
        gameButton.setWidth(20);
        gameButton.setHeight(20);
        return gameButton;
    }

    static class ButtonPosition {
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
