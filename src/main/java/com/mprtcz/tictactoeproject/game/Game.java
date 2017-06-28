package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.player.Player;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Game {
    Board board;
    private Player player1;
    private Player player2;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    void play() {}
}
