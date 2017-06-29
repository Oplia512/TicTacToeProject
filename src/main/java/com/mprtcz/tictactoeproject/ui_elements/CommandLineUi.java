package com.mprtcz.tictactoeproject.ui_elements;

import com.mprtcz.tictactoeproject.game.board.Board;
import com.mprtcz.tictactoeproject.player.Player;

import java.util.Scanner;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class CommandLineUi {
    private Scanner scanner;

    public CommandLineUi(Scanner scanner) {
        this.scanner = scanner;
    }

    public void drawBoard(Board board) {
        System.out.println("============BOARD=============");
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                System.out.print(" " +board.getFieldStringValue(i, j));
            }
            System.out.println("");
        }
        System.out.println("==============================");
    }

    public String getFieldFromTheUser(Player currentPlayer) {
        System.out.println("Type field number, " +currentPlayer.getName() +"! Sign: " +currentPlayer.getPlayerSign());
        return this.scanner.nextLine();
    }

    public String getArrayDimensions() {
        System.out.println("Pass on array dimensions in [height],[width] format");
        return scanner.nextLine();
    }

    public void communicateException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void displayWinningMessage(Player currentPlayer) {
        System.out.println("The game is won, winner: " +currentPlayer.getName()
                + ", sign: " +currentPlayer.getPlayerSign()
        + ", actual points: " +currentPlayer.getScore());
    }

    public String askForAnotherGame() {
        System.out.println("Do you want to play again? y/n");
         return scanner.nextLine();
    }
}
