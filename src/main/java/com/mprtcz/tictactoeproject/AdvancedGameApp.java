package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.game.*;
import com.mprtcz.tictactoeproject.game.board.*;
import com.mprtcz.tictactoeproject.message.EventObserver;
import com.mprtcz.tictactoeproject.net.ConnectionProvider;
import com.mprtcz.tictactoeproject.player.TicTacToePlayers;
import com.mprtcz.tictactoeproject.ui_elements.CommandLineUi;
import com.mprtcz.tictactoeproject.ui_elements.InputValidator;
import com.mprtcz.tictactoeproject.utils.LocaleManager;

import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by sergey on 07.07.17.
 */
public class AdvancedGameApp {

    public static void main(String[] args) {
        boolean mainLoopRunning = true;
        EventObserver.init();

        CommandLineUi commandLineUi = new CommandLineUi(new Scanner(System.in));
        chooseLocale(commandLineUi);
        chooseGameMode(commandLineUi);

        TicTacToePlayers ticTacToePlayers = new TicTacToePlayers(GameModeValidator.getCurrentGameMode());

        switch (GameModeValidator.getCurrentGameMode()){
            case TWO_PLAYERS:
                while(mainLoopRunning) {
                    Board board = validateAndInitializeBoard(commandLineUi);
                    Game ticTacToeGame = new TicTacToeGame(board, ticTacToePlayers, commandLineUi,
                            new MoveValidator(), new WinningConditionChecker(), new BoardManager(board));
                    ticTacToeGame.play();
                    mainLoopRunning = processNextGameAnswer(commandLineUi);
                    if(mainLoopRunning) {
                        ticTacToePlayers.reversePlayersSigns();
                    }
                }
                break;
            case NET_GAME:

                ConnectionProvider connectionProvider = new ConnectionProvider();

                EventObserver.getInstance().addObserver(connectionProvider);


                try {
                    connectionProvider.initProvider(InetAddress.getLocalHost(), InetAddress.getLocalHost());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        System.out.println("Hope you enjoyed the game, please like and subscribe for more content!\n" +
                "And stay tuned for the glorious Graphical User Interface! (Coming Soon!)");
    }

    private static Board validateAndInitializeBoard(CommandLineUi commandLineUi) {
        BoardValidator boardValidator = new BoardValidator();
        BoardSize boardSize;
        Board board = new Board();
        while (true) {
            String dimensionsString = commandLineUi.getArrayDimensions();
            try {
                boardSize = boardValidator.convertAndValidateDimensions(dimensionsString);
                BoardInitializer boardInitializer = new BoardInitializer(boardSize);
                boardInitializer.initializeBoard(board);
                return board;
            } catch (MalformedParametersException e) {
                commandLineUi.communicateException(e);
            }
        }
    }

    private static boolean processNextGameAnswer(CommandLineUi commandLineUi) {
        while(true) {
            String anotherGameAnswerString = commandLineUi.askForAnotherGame();
            InputValidator inputValidator = new InputValidator();
            try {
                return inputValidator.validateAnotherGameAnswer(anotherGameAnswerString);
            } catch (MalformedParametersException e) {
                commandLineUi.communicateException(e);
            }
        }
    }

    private static void chooseLocale(CommandLineUi commandLineUi) {
        String userLocaleInput = commandLineUi.getUserLocaleInput(LocaleManager.AvailableLocale.values());
        LocaleManager.validateInputAndSetLocale(userLocaleInput);
        commandLineUi.updateCommandLineLocale();
    }

    private static void chooseGameMode(CommandLineUi commandLineUi) {
        String gameModeInput = commandLineUi.askForGameMode();
        GameModeValidator.validateAndSetCurrentGameMode(gameModeInput);
    }
}
