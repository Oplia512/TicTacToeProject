package com.mprtcz.tictactoeproject.game;

/**
 * @author Michal_Partacz
 * @since 28.06.2017.
 */
public enum Sign implements GameElement {
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

    public static Sign getOppositeSign(Sign sign) {
        Sign toReturn = Sign.EMPTY;
        switch (sign) {
            case O:
                toReturn = Sign.X; break;
            case X:
                toReturn = Sign.O; break;
            case EMPTY:
                toReturn = Sign.EMPTY; break;
        }
        return toReturn;
    }
}
