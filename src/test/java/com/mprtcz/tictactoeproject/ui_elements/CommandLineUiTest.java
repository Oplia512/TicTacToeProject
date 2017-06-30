package com.mprtcz.tictactoeproject.ui_elements;

import com.mprtcz.tictactoeproject.game.Sign;
import com.mprtcz.tictactoeproject.player.TicTacToePlayer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
@Test
public class CommandLineUiTest {
    private CommandLineUi commandLineUi;
    private InputStream stdin = System.in;

    @BeforeMethod
    public void createCLI() {
        String data = "Data";
        this.stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);
        this.commandLineUi = new CommandLineUi(scanner);
    }

    @AfterMethod
    public void restoreSystemIn() {
        System.setIn(this.stdin);
    }

    public void getFieldFromUserTest() {
        assertEquals("Data", commandLineUi.getFieldFromTheUser(new TicTacToePlayer("Name", Sign.EMPTY)));
    }

    public void getArrayDimensionsTest() {
        assertEquals("Data",commandLineUi.getArrayDimensions());
    }


}
