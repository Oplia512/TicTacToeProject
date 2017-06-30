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
public class TicTacToePlayersTest {
    TicTacToePlayers ticTacToePlayers;

    @BeforeMethod
    public void createPlayersObject() {
        this.ticTacToePlayers = new TicTacToePlayers(GameMode.ONE_PLAYER);
    }

    public void createPlayersTest() {
        assertEquals(GameMode.ONE_PLAYER, ticTacToePlayers.getGameMode());
        assertEquals("Player1", ticTacToePlayers.getPlayer1().getName());
        assertEquals("Player2", ticTacToePlayers.getPlayer2().getName());
        assertEquals(Sign.O, ticTacToePlayers.getPlayer1().getGameElement());
        assertEquals(Sign.X, ticTacToePlayers.getPlayer2().getGameElement());
    }

    public void getOppositePlayerTest() {
        assertEquals(ticTacToePlayers.getPlayer1(), ticTacToePlayers.getOppositePlayer(ticTacToePlayers.getPlayer2()));
        assertEquals(ticTacToePlayers.getPlayer2(), ticTacToePlayers.getOppositePlayer(ticTacToePlayers.getPlayer1()));
    }
}
