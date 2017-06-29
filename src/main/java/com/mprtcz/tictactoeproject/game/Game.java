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

    public void play() {
        this.currentPlayer = players.getPlayer1();
        boolean isGameWon = false;
        this.commandLineUi = new CommandLineUi();
        MoveValidator moveValidator = new MoveValidator();
        WinningConditionChecker winningConditionChecker = new WinningConditionChecker();
        BoardManager boardManager = new BoardManager(this.board);
        commandLineUi.drawBoard(this.board);
        while (!isGameWon) {
            commandLineUi.drawBoard(this.board);
            String userInput = commandLineUi.getFieldFromTheUser(this.currentPlayer);
            try {
                Field chosenField = moveValidator.validateUserFieldInput(userInput, this.board);
                moveValidator.validateIfFieldIsTaken(this.board, chosenField);
                boardManager.updateBoard(chosenField, currentPlayer);
                isGameWon = winningConditionChecker.checkIfTheGameIsWon(boardManager, chosenField);
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
                commandLineUi.drawBoard(this.board);
                currentPlayer.increasePoints();
                commandLineUi.displayWinningMessage(currentPlayer);
            }
        }
    }


}
