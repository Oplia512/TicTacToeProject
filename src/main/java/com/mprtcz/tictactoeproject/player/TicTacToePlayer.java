package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.GameElement;
import com.mprtcz.tictactoeproject.game.Sign;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class TicTacToePlayer implements Player {
    private String name;
    private int score;
    private Sign playerSign;
    private Sign sign;

    public TicTacToePlayer(String name, Sign playerSign) {
        this.name = name;
        this.score = 0;
        this.playerSign = playerSign;
    }

    @Override
    public String toString() {
        return "TicTacToePlayer{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", playerSign=" + playerSign +
                '}';
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void increasePoints() {
        this.score++;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public GameElement getGameElement() {
        return playerSign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }
}
