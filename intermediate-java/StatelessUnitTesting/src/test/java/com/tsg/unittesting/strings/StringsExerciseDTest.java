package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsExerciseDTest {

    /* Test Plan:
     * null: null -> null
     * empty: "" -> ""
     * flip lower: simpleReverse( "fun times" ) ->  "semit nuf"
     * flip mixed: simpleReverse( "llama LLAMA duck" ) ->  "kcud AMALL amall"
     * flip palindrome: simpleReverse( "hannah" ) ->  "hannah"
     */

    @Test
    void testSimpleReverseNull() {
        //Arrange & Act
        String result = StringsExerciseD.simpleReverse(null);
        //Assert
        assertNull(result);
    }

    @Test
    void testSimpleReverseEmpty() {
        //Arrange & Act
        String result = StringsExerciseD.simpleReverse("");
        //Assert
        assertEquals("", result);
    }

    @Test
    void testSimpleReverseLowerCase() {
        //Arrange & Act
        String result = StringsExerciseD.simpleReverse("fun times");
        //Assert
        assertEquals("semit nuf", result);
    }

    @Test
    void testSimpleReverseMixedCase() {
        //Arrange & Act
        String result = StringsExerciseD.simpleReverse("llama LLAMA duck");
        //Assert
        assertEquals("kcud AMALL amall", result);
    }

    @Test
    void testSimpleReversePalindrome() {
        //Arrange & Act
        String result = StringsExerciseD.simpleReverse("hannah");
        //Assert
        assertEquals("hannah", result);
    }

}