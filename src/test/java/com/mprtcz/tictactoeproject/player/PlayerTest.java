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
public class PlayerTest {
    Player player;

    @BeforeMethod
    private void createPlayer() {
        this.player = new Player("Name", Sign.EMPTY);
    }

    public void increaseScoreTest() {
        assertEquals(0, player.getScore());
        player.increasePoints();
        assertEquals(1, player.getScore());
    }

    public void checkNameAndSignTest() {
        assertEquals("Name", player.getName());
        assertEquals(Sign.EMPTY, player.getPlayerSign());
    }

}
