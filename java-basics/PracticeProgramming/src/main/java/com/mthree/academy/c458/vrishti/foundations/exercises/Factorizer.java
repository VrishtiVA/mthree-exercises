package com.mthree.academy.c458.vrishti.foundations.exercises;

import java.util.Scanner;

/**
 * So, finding factors could be done with a simple linear search. - O(n) complexity
 * However this can be optimized, knowing that factors usually come in pairs.
 * So we could go half way, up to the highest unique pair.
 *
 * Imagine no. 4 w/ factors 1, 2, 4
 * Pairs: {{1, 4}, {2, 2}} -> up to 2
 * The highest unique pair introduce 2, and we can use that to find its partner.
 * Imagine no. 10 w/ factors 1, 2, 5, 10
 * Pairs: {{1, 10}, {2, 5}} -> up to 2
 * Don't have to find the flipped pairs, after finding that 2, we can find its pair using the target 10/2 = 5.
 * How do we find how high need to go?
 * Imagine no. 16 w/ factors {{1, 16}, {2, 8}, {4, 4}} -> up to 4
 * Imagine no. 18 w/ {{1, 18}, {2, 9}, {3, 6}} -> up to 3
 * Imagine no. 20 w/ {{1, 20}, {2, 10}, {4, 5}} -> up to 4
 * Imagine no. 21 w/ {{1, 21}, {3, 7}} -> up to 3
 * Imagine no. 24 w/ {{1, 24}, {2, 12}, {3, 8}, {4, 6}} -> up to 4
 * Imagine no. 63 w/ {{1, 63}, {3, 21}, {7, 9}} -> up to 7
 *
 * They're all <= their square root.
 * So we can linear search up to the square root to find which half pairs fit.
 * Then go back down from there to find their fitting pairs.
 * O (2sqrt(n)) = O(sqrt(n))
 *
 * Forward pass: if x mod i == 0, then is factor. factor = i
 * Backward pass: if x mod i == 0, then pair (x/i) is also factor
 * To get the peak, stay under in forward pass, but start there on backward pass
 *      since can have duplicate on a square number, but nowhere else.
 *      So backward pass just takes the pair.
 * This method would successfully state factors in increasing order,
 *
 * Ideas:
 * - Could use storage e.g. dynamic list to store all factors,
 *      - but can optimize to without, e.g. by accumulating them.
 *
 * Constraint Thoughts:
 * - What if number is negative?
 *     - Well it would have same factors anyway, with one of the pairs being negative.
 *     - And technically a positive also has negative factors.
 *     - So I'll just find the absolute factors for now.
 *
 */
public class Factorizer {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        //Desired inputs
        int originalNum;

        //Trackers
        int factorsSum = 0;
        int factorsLength = 0;

        //1. Ask for valid number to factor
        do {
            System.out.print("What number would you like to factor? ");
            try {
                originalNum = inputReader.nextInt();
                break; //Escape loop once valid
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number, please try again.");
            }
        } while (true);

        //2. Print original number
        System.out.printf("The factors of %d are:\n", originalNum);

        //3. Find factors, print each factor
        //Forward pass, start at 1 so no division by 0 error.
        for (int i = 1; i < Math.sqrt(originalNum); i++) {
            //Check if factor
            if (originalNum % i == 0) {
                System.out.print(i + " ");
                factorsLength ++;
                factorsSum += i;
            }
        }
        //Backward pass
        for (int i = (int) Math.sqrt(originalNum); i > 0; i--) {
            //Check if factor, and state pair
            if (originalNum % i == 0) {
                System.out.print((originalNum/i) + " ");
                factorsLength ++;
                factorsSum += i;
            }
        }

        //Total number of factors
        System.out.println();
        System.out.printf("%d has %d factors.\n", originalNum, factorsLength);

        //Is perfect? (all factors sum to original?)
        if (factorsSum == originalNum) {
            System.out.println(originalNum + " is a perfect number.");
        } else {
            System.out.println(originalNum + " is not a perfect number.");
        }

        //Is number prime? (only 1 and self)? All have this so length 2 = prime.
        if (factorsLength == 2) {
            System.out.println(originalNum + " is a prime number.");
        } else {
            System.out.println(originalNum + " is not a prime number.");
        }

    }
}
