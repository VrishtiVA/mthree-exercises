package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsExerciseFTest {

    /* Test Plan:
     * Null: null -> null
     * Empty: "" -> null
     * SingleLongest: longestWord( "Invention my dear friends is 93% perspiration 6% electricity 4% evaporation and 2% butter ripple" ) ->  "perspiration"
     * Hyphenated: longestWord( "All well-established principles should be periodically challenged" ) ->  "well-established"
     * Tied: longestWord( "Never argue with the data" ) ->  "Never"
     */

    @Test
    void testLongestWordNull() {
        //Arrange & Act
        String result = StringsExerciseF.longestWord(null);
        //Assert
        assertNull(result, "There is no longest word because there are no words.");
    }

    @Test
    void testLongestWordEmpty() {
        //Arrange & Act
        String result = StringsExerciseF.longestWord("");
        //Assert
        assertNull(result, "There is no longest word because there are no words.");
    }

    @Test
    void testLongestWordSingleLongest() {
        //Arrange & Act
        String result = StringsExerciseF.longestWord("Invention my dear friends is 93% perspiration 6% electricity 4% evaporation and 2% butter ripple");
        //Assert
        assertEquals("perspiration", result);
    }

    @Test
    void testLongestWordHyphenated() {
        //Arrange & Act
        String result = StringsExerciseF.longestWord("All well-established principles should be periodically challenged");
        //Assert
        assertEquals("well-established", result);
    }

    @Test
    void testLongestWordTied() {
        //Arrange & Act
        String result = StringsExerciseF.longestWord("Never argue with the data");
        //Assert
        assertEquals("Never", result, "The first longest word should be returned in the case of a tie.");
    }

}