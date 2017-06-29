package com.mprtcz.tictactoeproject.player;

import com.mprtcz.tictactoeproject.game.Sign;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
@Test
public class PlayerTest {

    public void increaseScoreTest() {
        Player player = new Player("Name", Sign.EMPTY);
        assertEquals(0, player.getScore());
        player.increasePoints();
        assertEquals(1, player.getScore());
    }

}
