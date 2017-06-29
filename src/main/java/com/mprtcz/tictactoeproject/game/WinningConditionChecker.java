package com.mprtcz.tictactoeproject.game;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public class WinningConditionChecker {
    public boolean checkIfTheGameIsWon(BoardManager boardManager, Field field) {
        return checkRow(getRow(boardManager, field)) || checkColumn(getColumn(boardManager, field));
        //TODO other winning conditions
    }

    private int[] getRow(BoardManager boardManager, Field field) {
        int rowIndex = field.getYCoordinate();
        return boardManager.getValuesInRow(rowIndex);
    }

    private int[] getColumn(BoardManager boardManager, Field field) {
        int columnIndex = field.getXCoordinate();
        return boardManager.getIntValuesInColumn(columnIndex);
    }

    private boolean checkRow(int[] row) {
        return checkArrayElementsEquality(row);
    }

    private boolean checkColumn(int[] column) {
        return checkArrayElementsEquality(column);
    }

    private boolean checkArrayElementsEquality(int[] array) {
        if(array[0] == 0) {
            return false;
        }
        int firstNumber = array[0];
        for (int i = 0; i < array.length; i++) {
            if(firstNumber != array[i]) {
                return false;
            }
        }
        return true;
    }
}
