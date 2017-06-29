package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.player.Player;
import com.mprtcz.tictactoeproject.player.Players;
import com.mprtcz.tictactoeproject.ui_elements.CommandLineUi;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Game {
    private Board board;
    private Players players;
    private Player currentPlayer;
    private CommandLineUi commandLineUi;

    public Game(Board board, Players players, CommandLineUi commandLineUi) {
        this.commandLineUi = commandLineUi;
        this.board = board;
        this.players = players;
    }

    void play() {
        this.currentPlayer = players.getPlayer1();
        boolean isGameWon = false;
        this.commandLineUi = new CommandLineUi();
        MoveValidator moveValidator = new MoveValidator();
        WinningConditionChecker winningConditionChecker = new WinningConditionChecker();
        BoardValidator boardValidator = new BoardValidator();
        BoardManager boardManager = new BoardManager(this.board);
        commandLineUi.drawBoard(this.board);
        while (!isGameWon) {
            commandLineUi.drawBoard(this.board);
            String userInput = commandLineUi.getFieldFromTheUser();
            try {
                Field chosenField = moveValidator.validateUserFieldInput(userInput, this.board);
                moveValidator.validateIfFieldIsTaken(this.board, chosenField);
                boardManager.updateBoard(chosenField, currentPlayer);
                isGameWon = winningConditionChecker.checkIfTheGameIsWon(board);
            } catch (MalformedParametersException e) {
                commandLineUi.communicateException(e);
                continue;
            }
            // switch players
            this.currentPlayer = players.getOppositePlayer(this.currentPlayer);
        }
    }

    public static void main(String[] args) {
        CommandLineUi commandLineUi = new CommandLineUi();
        boolean areDimensionsOK = false;
        BoardValidator boardValidator = new BoardValidator();
        BoardSize boardSize = null;
        Board board = new Board();
        while(!areDimensionsOK) {
            String dimensionsString = commandLineUi.getArrayDimensions();
            try{
                boardSize = boardValidator.convertAndValidateDimensions(dimensionsString);
                BoardInitializer boardInitializer = new BoardInitializer(boardSize);
                boardInitializer.initializeBoard(board);
                areDimensionsOK = true;
            } catch (MalformedParametersException e) {
                commandLineUi.communicateException(e);
            }
        }

        Players players = new Players(GameMode.TWO_PLAYERS);

        Game game = new Game(board, players, commandLineUi);
        game.play();
    }
}
