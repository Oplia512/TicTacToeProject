package com.mprtcz.tictactoeproject.game;

/**
 * Created by Michal_Partacz on 28.06.2017.
 */
public enum Sign {
    X(-1, "X"),
    O(1, "O"),
    EMPTY(0, ".");

    int value;
    String representation;

    Sign(int value, String representation) {
        this.value = value;
        this.representation = representation;
    }

    public int getValue() {
        return value;
    }

    public String getRepresentation() {
        return representation;
    }
}
