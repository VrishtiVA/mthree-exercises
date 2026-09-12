package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsExerciseETest {

    /* Condition
     * true if either String 'contains' the other...
     * but false if they are exactly equal, or completely different.
     *
     * Test Plan:
     * //Nulls:
     * null ( null, null ) ->  false
     * null ( null, "" ) ->  false
     * null ( "", null ) ->  false
     *
     * //Empty:
     * one empty ( "", "one" ) ->  false
     * contains empty ( "fancypants", "" ) ->  true
     *
     * //Contains
     * middle ( "fancypants", "pant" ) ->  true
     * start ( "fancypants", "fancy" ) ->  true
     * end containsTheOther( "one", "tone" ) ->  true
     * equal containsTheOther( "same", "same" ) ->  false
     *
     * //Doesn't contain
     * false containsTheOther( "llama", "duck" ) ->  false
     */

    @Test
    void testContainsTheOtherBothNull() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther(null, null);
        //Assert
        assertFalse(result, "Result should be false, since null is exactly equal to null.");
    }

    @Test
    void testContainsTheOtherOneNull() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther(null, "");
        //Assert
        assertFalse(result, "Result should be false, as null can't contain anything.");
    }

    @Test
    void testContainsTheOtherTwoNull() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther("", null);
        //Assert
        assertFalse(result, "Result should be false, as string doesn't contain null.");
    }

    @Test
    void testContainsTheOtherOneEmpty() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther("", "one");
        //Assert
        assertFalse(result);
    }

    @Test
    void testContainsTheOtherTwoEmpty() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther("fancypants", "");
        //Assert
        assertTrue(result);
    }

    @Test
    void testContainsTheOtherMiddle() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther( "fancypants", "pant");
        //Assert
        assertTrue(result);
    }

    @Test
    void testContainsTheOtherStart() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther("fancypants", "fancy");
        //Assert
        assertTrue(result);
    }

    @Test
    void testContainsTheOtherEnd() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther("one", "tone");
        //Assert
        assertTrue(result);
    }

    @Test
    void testContainsTheOtherEqual() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther("same", "same");
        //Assert
        assertFalse(result);
    }

    @Test
    void testContainsTheOtherNot() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther( "llama", "duck" );
        //Assert
        assertFalse(result);
    }

}