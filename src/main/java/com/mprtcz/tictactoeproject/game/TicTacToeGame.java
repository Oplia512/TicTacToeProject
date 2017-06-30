package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.game.board.Board;
import com.mprtcz.tictactoeproject.game.board.BoardManager;
import com.mprtcz.tictactoeproject.game.board.Field;
import com.mprtcz.tictactoeproject.player.Player;
import com.mprtcz.tictactoeproject.player.TicTacToePlayers;
import com.mprtcz.tictactoeproject.ui_elements.CommandLineUi;

import java.lang.reflect.MalformedParametersException;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class TicTacToeGame implements Game {
    private Board board;
    private TicTacToePlayers ticTacToePlayers;
    private Player currentPlayer;
    private CommandLineUi commandLineUi;
    private BoardManager boardManager;
    private WinningConditionChecker winningConditionChecker;
    private MoveValidator moveValidator;
    private boolean isGameWon;


    public TicTacToeGame(Board board,
                         TicTacToePlayers ticTacToePlayers,
                         CommandLineUi commandLineUi,
                         MoveValidator moveValidator,
                         WinningConditionChecker winningConditionChecker,
                         BoardManager boardManager) {
        this.commandLineUi = commandLineUi;
        this.board = board;
        this.ticTacToePlayers = ticTacToePlayers;
        this.moveValidator = moveValidator;
        this.boardManager = boardManager;
        this.winningConditionChecker = winningConditionChecker;
    }

    @Override
    public void play() {
        this.currentPlayer = ticTacToePlayers.getPlayer1();
        isGameWon = false;
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
                this.currentPlayer = ticTacToePlayers.getOppositePlayer(this.currentPlayer);
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
