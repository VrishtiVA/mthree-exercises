package com.tsg.unittesting.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExerciseDTest {

    /* Test Plan:
     * TrueLower: isFirstTheFirst( 'a' , 'b'  ) ->  true
     * FalseLower: isFirstTheFirst( 'b' , 'a'  ) ->  false
     * TrueMixedCase: isFirstTheFirst( 'a' , 'Z'   ) -> true
     * FalseMixedCase: isFirstTheFirst( 'Z' , 'a'   ) -> false
     * TrueUpper: isFirstTheFirst( 'O' , 'X'  ) ->  true
     * FalseUpper: isFirstTheFirst( 'Z' , 'X'  ) ->  false
     * SameMixedCase: isFirstTheFirst( 'Z' , 'z'   ) -> false
     */

    @Test
    void testIsFirstTheFirstTrueLower() {
        //Arrange & Act
        boolean result = LogicExerciseD.isFirstTheFirst('a' , 'b');
        //Assert
        assertTrue(result);
    }

    @Test
    void testIsFirstTheFirstFalseLower() {
        //Arrange & Act
        boolean result = LogicExerciseD.isFirstTheFirst('b' , 'a');
        //Assert
        assertFalse(result);
    }

    @Test
    void testIsFirstTheFirstTrueMixedCase() {
        //Arrange & Act
        boolean result = LogicExerciseD.isFirstTheFirst('a' , 'Z');
        //Assert
        assertTrue(result);
    }

    @Test
    void testIsFirstTheFirstFalseMixedCase() {
        //Arrange & Act
        boolean result = LogicExerciseD.isFirstTheFirst('Z' , 'a');
        //Assert
        assertFalse(result);
    }

    @Test
    void testIsFirstTheFirstTrueUpper() {
        //Arrange & Act
        boolean result = LogicExerciseD.isFirstTheFirst('O' , 'X');
        //Assert
        assertTrue(result);
    }

    @Test
    void testIsFirstTheFirstFalseUpper() {
        //Arrange & Act
        boolean result = LogicExerciseD.isFirstTheFirst('Z' , 'X');
        //Assert
        assertFalse(result);
    }

    @Test
    void testIsFirstTheFirstSameMixedCase() {
        //Arrange & Act
        boolean result = LogicExerciseD.isFirstTheFirst('Z' , 'z');
        //Assert
        assertFalse(result);
    }
}