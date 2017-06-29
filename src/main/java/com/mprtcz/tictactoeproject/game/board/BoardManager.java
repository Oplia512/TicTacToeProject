package com.mprtcz.tictactoeproject.game.board;

import com.mprtcz.tictactoeproject.game.Field;
import com.mprtcz.tictactoeproject.game.Sign;
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
        this.board.boardArray[chosenField.getYCoordinate()][chosenField.getXCoordinate()] = currentPlayer.getPlayerSign();
    }

    public boolean isThereAnEmptyPlaceInTheArray() {
        for (Sign[] signs: board.boardArray) {
            for (Sign s : signs) {
                if(s.getValue() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getValuesInRow(int rowIndex) {
        return convertSignArrayToIntArray(this.board.boardArray[rowIndex]);
    }

    public int[] getIntValuesInColumn(int columnIndex) {
        int[] column = new int[this.board.boardArray.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = this.board.boardArray[i][columnIndex].getValue();
        }
        return column;
    }

    private int[] convertSignArrayToIntArray(Sign[] signArray) {
        int[] intArray = new int[signArray.length];
        for (int i = 0; i < signArray.length; i++) {
            intArray[i] = signArray[i].getValue();
        }
        return intArray;
    }
}
