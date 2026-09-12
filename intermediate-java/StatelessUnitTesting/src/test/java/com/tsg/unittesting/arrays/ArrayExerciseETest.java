package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayExerciseETest {

    /*
     * Note: No need for set up since test class is static.
     *
     * Test Cases:
     * - all lower case: camelCaseIt( {"llama", "llama", "duck"}  ) ->  "llamaLlamaDuck"
     * - longer all lower case: camelCaseIt( {"lambs", "eat", "oats", "and", "does", "eat", "oats"}  ) ->  "lambsEatOatsAndDoesEatOats"
     * - all capitals: camelCaseIt( {"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"}  ) ->  "doOrDoNotThereIsNoTry"
     * - inverted camel case: camelCaseIt( {"LLAMA", "lLAMA", "dUCK"}  ) ->  "llamaLlamaDuck"
     * - word is empty: camelCaseIt( {"llama", "", "duck"}  ) ->  "llamaDuck"
     * - word is single char: camelCaseIt( {"llama", "a", "duck"}  ) ->  "llamaADuck"
     * - no words: [] -> "";
     * - 1 word: "llama" -> "llama"
     */

    @Test
    void testCamelCaseAllLower() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[]{"llama", "llama", "duck"});
        //Assert
        assertEquals("llamaLlamaDuck", result);
    }

    @Test
    void testCamelCaseAllLongerLower() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[]{"lambs", "eat", "oats", "and", "does", "eat", "oats"});
        //Assert
        assertEquals("lambsEatOatsAndDoesEatOats", result);
    }

    @Test
    void testCamelCaseAllCapital() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[]{"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"});
        //Assert
        assertEquals("doOrDoNotThereIsNoTry", result);
    }

    @Test
    void testCamelCaseAllInvertedCamel() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[]{"LLAMA", "lLAMA", "dUCK"});
        //Assert
        assertEquals("llamaLlamaDuck", result);
    }

    @Test
    void testCamelCaseEmptyWord() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[]{"llama", "", "duck"});
        //Assert
        assertEquals("llamaDuck", result);
    }

    @Test
    void testCamelCaseSingleCharacterWord() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[]{"llama", "a", "duck"});
        //Assert
        assertEquals("llamaADuck", result);
    }

    @Test
    void testCamelCaseNoWords() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[0]);
        //Assert
        assertEquals("", result);
    }

    @Test
    void testCamelCaseOneWord() {

        //Act
        String result = ArrayExerciseE.camelCaseIt(new String[]{"llama"});
        //Assert
        assertEquals("llama", result);
    }

}