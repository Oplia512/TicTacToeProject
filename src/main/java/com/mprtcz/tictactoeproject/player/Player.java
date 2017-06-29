package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.Sign;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Player {
    private String name;
    private int score;
    private Sign playerSign;

    public Player(String name, Sign playerSign) {
        this.name = name;
        this.score = 0;
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
                ", playerSign=" + playerSign +
                '}';
    }

    public String getName() {
        return name;
    }

    public void increasePoints() {
        this.score++;
    }

    public int getScore() {
        return score;
    }
}
