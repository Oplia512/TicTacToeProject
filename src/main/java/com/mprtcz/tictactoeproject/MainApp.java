package com.mprtcz.tictactoeproject;

import com.mprtcz.tictactoeproject.game.*;
import com.mprtcz.tictactoeproject.player.Players;
import com.mprtcz.tictactoeproject.ui_elements.CommandLineUi;
import com.mprtcz.tictactoeproject.ui_elements.InputValidator;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
public class MainApp {

    public static void main(String[] args) {
        boolean mainLoopRunning = true;
        Players players = new Players(GameMode.TWO_PLAYERS);
        while(mainLoopRunning) {
            CommandLineUi commandLineUi = new CommandLineUi();
            boolean areDimensionsOK = false;
            BoardValidator boardValidator = new BoardValidator();
            BoardSize boardSize = null;
            Board board = new Board();
            while (!areDimensionsOK) {
                String dimensionsString = commandLineUi.getArrayDimensions();
                try {
                    boardSize = boardValidator.convertAndValidateDimensions(dimensionsString);
                    BoardInitializer boardInitializer = new BoardInitializer(boardSize);
                    boardInitializer.initializeBoard(board);
                    areDimensionsOK = true;
                } catch (MalformedParametersException e) {
                    commandLineUi.communicateException(e);
                }
            }

            Game game = new Game(board, players, commandLineUi);
            game.play();

            boolean isNextAnswerBad = false;
            while(!isNextAnswerBad) {
                String anotherGameAnswerString = commandLineUi.askForAnotherGame();
                InputValidator inputValidator = new InputValidator();
                try {
                    mainLoopRunning = inputValidator.validateAnotherGameAnswer(anotherGameAnswerString);
                    isNextAnswerBad = true;
                } catch (MalformedParametersException e) {
                    commandLineUi.communicateException(e);
                }
            }
        }
        System.out.println("Hope you enjoyed the game, please like and subscribe for more content!");
    }
}
