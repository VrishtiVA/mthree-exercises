package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsExerciseCTest {

    /* Test Plan:
     * Null: null -> null
     * Empty: removeTheVowels( "" ) ->  ""
     * Mixed type: removeTheVowels( "truncate" ) ->  "trnct"
     * Only vowels: removeTheVowels( "aeiou" ) ->  ""
     * No vowels: removeTheVowels( "qwrtyp" ) ->  "qwrtyp"
     */

    @Test
    void testRemoveTheVowelsNull() {
        //Arrange & Act
        String result = StringsExerciseC.removeTheVowels(null);
        //Assert
        assertNull(result);
    }

    @Test
    void testRemoveTheVowelsEmpty() {
        //Arrange & Act
        String result = StringsExerciseC.removeTheVowels("");
        //Assert
        assertEquals("", result);
    }

    @Test
    void testRemoveTheVowelsMixedType() {
        //Arrange & Act
        String result = StringsExerciseC.removeTheVowels("truncate");
        //Assert
        assertEquals("trnct", result);
    }

    @Test
    void testRemoveTheVowelsAllVowels() {
        //Arrange & Act
        String result = StringsExerciseC.removeTheVowels("aeiou");
        //Assert
        assertEquals("", result);
    }

    @Test
    void testRemoveTheVowelsNoVowels() {
        //Arrange & Act
        String result = StringsExerciseC.removeTheVowels("qwrtyp");
        //Assert
        assertEquals("qwrtyp", result);
    }

}