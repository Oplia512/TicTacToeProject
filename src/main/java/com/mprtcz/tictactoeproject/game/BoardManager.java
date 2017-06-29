package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.player.Player;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class BoardManager {
    private Board board;

    public BoardManager(Board board) {
        this.board = board;
    }

    public void updateBoard(Field chosenField, Player currentPlayer) {
        this.board.boardArray[chosenField.getyCoordinate()][chosenField.getxCoordinate()] = currentPlayer.getPlayerSign();
    }
}
