package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.GameMode;
import com.mprtcz.tictactoeproject.game.Sign;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
@Test
public class PlayersTest {
    Players players;

    @BeforeMethod
    public void createPlayersObject() {
        this.players = new Players(GameMode.ONE_PLAYER);
    }

    public void createPlayersTest() {
        assertEquals(GameMode.ONE_PLAYER, players.getGameMode());
        assertEquals("Player1", players.getPlayer1().getName());
        assertEquals("Player2", players.getPlayer2().getName());
        assertEquals(Sign.O, players.getPlayer1().getPlayerSign());
        assertEquals(Sign.X, players.getPlayer2().getPlayerSign());
    }

    public void getOppositePlayerTest() {
        assertEquals(players.getPlayer1(), players.getOppositePlayer(players.getPlayer2()));
        assertEquals(players.getPlayer2(), players.getOppositePlayer(players.getPlayer1()));
    }
}
