package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal_Partacz on 28.06.2017.
 */
public class GamesHistory {

    private List<Game> games = new ArrayList<>();

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }
}
