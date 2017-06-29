package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.GameMode;
import com.mprtcz.tictactoeproject.ui_elements.PlayerNamesDialog;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class Players {
    private GameMode gameMode;
    private Player player1;
    private Player player2;

    public Players(GameMode gameMode, PlayerNamesDialog playerNamesDialog) {
        this.gameMode = gameMode;
        this.player1 = new Player(playerNamesDialog.getFirstPlayerName());
        this.player2 = new Player(playerNamesDialog.getSecondPlayerName());
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
