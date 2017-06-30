package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.game.*;
import com.mprtcz.tictactoeproject.game.board.*;
import com.mprtcz.tictactoeproject.player.Players;
import com.mprtcz.tictactoeproject.ui_elements.CommandLineUi;
import com.mprtcz.tictactoeproject.ui_elements.InputValidator;

import java.lang.reflect.MalformedParametersException;
import java.util.Scanner;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
public class MainApp {

    public static void main(String[] args) {
        boolean mainLoopRunning = true;
        Players players = new Players(GameMode.TWO_PLAYERS);
        while(mainLoopRunning) {
            CommandLineUi commandLineUi = new CommandLineUi(new Scanner(System.in));
            Board board = validateAndInitializeBoard(commandLineUi);
            Game game = new Game(board, players, commandLineUi,
                    new MoveValidator(), new WinningConditionChecker(), new BoardManager(board));
            game.play();
            mainLoopRunning = processNextGameAnswer(commandLineUi);
        }
        System.out.println("Hope you enjoyed the game, please like and subscribe for more content!\n" +
                "And stay tuned for the glorious Graphical User Interface!");
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
}
