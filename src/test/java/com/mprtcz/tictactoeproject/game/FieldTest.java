package com.mprtcz.tictactoeproject.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
@Test
public class FieldTest {

    @DataProvider(name = "parameters")
    public static Object[][] parameters() {
        return new Object[][] {
                {0, 1, 0, 0},
                {1, 2, 1, 0},
                {15, 4, 3, 3}
        };
    }

    @Test(dataProvider = "parameters")
    public void convertFieldIndexToCoordinatesTest(int fieldIndex,
                                                   int boardWidth,
                                                   int xCoordinate,
                                                   int yCoordinate) {
        Field field = new Field();
        assertEquals(0,field.getXCoordinate());
        assertEquals(0,field.getYCoordinate());
        field.convertFieldIndexToCoordinates(fieldIndex, boardWidth);
        assertEquals(xCoordinate, field.getXCoordinate());
        assertEquals(yCoordinate, field.getYCoordinate());
    }
}

