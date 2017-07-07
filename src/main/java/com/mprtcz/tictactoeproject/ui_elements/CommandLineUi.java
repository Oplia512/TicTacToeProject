package com.mprtcz.tictactoeproject.ui_elements;

import com.mprtcz.tictactoeproject.game.GameMode;
import com.mprtcz.tictactoeproject.game.board.Board;
import com.mprtcz.tictactoeproject.player.Player;
import com.mprtcz.tictactoeproject.utils.LocaleManager;
import com.mprtcz.tictactoeproject.utils.MessagesWrapper;

import java.util.Scanner;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class CommandLineUi implements NetCommandLine{
    private MessagesWrapper messagesWrapper;
    private Scanner scanner;

    public CommandLineUi(Scanner scanner) {
        this.scanner = scanner;
        this.messagesWrapper = new MessagesWrapper();
    }

    public void updateCommandLineLocale() {
        this.messagesWrapper = new MessagesWrapper();
    }

    public void drawBoard(Board board) {
        System.out.println(String.format("============%s===========", messagesWrapper.getString("BOARD_HEADER")));
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                System.out.print(" " + board.getFieldStringValue(i, j));
            }
            System.out.println("");
        }
        System.out.println("==============================");
    }

    public String getFieldFromTheUser(Player currentPlayer) {
        System.out.println(String.format(messagesWrapper.getString("TYPE_FIELD_NUMBER"), currentPlayer.getName(), currentPlayer.getGameElement()));
        return this.scanner.nextLine();
    }

    public String getArrayDimensions() {
        System.out.println(messagesWrapper.getString("PASS_ARRAY_DIMENSIONS_MESSAGE"));
        return scanner.nextLine();
    }

    public void communicateException(Exception e) {
        System.err.println(e.getMessage());
    }

    public void displayWinningMessage(Player currentPlayer) {
        System.out.println(String.format(messagesWrapper.getString("DISPLAY_GAME_WINNER"),
                currentPlayer.getName(),
                currentPlayer.getGameElement(),
                currentPlayer.getScore()));
    }

    public String askForAnotherGame() {
        System.out.println(messagesWrapper.getString("PLAY_AGAIN_QUESTION"));
        return scanner.nextLine();
    }

    public String getUserLocaleInput(LocaleManager.AvailableLocale[] values) {
        System.out.println(messagesWrapper.getString("CHOOSE_LANGUAGE"));
        for (LocaleManager.AvailableLocale value : values) {
            System.out.println(String.format(messagesWrapper.getString("TYPE_A_TWO_PARAM_INPUT"), value.getNameShort(), value.getLocaleName()));
        }
        return scanner.nextLine();
    }

    public String askForGameMode(){
        System.out.println(messagesWrapper.getString("CHOOSE_GAME_MODE"));
        for (GameMode mode : GameMode.values()) {
            System.out.println(String.format(messagesWrapper.getString("TYPE_A_TWO_PARAM_INPUT"), mode.getMarker(), mode.getName()));
        }
        return scanner.nextLine();
    }
}
