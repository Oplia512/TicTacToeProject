package com.mprtcz.tictactoeproject.controllers;

import com.mprtcz.tictactoeproject.game.Board;
import com.mprtcz.tictactoeproject.game.BoardInitializer;
import com.mprtcz.tictactoeproject.game.Game;
import com.mprtcz.tictactoeproject.player.Player;
import com.mprtcz.tictactoeproject.ui_elements.GameButton;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Michal_Partacz on 27.06.2017.
 */
public class Controller {
    Set<GameButton> gameButtons = new HashSet<>();
    Player player1 = new Player("Player name 1");
    Player player2 = new Player("Player name 2");
    int boardWidth = 0;
    int boardHeight = 0;

    void startGame() {
        Board board = new Board();
        BoardInitializer boardInitializer = new BoardInitializer(boardHeight, boardWidth);
        boardInitializer.initializeBoard(board);
        Game game = new Game(board, this.player1, this.player2);
    }


}
