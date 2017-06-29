package com.mprtcz.tictactoeproject.ui_elements;

import org.testng.annotations.Test;

import java.lang.reflect.MalformedParametersException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Michal_Partacz
 * @since 29.06.17
 */
@Test
public class InputValidatorTest {
    public void validateAnotherGameAnswerTest() {
        InputValidator inputValidator = new InputValidator();
        assertTrue(inputValidator.validateAnotherGameAnswer("y"));
        assertFalse(inputValidator.validateAnotherGameAnswer("n"));
        assertFalse(inputValidator.validateAnotherGameAnswer("N"));
        assertTrue(inputValidator.validateAnotherGameAnswer("Y"));
    }

    @Test(expectedExceptions = MalformedParametersException.class)
    public void validateAnotherGameAnswerTest_Exception() {
        InputValidator inputValidator = new InputValidator();
        inputValidator.validateAnotherGameAnswer("g");
    }
}

