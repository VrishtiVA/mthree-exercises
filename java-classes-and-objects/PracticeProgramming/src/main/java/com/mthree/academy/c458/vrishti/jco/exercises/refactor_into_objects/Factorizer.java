package com.mthree.academy.c458.vrishti.jco.exercises.refactor_into_objects;

import java.util.Scanner;

public class Factorizer {
    public void runFactorizer() {

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
