package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayExerciseATest {

    //This is actually unnecessary since class is static itself.

    private static ArrayExerciseA testClass;

    @BeforeAll
    public static void setUpClass() {
        //Since this is static, it can't refer to non-static variables.
        //So the ArrayExerciseA attribute has to be static too, unless wanting to initialize in @BeforeEach.
        //But that is unnecessary as don't need fresh, method doesn't have side effects.
        testClass = new ArrayExerciseA();
    }

    /* ========== Test Plan ==========
    Note: The method has not yet been implemented, so I decide how it must work here.

    Test Objective: Ensure maxOfArray works as expected.
    Test Scope: maxOfArray(int[] numbers) method
    Test Approach: Unit testing, potentially automated with GitHub Actions too.
    Test Cases:

    empty array {} -> 0
    non init array {0, 0, 0} -> 0
    mix of nums and nulls array {1, 2, -1, 0} -> ignore nulls, give max
    single value array {1} -> 1
    positive nums array {3, 4, 5} -> 5
    negative nums array {-9000, -700, -50, -3} -> -3
    reversed array {5, 4, 3} -> 5

     */

    @Test
    void testMaxOfEmptyArray() {

        //Arrange
        int[] arr = new int[0];
        //Act
        int maxOfArray = testClass.maxOfArray(arr);
        //Assert
        assertEquals(0, maxOfArray, "The maximum should be 0, since empty.");
    }

    @Test
    void testMaxOfDefaultArray() {

        //Arrange
        int[] arr = new int[5];
        //Act
        int maxOfArray = testClass.maxOfArray(arr);
        //Assert
        assertEquals(0, maxOfArray, "The maximum should be the default value 0.");
    }

    @Test
    void testMaxOfMixedArray() {

        //Arrange
        int[] arr = new int[4];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = -1;

        //Act
        int maxOfArray = testClass.maxOfArray(arr);

        //Assert
        assertEquals(2, maxOfArray, "Maximum should be 2.");
    }

    @Test
    void testMaxOfSingleValueArray() {

        //Arrange
        int[] arr = new int[] {1};
        //Act
        int maxOfArray = testClass.maxOfArray(arr);
        //Assert
        assertEquals(1, maxOfArray, "The only value 1 should be the maximum.");
    }

    @Test
    void testMaxOfPositiveNumbersArray() {

        //Arrange
        int[] arr = new int[] {3, 4, 5};
        //Act
        int maxOfArray = testClass.maxOfArray(arr);
        //Assert
        assertEquals(5, maxOfArray, "5 should be the maximum.");
    }

    @Test
    void testMaxOfNegativeNumbersArray() {

        //Arrange
        int[] arr = new int[] {-9000, -700, -50, -3};
        //Act
        int maxOfArray = testClass.maxOfArray(arr);
        //Assert
        assertEquals(-3, maxOfArray, "-3 should be the maximum.");
    }

    @Test
    void testMaxOfReversedNumbersArray() {

        //Arrange
        int[] arr = new int[] {5, 4, 3};
        //Act
        int maxOfArray = testClass.maxOfArray(arr);
        //Assert
        assertEquals(5, maxOfArray, "5 should be the maximum.");
    }

}