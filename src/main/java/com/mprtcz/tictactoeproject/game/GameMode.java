package com.mprtcz.tictactoeproject.game;

/**
 * @author Michal_Partacz
 * @since 29.06.2017.
 */
public enum GameMode {
    //ONE_PLAYER,
    TWO_PLAYERS("1", "TWO PLAYERS"),
    NET_GAME("2", "NET GAME"),
    DEFAULT("anything", "DEFAULT [TWO PLAYERS]");

    String name;
    String marker;

    GameMode(String marker, String name) {
        this.name = name;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public String getMarker() {
        return marker;
    }
}
