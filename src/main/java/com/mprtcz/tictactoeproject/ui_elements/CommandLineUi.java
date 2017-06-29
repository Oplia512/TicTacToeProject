package com.mprtcz.tictactoeproject.ui_elements;

import com.mprtcz.tictactoeproject.game.Board;

import java.util.Scanner;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class CommandLineUi {

    private String fieldFromTheUser;
    private String arrayDimensions;
    private Scanner scanner;

    public CommandLineUi() {
        this.scanner = new Scanner(System.in);
    }

    public void drawBoard(Board board) {
        System.out.println("==============================");
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                System.out.print(" " +board.getFieldStringValue(i, j));
            }
            System.out.println("");
        }
        System.out.println("==============================");
    }

    public String getFieldFromTheUser() {
        System.out.println("Type field number:" );
        return this.scanner.nextLine();
    }

    public void communicateBadFieldChosen() {
        System.out.println("Invalid field chosen");
    }

    public String getArrayDimensions() {
        System.out.println("Pass on array dimensions in [height],[width] format");
        return scanner.nextLine();
    }

    public void communicateBadDimensionsTyped() {
        System.out.println("Dimensions specified are bad");
    }
}
