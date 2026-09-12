package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsExerciseATest {

    /*
     * Test Plan:
     * w/Special: yell( "Hello there." ) ->  "HELLO THERE."
     * allLower: yell( "shhhhhhhhhhhh" ) ->  SHHHHHHHHHHHH
     * mixedCase: yell( "AAaAAAaAAAaaAAHHHH" ) ->  "AAAAAAAAAAAAAAHHHH"
     * allUpper: yell( "HELLO" ) ->  "HELLO"
     */

    @Test
    void testYellMixedCaseWithSpecial() {
        //Arrange & Act
        String result = StringsExerciseA.yell("Hello there.");
        //Assert
        assertEquals("HELLO THERE.", result);
    }

    @Test
    void testYellAllLowerCase() {
        //Arrange & Act
        String result = StringsExerciseA.yell("shhhhhhhhhhhh");
        //Assert
        assertEquals("SHHHHHHHHHHHH", result);
    }

    @Test
    void testYellMixedCase() {
        //Arrange & Act
        String result = StringsExerciseA.yell("AAaAAAaAAAaaAAHHHH");
        //Assert
        assertEquals("AAAAAAAAAAAAAAHHHH", result);
    }

    @Test
    void testYellAllUpper() {
        //Arrange & Act
        String result = StringsExerciseA.yell("HELLO");
        //Assert
        assertEquals("HELLO", result);
    }

}