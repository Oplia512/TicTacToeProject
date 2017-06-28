package com.mprtcz.tictactoeproject.game;

import com.mprtcz.tictactoeproject.player.Player;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public class Move {
    private int columnIndex;
    private int rowIndex;
    private Player Player;

    public Move(int columnIndex, int rowIndex, Player player) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        Player = player;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public com.mprtcz.tictactoeproject.player.Player getPlayer() {
        return Player;
    }
}
