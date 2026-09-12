package com.tsg.unittesting.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExerciseBTest {

    static LogicExerciseB testClass;

    @BeforeAll
    static void setUpClass() {
        testClass = new LogicExerciseB();
    }

    /* Test Plan:
     * Single digit first: placeOf( 1 ) ->   "1st"
     * Single digit second: placeOf( 2 ) -> "2nd"
     * Single digit third: placeOf( 3 ) ->   "3rd"
     * Double digit second: placeOf( 22 ) ->   "22nd"
     * Triple digit first: placeOf( 101 ) ->   "201st"
     * Triple digit third: placeOf( 203 ) ->   "203rd"
     */

    @Test
    void testPlaceOfSingleDigitFirst() {

        //Arrange & Act
        String result = testClass.placeOf(1);
        //Assert
        assertEquals("1st", result);
    }

    @Test
    void testPlaceOfSingleDigitSecond() {

        //Arrange & Act
        String result = testClass.placeOf(2);
        //Assert
        assertEquals("2nd", result);
    }

    @Test
    void testPlaceOfSingleDigitThird() {

        //Arrange & Act
        String result = testClass.placeOf(3);
        //Assert
        assertEquals("3rd", result);
    }

    @Test
    void testPlaceOfDoubleDigitSecond() {

        //Arrange & Act
        String result = testClass.placeOf(22);
        //Assert
        assertEquals("22nd", result);
    }

    @Test
    void testPlaceOfTripleDigitFirst() {

        //Arrange & Act
        String result = testClass.placeOf(101);
        //Assert
        assertEquals("101st", result);
    }

    @Test
    void testPlaceOfTripleDigitThird() {

        //Arrange & Act
        String result = testClass.placeOf(203);
        //Assert
        assertEquals("203rd", result);
    }

}