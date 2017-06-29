package com.mprtcz.tictactoeproject.controllers;

import com.mprtcz.tictactoeproject.game.*;
import com.mprtcz.tictactoeproject.player.Players;
import com.mprtcz.tictactoeproject.ui_elements.PlayerNamesDialog;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Michal_Partacz
 * @since 27.06.2017.
 */
public class Controller {
    private Players players;
    private GameMode gameMode;
    private Game game;
    private Board board;
    private BoardSize boardSize;

    public ListView<String> playersListView;
    public Button startGameButton;
    public Label currentPlayerLabel;
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

    public void onStartGameButtonClicked() {
        //TODO start game
        this.startGame();
    }

    void startGame() {
        this.game = new Game(board, this.players);
    }

    private void initializeBoard() {
        this.board = new Board();
        BoardInitializer boardInitializer = new BoardInitializer(this.boardSize);
        boardInitializer.initializeBoard(board);
        board.getBoardGUI().drawButtons(this.buttonsGridPane);
    }

    public void initialize() {
        setMainUIVisibility(false);
        this.chooseOpponentVBox.setVisible(true);
        this.choosePlayerNamesVBox.setVisible(false);
        this.boardSize = new BoardSize(3, 3);
        this.addSlidersListeners();
        initializeBoard();
    }

    private void addSlidersListeners() {
        this.horizontalSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            boardSize.setWidth(newValue.intValue());
            initializeBoard();
        });
        this.verticalSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            boardSize.setHeight(newValue.intValue());
            initializeBoard();
        });
    }

    private void setMainUIVisibility(boolean visible) {
        this.startGameButton.setVisible(visible);
        this.currentPlayerLabel.setVisible(visible);
        this.rightPanelVBox.setVisible(visible);
        this.buttonsGridPane.setVisible(visible);
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


    public void onVerticalSliderScroll() {
        if (this.game == null || !this.game.isRunning()) {
            System.out.println("Controller.onVerticalSliderDragDetected");
            this.boardSize.setHeight((int) this.verticalSlider.getValue());
        }
        this.initializeBoard();
    }

    public void onHorizontalSliderScroll() {
        if (this.game == null || !this.game.isRunning()) {
            System.out.println("Controller.onVerticalSliderDragDetected");
            this.boardSize.setWidth((int) this.horizontalSlider.getValue());
        }
        this.initializeBoard();
    }

}
