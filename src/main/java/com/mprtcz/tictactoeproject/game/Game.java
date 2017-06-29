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
        BoardManager boardManager = new BoardManager(this.board);
        commandLineUi.drawBoard(this.board);
        while (!isGameWon) {
            commandLineUi.drawBoard(this.board);
            String userInput = commandLineUi.getFieldFromTheUser();
            try {
                Field chosenField = moveValidator.validateUserFieldInput(userInput, this.board);
                boardManager.updateBoard(chosenField, currentPlayer);
                isGameWon = winningConditionChecker.checkIfTheGameIsWon(board);
            } catch (MalformedParametersException e) {
                commandLineUi.communicateBadFieldChosen();
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
        while(!areDimensionsOK) {
            String dimensionsString = commandLineUi.getArrayDimensions();
            try{
                boardSize = boardValidator.convertAndValidateDimensions(dimensionsString);
                areDimensionsOK = true;
            } catch (MalformedParametersException e) {
                commandLineUi.communicateBadDimensionsTyped();
            }
        }
        Board board = new Board();
        BoardInitializer boardInitializer = new BoardInitializer(boardSize);
        boardInitializer.initializeBoard(board);

        Players players = new Players(GameMode.ONE_PLAYER);

        Game game = new Game(board, players, commandLineUi);
        game.play();
    }
}
