package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.Sign;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Player {
    private String name;
    private int score;
    private GamesHistory gamesHistory;
    private Sign playerSign;

    public Player(String name, Sign playerSign) {
        this.name = name;
        this.score = 0;
        this.gamesHistory = new GamesHistory();
        this.playerSign = playerSign;
    }

    public void setPlayerSign(Sign playerSign) {
        this.playerSign = playerSign;
    }

    public Sign getPlayerSign() {
        return playerSign;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", gamesHistory=" + gamesHistory +
                ", playerSign=" + playerSign +
                '}';
    }

    public String getName() {
        return name;
    }
}
