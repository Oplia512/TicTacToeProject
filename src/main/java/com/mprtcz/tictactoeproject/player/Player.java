package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.GameElement;

/**
 * @author Michal_Partacz
 * @since 30.06.2017.
 */
public interface Player {
    String getName();

    void increasePoints();

    int getScore();

    GameElement getGameElement();
}
