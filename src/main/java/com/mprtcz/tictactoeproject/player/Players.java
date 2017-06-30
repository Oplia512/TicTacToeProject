package com.mprtcz.tictactoeproject.player;

/**
 * @author Michal_Partacz
 * @since 30.06.2017.
 */
public interface Players {
    Player getPlayer1();

    Player getPlayer2();

    Player getOppositePlayer(Player player);
}
