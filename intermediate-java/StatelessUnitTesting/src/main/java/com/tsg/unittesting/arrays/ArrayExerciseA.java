/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.arrays;

/**
 *
 * @author ahill
 */
public class ArrayExerciseA {
    
    /**
     * Given an array of ints, find and return the maximum value.
     * 
     * Example Results:
     * maxOfArray( {1}  ) ->  1
     * maxOfArray( {3,4,5}  ) ->  5
     * maxOfArray( {-9000, -700, -50, -3}  ) ->  -3
     * 
     * @param numbers array of integers
     * @return int max
     */
    public static int maxOfArray(int[] numbers){

        //Empty array case
        if (numbers.length == 0) return 0;

        //Tracking variable
        int maxOfArray = numbers[0]; //Initialize with first value

        //Linear search for maximum
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxOfArray)
                maxOfArray = numbers[i];
        }

        //Return max
        return maxOfArray;
    }
    
}
