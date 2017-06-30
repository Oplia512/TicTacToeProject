package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.Sign;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
@Test
public class TicTacToePlayerTest {
    TicTacToePlayer ticTacToePlayer;

    @BeforeMethod
    private void createPlayer() {
        this.ticTacToePlayer = new TicTacToePlayer("Name", Sign.EMPTY);
    }

    public void increaseScoreTest() {
        assertEquals(0, ticTacToePlayer.getScore());
        ticTacToePlayer.increasePoints();
        assertEquals(1, ticTacToePlayer.getScore());
    }

    public void checkNameAndSignTest() {
        assertEquals("Name", ticTacToePlayer.getName());
        assertEquals(Sign.EMPTY, (Sign) ticTacToePlayer.getGameElement());
    }

}
