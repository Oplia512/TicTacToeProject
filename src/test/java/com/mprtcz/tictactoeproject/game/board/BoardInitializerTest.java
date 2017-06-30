package com.mprtcz.tictactoeproject.game.board;

import com.mprtcz.tictactoeproject.game.Sign;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.MalformedParametersException;

import static org.testng.Assert.assertEquals;

/**
 * @author Michal_Partacz
 * @since 30.06.2017.
 */
@Test
public class BoardInitializerTest {
    Board board;
    BoardSize boardSize;

    @BeforeTest
    public void createBoardAndBoardSize() {
        this.boardSize = new BoardSize(10, 5);
        this.board = new Board();
    }

    public void checkConstraintsTest() {
        BoardInitializer boardInitializer = new BoardInitializer(this.boardSize);
        boardInitializer.checkConstraints();
    }

    @Test(expectedExceptions = MalformedParametersException.class)
    public void checkConstraintsTestException_valueTooBig() {
        BoardInitializer boardInitializer = new BoardInitializer(new BoardSize(50, 10));
        boardInitializer.checkConstraints();
    }

    @Test(expectedExceptions = MalformedParametersException.class)
    public void checkConstraintsTestException_NegativeValue() {
        BoardInitializer boardInitializer = new BoardInitializer(new BoardSize(-1, 10));
        boardInitializer.checkConstraints();
    }

    public void initializeBoardTest_BoardDimensions() {
        BoardInitializer boardInitializer = new BoardInitializer(this.boardSize);
        boardInitializer.initializeBoard(this.board);
        assertEquals(5, this.board.getBoardHeight());
        assertEquals(10, this.board.getBoardWidth());
    }

    public void initializeBoardTest_EmptySignFields() {
        BoardInitializer boardInitializer = new BoardInitializer(this.boardSize);
        boardInitializer.initializeBoard(this.board);
        assertEquals(Sign.EMPTY, this.board.getValueOnPosition(0, 0));
        assertEquals(Sign.EMPTY, this.board.getValueOnPosition(1, 1));
        assertEquals(Sign.EMPTY, this.board.getValueOnPosition(2, 9));
        assertEquals(Sign.EMPTY, this.board.getValueOnPosition(4, 3));
    }
}
