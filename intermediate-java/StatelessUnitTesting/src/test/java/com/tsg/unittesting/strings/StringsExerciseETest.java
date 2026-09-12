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
     * one empty ( "", "one" ) ->  true
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
    void testContainsTheOtherEitherNull() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther(null, "");
        boolean result2 = StringsExerciseE.containsTheOther("", null);
        //Assert
        assertFalse(result, "Result should be false, as null can't contain anything.");
        assertFalse(result2, "Result should be false, as null can't contain anything.");
    }

    @Test
    void testContainsTheOtherEitherEmpty() {
        //Arrange & Act
        boolean result = StringsExerciseE.containsTheOther("", "one");
        boolean result2 = StringsExerciseE.containsTheOther("fancypants", "");
        //Assert
        assertTrue(result);
        assertTrue(result2);
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
        boolean result = StringsExerciseE.containsTheOther("fancy", "fancypants");
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