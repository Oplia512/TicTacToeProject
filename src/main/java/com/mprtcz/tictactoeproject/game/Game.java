package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.player.Players;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Game {
    private Board board;
    private Players players;
    private boolean running;

    public Game(Board board, Players players) {
        this.board = board;
        this.players = players;
    }

    void play() {}

    public boolean isRunning() {
        return running;
    }
}
