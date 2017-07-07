package com.mprtcz.tictactoeproject.game;

/**
 * Created by sergey on 07.07.17.
 */
public class GameModeValidator {

    private static GameMode currentGameMode;

    public static GameMode getCurrentGameMode() {
        return currentGameMode;
    }

    public static void validateAndSetCurrentGameMode(String mode) {
        switch (mode) {
            case "1":
                currentGameMode = GameMode.TWO_PLAYERS;
                break;
            case "2":
                currentGameMode = GameMode.TWO_PLAYERS;
                break;
            default:
                currentGameMode = GameMode.TWO_PLAYERS;
                break;
        }
    }
}
