package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.GameMode;
import com.mprtcz.tictactoeproject.game.Sign;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class TicTacToePlayers implements Players {
    private GameMode gameMode;
    private TicTacToePlayer Player1;
    private TicTacToePlayer Player2;

    public TicTacToePlayers(GameMode gameMode) {
        this.gameMode = gameMode;
        this.Player1 = new TicTacToePlayer("Player1", Sign.O);
        this.Player2 = new TicTacToePlayer("Player2", Sign.X);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public Player getPlayer1() {
        return Player1;
    }

    @Override
    public Player getPlayer2() {
        return Player2;
    }

    @Override
    public Player getOppositePlayer(Player player) {
        if (player == Player1) {
            return Player2;
        } else {
            return Player1;
        }
    }

    public void reversePlayersSigns() {
        this.Player1.setSign(Sign.getOppositeSign((Sign) this.Player1.getGameElement()));
        this.Player2.setSign(Sign.getOppositeSign((Sign) this.Player2.getGameElement()));
    }
}
