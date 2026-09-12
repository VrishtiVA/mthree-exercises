package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayExerciseBTest {

    /* ========== Test Plan ==========

    Test Objective: Ensure
    Test Scope: multiplyAll() method
    Approach: Unit testing
    Test Cases:

    multiply all empty: multiplyAll( 5 , [] ) -> []
    multiply all positive: multiplyAll( 5 , [ 1 , 2 , 3 , 4 , 5 ] ) -> [ 5 , 10 , 15 , 20 , 25 ]
    multiply all zero: multiplyAll( 0 , [ 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 ] ) -> [ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ]
    multiply all negative: multiplyAll( -1 , [ -2 , 0 , 0 , 1 ] ) -> [ 2 , 0 , 0 , -1 ]

    ==================================
     */

    @Test
    void testMultiplyAllEmpty() {

        //Act
        int[] result = ArrayExerciseB.multiplyAll(5, new int[0]);

        //Assert
        assertArrayEquals(new int[0], result, "Result array should also have no elements.");
    }

    @Test
    void testMultiplyAllPositive() {

        //Act
        int[] result = ArrayExerciseB.multiplyAll(5, new int[]{1, 2, 3, 4, 5});

        //Assert
        assertArrayEquals(new int[]{5 , 10 , 15 , 20 , 25}, result, "All elements should be multiplied by 5.");
    }

    @Test
    void testMultiplyAllZero() {

        //Act
        int[] result = ArrayExerciseB.multiplyAll(0, new int[]{1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1});

        //Assert
        assertArrayEquals(new int[9], result, "All elements should be 0.");
    }

    @Test
    void testMultiplyAllNegative() {

        //Act
        int[] result = ArrayExerciseB.multiplyAll(-1, new int[]{-2 , 0 , 0 , 1});
        //Assert
        assertArrayEquals(new int[]{2 , 0 , 0 , -1}, result, "All elements should be multiplied by -1.");
    }

}