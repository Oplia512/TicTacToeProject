package com.mprtcz.tictactoeproject.controllers;

import com.mprtcz.tictactoeproject.game.Board;
import com.mprtcz.tictactoeproject.game.BoardInitializer;
import com.mprtcz.tictactoeproject.game.Game;
import com.mprtcz.tictactoeproject.game.GameMode;
import com.mprtcz.tictactoeproject.player.Players;
import com.mprtcz.tictactoeproject.ui_elements.GameButton;
import com.mprtcz.tictactoeproject.ui_elements.PlayerNamesDialog;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michal_Partacz
 * @since 27.06.2017.
 */
public class Controller {
    private Set<GameButton> gameButtons = new HashSet<>();
    private Players players;
    private int boardWidth = 0;
    private int boardHeight = 0;
    private GameMode gameMode;
    private PlayerNamesDialog playerNamesDialog;

    public ListView playersListView;
    public Button startGameButton;
    public Label currentPlayerLabel;
    public Pane buttonPane;
    public Label playersLabel;
    public BorderPane borderPane;
    public VBox choosePlayerNamesVBox;
    public VBox chooseOpponentVBox;
    public TextField firstPlayerTextField;
    public TextField secondPlayerTextField;

    public Controller() {
    }


    public void onStartGameButtonClicked() {
        //TODO start game
    }

    void startGame() {
        Board board = new Board();
        BoardInitializer boardInitializer = new BoardInitializer(boardHeight, boardWidth);
        boardInitializer.initializeBoard(board);
        Game game = new Game(board, this.players);
    }

    public void initialize() {
        setMainUIVisibility(false);
        this.chooseOpponentVBox.setVisible(true);
        this.choosePlayerNamesVBox.setVisible(false);
    }

    private void setMainUIVisibility(boolean visible) {
        this.playersListView.setVisible(visible);
        this.startGameButton.setVisible(visible);
        this.currentPlayerLabel.setVisible(visible);
        this.buttonPane.setVisible(visible);
        this.playersLabel.setVisible(visible);
    }

    public void vsPlayerButtonClicked() {
        setAfterPlayerChooseVisibility();
        this.gameMode = GameMode.TWO_PLAYERS;
    }

    public void onVsAIButtonClicked() {
        setAfterPlayerChooseVisibility();
        this.gameMode = GameMode.ONE_PLAYER;
    }

    private void createPlayerNamesDialog() {
        this.playerNamesDialog = new PlayerNamesDialog(firstPlayerTextField, secondPlayerTextField);
        this.players = new Players(this.gameMode, this.playerNamesDialog);
    }

    private void setAfterPlayerChooseVisibility() {
        this.chooseOpponentVBox.getChildren().clear();
        this.chooseOpponentVBox.setVisible(false);
        this.choosePlayerNamesVBox.setVisible(true);
    }

    public void onPlayerNamesConfButtonClicked() {
        createPlayerNamesDialog();
        this.choosePlayerNamesVBox.setVisible(false);
        this.choosePlayerNamesVBox.getChildren().clear();
        setMainUIVisibility(true);
    }

}
