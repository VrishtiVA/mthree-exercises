/*********************************
* The Software Guild
* Copyright (C) 2020 Wiley edu LLC - All Rights Reserved
*********************************/
package com.tsg.unittesting.arrays;

/**
 *
 * @author ahill
 */
public class ArrayExerciseD {
    
    /**
     * Given an array of doubles, return the biggest number of the lot, as if the decimal had gone missing!
     * 
     * pointFree( [1.1, .22]  ) ->  22
     * pointFree( [ .039 , 20 , .005005 ]  ) ->  5005
     * pointFree( [ -9.9 , -700 , -.5  ]  ) ->  -5
     * 
     * @param numbers
     * @return
     * @implNote Implemented by VrishtiVA
     */
    public static int pointFree(double[] numbers){

        //Cut early if nothing to max of
        if (numbers.length == 0) return 0;

        //Tracking variable
        int maxPointFree = calculatePointFree(numbers[0]);

        //Utility variable
        int pointFree;

        //Find max
        for (int i = 0; i < numbers.length; i++) {
            pointFree = calculatePointFree(numbers[i]);
            //Update maxPointFree if found new max.
            if (pointFree > maxPointFree)
                maxPointFree = pointFree;
        }

        //Return resulting maxPointFree
        return maxPointFree;
    }

    /**
     * @implNote Convert to point free by string conversion, replace . with nothing, and back to int.
     *           Cut of tailing zeros after decimal point too.
     * @param num The double number to convert
     * @return The point free number as int
     * @author VrishtiVA
     */
    private static int calculatePointFree(double num) {

        //Convert to string
        String stringNum = Double.toString(num);

        //Remove tailing 0s
        int cutPoint = stringNum.length();
        for (int i = stringNum.length() - 1; i > 0; i-- ) {
            //Stop at decimal point or if found something other than 0.
            if (stringNum.charAt(i) != '0') break;
            //Cut if found tailing zero
            cutPoint --;
        }

        //Apply point free conversion
        return Integer.parseInt(stringNum.substring(0, cutPoint).replace(".", ""));
    }

}
