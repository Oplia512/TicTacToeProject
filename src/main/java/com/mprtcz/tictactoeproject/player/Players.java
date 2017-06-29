package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.GameMode;
import com.mprtcz.tictactoeproject.game.Sign;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class Players {
    private GameMode gameMode;
    private Player player1;
    private Player player2;

    public Players(GameMode gameMode) {
        this.gameMode = gameMode;
        this.player1 = new Player("Player1", Sign.O);
        this.player2 = new Player("Player2", Sign.X);
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

    public Player getOppositePlayer(Player player) {
        if(player == player1) {
            return player2;
        } else {
            return player1;
        }
    }
}
