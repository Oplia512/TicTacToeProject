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
    private BoardManager boardManager;
    private WinningConditionChecker winningConditionChecker;
    private MoveValidator moveValidator;
    private boolean isGameWon;


    public Game(Board board,
                Players players,
                CommandLineUi commandLineUi,
                MoveValidator moveValidator,
                WinningConditionChecker winningConditionChecker,
                BoardManager boardManager) {
        this.commandLineUi = commandLineUi;
        this.board = board;
        this.players = players;
        this.moveValidator = moveValidator;
        this.boardManager = boardManager;
        this.winningConditionChecker = winningConditionChecker;
    }

    public void play() {
        this.currentPlayer = players.getPlayer1();
        isGameWon = false;
        commandLineUi.drawBoard(this.board);
        while (!isGameWon) {
            commandLineUi.drawBoard(this.board);
            String userInput = commandLineUi.getFieldFromTheUser(this.currentPlayer);
            try {
                handleFieldChoosingAndCheckWinner(userInput);
            } catch (MalformedParametersException e) {
                commandLineUi.communicateException(e);
                continue;
            } catch (IllegalStateException e) {
                commandLineUi.drawBoard(this.board);
                commandLineUi.communicateException(e);
                break;
            }
            if(!isGameWon) {
                this.currentPlayer = players.getOppositePlayer(this.currentPlayer);
            } else {
                this.handleWinningConditions();
            }
        }
    }

    private void handleWinningConditions() {
        commandLineUi.drawBoard(this.board);
        currentPlayer.increasePoints();
        commandLineUi.displayWinningMessage(currentPlayer);
    }

    private void handleFieldChoosingAndCheckWinner(String userInput)
            throws MalformedParametersException, IllegalStateException {
        Field chosenField = moveValidator.validateUserFieldInput(userInput, this.board);
        moveValidator.validateIfFieldIsTaken(this.board, chosenField);
        boardManager.updateBoard(chosenField, currentPlayer);
        isGameWon = winningConditionChecker.checkIfTheGameIsWon(boardManager, chosenField);
    }


}
