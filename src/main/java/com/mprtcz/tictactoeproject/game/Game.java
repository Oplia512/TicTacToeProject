package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.player.Player;
import com.mprtcz.tictactoeproject.player.Players;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Game {
    private Board board;
    private Players players;
    private boolean running;
    private Player currentPlayer;

    public Game(Board board, Players players) {
        this.board = board;
        this.players = players;
    }

    void play() {
        this.currentPlayer = players.getPlayer1();
        boolean gameContinues = true;
        while (gameContinues) {

        }
    }

    public boolean isRunning() {
        return running;
    }
}
