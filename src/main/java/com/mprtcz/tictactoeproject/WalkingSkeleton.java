package com.mprtcz.tictactoeproject;

import java.util.Scanner;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class WalkingSkeleton {

    public static void main(String[] args) {
        int[][] board = new int[5][3];
        System.out.println("the board is ");
        drawABoard(board);
        boolean gameRunning = true;
        int currentPlayer = 1;
        while (gameRunning) {

            String typedInCoordinates = getCoordinatesFromTheUser(currentPlayer);
            if (canBePlaced(board, getFirstCoordinate(typedInCoordinates), getSecondCoordinate(typedInCoordinates))) {
                board[getFirstCoordinate(typedInCoordinates)][getSecondCoordinate(typedInCoordinates)] = currentPlayer;
                drawABoard(board);
            } else {
                System.out.println("Cannot be placed there, try again");
                continue;
            }
            gameRunning = !isWinner(board, getFirstCoordinate(typedInCoordinates), getSecondCoordinate(typedInCoordinates));
            if (gameRunning) {
                currentPlayer = switchPlayer(currentPlayer);
            }
        }
        System.out.println("Game was won by " + translateSignToString(currentPlayer));
    }

    static int switchPlayer(int playerSign) {
        return playerSign == 1 ? -1 : 1;
    }

    static void drawABoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(translateSignToString(board[i][j]));
            }
            System.out.println("");
        }
        System.out.println("");
    }

    static boolean isWinner(int[][] board, int lastFirstCoordinate, int lastSecondCoordinate) {
        if (summarizeRow(board, lastFirstCoordinate) == Math.abs(board[0].length) || summarizeColumn(board, lastSecondCoordinate) == Math.abs(board.length)) {
            return true;
        }
        //TODO other types of winning conditions
        return false;
    }

    static int summarizeRow(int[][] board, int rowIndex) {
        int[] row = board[rowIndex];
        int sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum += row[i];
        }
        return sum;
    }

    static int summarizeColumn(int[][] board, int columnIndex) {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            sum += board[i][columnIndex];
        }
        return sum;
    }

    static boolean canBePlaced(int[][] board, int firstCoordinate, int secondCoordinate) {
        System.out.println("Coordinates = " + firstCoordinate + " " + secondCoordinate);
        if (board.length <= firstCoordinate || board[0].length <= secondCoordinate) {
            return false;
        } else if (firstCoordinate < 0 || secondCoordinate < 0) {
            return false;
        }
        return board[firstCoordinate][secondCoordinate] == 0;
    }

    static String getCoordinatesFromTheUser(int sign) {
        System.out.println("Select a place on board for a" + translateSignToString(sign) + " type coordinates separated by a space");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static int getFirstCoordinate(String coordinates) {
        return parseANumberByItsOrder(coordinates, 0);
    }

    static int getSecondCoordinate(String coordinates) {
        return parseANumberByItsOrder(coordinates, 1);
    }

    static int parseANumberByItsOrder(String string, int place) {
        String first = string.split(" ")[place];
        return Integer.parseInt(first);
    }

    static String translateSignToString(int sign) {
        if (sign == 1) {
            return "O";
        } else if (sign == -1) {
            return "X";
        } else {
            return ".";
        }
    }
}
