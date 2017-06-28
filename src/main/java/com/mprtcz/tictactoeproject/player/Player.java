package com.mprtcz.tictactoeproject.player;

/**
 * Created by Michal_Partacz on 28.06.2017.
 */
public class Player {
    private String name;
    private int score;
    private GamesHistory gamesHistory;
    private int currentSign;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.gamesHistory = new GamesHistory();
    }

    public void setCurrentSign(int currentSign) {
        this.currentSign = currentSign;
    }

    public int getCurrentSign() {
        return currentSign;
    }
}
