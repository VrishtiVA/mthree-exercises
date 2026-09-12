package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayExerciseCTest {

    static ArrayExerciseC testClass;

    @BeforeAll
    static void setUpClass() {
        testClass = new ArrayExerciseC();
    }

    /* ========== Test Plan ==========

    Test Cases:
    singleDigits stringThemTogether( { 1, 3, 3, 7 }  ) ->  "1337"
    variedDigits stringThemTogether( { 1, 33, 555, 7777, 99999 } ) ->  "133555777799999"
    empty stringThemTogether( { }  ) ->  ""

    ==================================
     */

    @Test
    void testStringThemTogetherSingleDigits() {

        //Act
        String result = testClass.stringThemTogether(new int[]{ 1, 3, 3, 7 });
        //Assert
        assertEquals("1337", result, "Should be strung together.");
    }

    @Test
    void testStringThemTogetherVariedDigits() {

        //Act
        String result = testClass.stringThemTogether(new int[]{ 1, 33, 555, 7777, 99999 });
        //Assert
        assertEquals("133555777799999", result, "Should be strung together.");
    }

    @Test
    void testStringThemTogetherEmpty() {

        //Act
        String result = testClass.stringThemTogether(new int[0]);
        //Assert
        assertEquals("", result, "Should return empty string.");
    }
}