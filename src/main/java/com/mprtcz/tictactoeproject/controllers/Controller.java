package com.mprtcz.tictactoeproject.controllers;

import com.mprtcz.tictactoeproject.game.Board;
import com.mprtcz.tictactoeproject.game.BoardInitializer;
import com.mprtcz.tictactoeproject.game.Game;
import com.mprtcz.tictactoeproject.game.GameMode;
import com.mprtcz.tictactoeproject.player.Players;
import com.mprtcz.tictactoeproject.ui_elements.PlayerNamesDialog;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * @author Michal_Partacz
 * @since 27.06.2017.
 */
public class Controller {
    private Players players;
    private int boardWidth = 5;
    private int boardHeight = 5;
    private GameMode gameMode;
    private Game game;
    private Board board;

    public ListView<String> playersListView;
    public Button startGameButton;
    public Label currentPlayerLabel;
    public Pane buttonPane;
    public Label playersLabel;
    public BorderPane borderPane;
    public VBox choosePlayerNamesVBox;
    public VBox chooseOpponentVBox;
    public TextField firstPlayerTextField;
    public TextField secondPlayerTextField;
    public HBox secondPlayerHBox;
    public Slider verticalSlider;
    public Slider horizontalSlider;
    public VBox rightPanelVBox;
    public GridPane buttonsGridPane;

    public Controller() {
    }


    public void onStartGameButtonClicked() {
        //TODO start game
        this.startGame();
    }

    void startGame() {
        this.board = new Board();
        BoardInitializer boardInitializer = new BoardInitializer(boardHeight, boardWidth);
        boardInitializer.initializeBoard(board);
        this.game = new Game(board, this.players);
        board.getBoardGUI().drawButtons(this.buttonsGridPane);
    }

    private void initializeGridPane(int width, int height) {
//        this.buttonsGridPane.
    }

    public void initialize() {
        setMainUIVisibility(false);
        this.chooseOpponentVBox.setVisible(true);
        this.choosePlayerNamesVBox.setVisible(false);
    }

    private void setMainUIVisibility(boolean visible) {
        this.startGameButton.setVisible(visible);
        this.currentPlayerLabel.setVisible(visible);
        this.buttonPane.setVisible(visible);
        this.rightPanelVBox.setVisible(visible);
    }

    public void vsPlayerButtonClicked() {
        this.gameMode = GameMode.TWO_PLAYERS;
        setAfterPlayerChooseVisibility();
    }

    public void onVsAIButtonClicked() {
        this.gameMode = GameMode.ONE_PLAYER;
        setAfterPlayerChooseVisibility();
    }

    private void createPlayerNamesDialog() {
        PlayerNamesDialog playerNamesDialog = new PlayerNamesDialog(firstPlayerTextField, secondPlayerTextField);
        this.players = new Players(this.gameMode, playerNamesDialog);
        displayPlayers();
    }

    private void setAfterPlayerChooseVisibility() {
        this.chooseOpponentVBox.getChildren().clear();
        this.chooseOpponentVBox.setVisible(false);
        this.choosePlayerNamesVBox.setVisible(true);
        if (this.gameMode == GameMode.ONE_PLAYER) {
            this.secondPlayerHBox.setVisible(false);
            this.secondPlayerHBox.getChildren().clear();
        }
    }

    public void onPlayerNamesConfButtonClicked() {
        createPlayerNamesDialog();
        this.choosePlayerNamesVBox.setVisible(false);
        this.choosePlayerNamesVBox.getChildren().clear();
        setMainUIVisibility(true);
    }

    private void displayPlayers() {
        this.playersListView.getItems().add(this.players.getPlayer1().getName());
        if (this.players.getGameMode() == GameMode.TWO_PLAYERS) {
            this.playersListView.getItems().add(this.players.getPlayer2().getName());
        }
    }

    public void onVerticalSliderDragDetected() {
        if (this.game.isRunning()) {
            return;
        }

    }

    public void onHorizontalDragDetected() {
        if (this.game.isRunning()) {
            return;
        }
    }




}
