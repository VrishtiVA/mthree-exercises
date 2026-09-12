package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayExerciseDTest {

    /* ========== Test Class ==========

    "As if the decimal point had gone missing"
    Test Cases:
    - Normal case: pointFree( [1.1, .2]  ) ->  11
    - Balanced case: pointFree( [.2, 1.]  ) ->  2
    - More decimal places: pointFree( [1.1, .22]  ) ->  22
    - Zero headed: pointFree( [ .039 , 20 , .005005 ]  ) ->  5005
    - Against negatives: pointFree( [ -9.9 , -700 , -.5  ]  ) ->  -5
    - Post point zero tailed: pointFree( [ 5.15, 5.200  ]  ) ->  515

     */

    @Test
    void testPointFreeNormal() {

        //Act
        int result = ArrayExerciseD.pointFree(new double[]{1.1, .2});
        //Assert
        assertEquals(11, result);
    }

    @Test
    void testPointFreeBalanced() {

        //Act
        int result = ArrayExerciseD.pointFree(new double[]{.2, 1});
        //Assert
        assertEquals(2, result);
    }

    @Test
    void testPointFreeMoreDecimalPlaces() {

        //Act
        int result = ArrayExerciseD.pointFree(new double[]{1.1, .22});
        //Assert
        assertEquals(22, result);
    }

    @Test
    void testPointFreeZeroHeaded() {

        //Act
        int result = ArrayExerciseD.pointFree(new double[]{.039 , 20 , .005005});
        //Assert
        assertEquals(5005, result);
    }

    @Test
    void testPointAgainstNegatives() {

        //Act
        int result = ArrayExerciseD.pointFree(new double[]{-9.9 , -700 , -.5});
        //Assert
        assertEquals(-5, result);
    }

    @Test
    void testPointPostPointZeroTailed() {

        //Act
        int result = ArrayExerciseD.pointFree(new double[]{5.15, 5.200});

        //Assert
        if (result == 5200) fail("Result should 515, not 5200.");
        assertEquals(515, result, "Result should be 515.");
    }

}