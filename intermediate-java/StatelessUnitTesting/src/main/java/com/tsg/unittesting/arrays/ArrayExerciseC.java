/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.arrays;

/**
 *
 * @author ahill
 */
public class ArrayExerciseC {

    /**
     * Given an array of integers, return them all as a single continuous text value.
     * 
     * stringThemTogether( { 1, 3, 3, 7 }  ) ->  "1337"
     * stringThemTogether( { 1, 33, 555, 7777, 99999 } ) ->  "133555777799999"
     * stringThemTogether( { }  ) ->  ""
     * 
     * @param nums
     * @return String
     * @implNote Implemented by VrishtiVA
     */
    public String stringThemTogether(int[] nums){

        //Prevent sb object creation if nothing to string by ending early
        if (nums.length == 0) return "";

        //Use string builder to build together
        StringBuilder stringBuilder = new StringBuilder();
        for (int n : nums) {
            stringBuilder.append(n);
        }

        //Return string form
        return stringBuilder.toString();
    }
    
}
