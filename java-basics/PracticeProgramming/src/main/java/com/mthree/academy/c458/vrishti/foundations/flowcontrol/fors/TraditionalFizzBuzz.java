package com.mthree.academy.c458.vrishti.foundations.flowcontrol.fors;

import java.util.Scanner;

public class TraditionalFizzBuzz {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        //Desired input
        int upto;

        //Utility variable
        boolean isFactor;
        int fizzbuzz = 0;

        //Take user input
        System.out.print("How many units of fizzing and buzzing do you need in your life? ");
        upto = inputReader.nextInt();
        System.out.println();

        //Count units
        for (int i = 0; fizzbuzz < upto; i++) {

            //0 shouldn't be treated as fizzbuzz.
            if (i == 0) {
                System.out.println(0);
                continue;
            }

            //Reset flag
            isFactor = false;

            //Try multiple of 3
            if (i % 3 == 0) {
                isFactor = true;
                System.out.print("fizz ");
            }

            //Try multiple of 5
            if (i % 5 == 0) {
                isFactor = true;
                System.out.print("buzz");
            }

            //Otherwise just output number
            if (!isFactor) {
                System.out.print(i);
            } else {
                fizzbuzz++;
            }

            System.out.println();
        }

        System.out.println("\nTRADITION!!!!!");

    }
}
