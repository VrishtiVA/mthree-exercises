package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsExerciseBTest {

    /* Test Plan:
     * Null: null -> null
     * Empty: "" -> ""
     * Mixed case: tripleIt( "Llama" ) ->  "llamaLLAMAllama"
     * Lower case: tripleIt( "ha" ) ->  "haHAha"
     * Upper case: tripleIt( "HA" ) ->  "HAHAha"
     * Mixed case w/special: tripleIt( "Ha!" ) ->  "Ha!HA!ha!"
     */

    @Test
    void testTripleItNull() {
        //Arrange & Act
        String result = StringsExerciseB.tripleIt(null);
        //Assert
        assertNull(result);
    }

    @Test
    void testTripleItEmpty() {
        //Arrange & Act
        String result = StringsExerciseB.tripleIt("");
        //Assert
        assertEquals("", result);
    }

    @Test
    void testTripleItMixedCase() {
        //Arrange & Act
        String result = StringsExerciseB.tripleIt("Llama");
        //Assert
        assertEquals("llamaLLAMAllama", result);
    }

    @Test
    void testTripleItLowerCase() {
        //Arrange & Act
        String result = StringsExerciseB.tripleIt("ha");
        //Assert
        assertEquals("haHAha", result);
    }

    @Test
    void testTripleItUpperCase() {
        //Arrange & Act
        String result = StringsExerciseB.tripleIt("HA");
        //Assert
        assertEquals("HAHAha", result);
    }

    @Test
    void testTripleItMixedCaseWithSpecial() {
        //Arrange & Act
        String result = StringsExerciseB.tripleIt("Ha!");
        //Assert
        assertEquals("Ha!HA!ha!", result);
    }
}