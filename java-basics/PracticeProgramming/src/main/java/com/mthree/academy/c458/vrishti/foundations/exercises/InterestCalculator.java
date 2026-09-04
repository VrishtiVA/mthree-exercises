package com.mthree.academy.c458.vrishti.foundations.exercises;

import java.util.Scanner;

/**
 * Program to tell John how much money will be in find after specified no. years.
 *
 * New amount each quarter:
 * CurrentBalance * (1 + (QuarterlyInterestRate / 100))
 *
 * Desired outputs after each year:
 * //Year,
 * //initial principal,
 * //interest earned for the year,
 * //principal in end.
 *
 */
public class InterestCalculator {

    public static void main(String[] args) {

        //Desired inputs
        float annualInterestRate;
        float principal;
        float yearsToStayInFund;

        //Utility variables
        float quarterlyInterestRate;
        float previousPrincipal;

        //Take inputs
        principal = takeValidFloatInput("How much money do you want to invest?");
        yearsToStayInFund = takeValidFloatInput("How many years are you investing?");
        annualInterestRate = takeValidFloatInput("What is the annual interest rate % growth?");

        //Calculate quarterly interest rate
        quarterlyInterestRate = annualInterestRate / 4f;

        //Calculations per year
        System.out.println("Calculating...");
        for (int i = 1; i <= yearsToStayInFund; i++) {

            //Calculate principal at end, and earnings
            previousPrincipal = principal;
            principal = (float) (principal * Math.pow(1 + (quarterlyInterestRate / 100), 4));

            //Output year summary
            System.out.printf("Year %d:\n", i);
            System.out.printf("Began with $%.2f\n", previousPrincipal);
            System.out.printf("Earned $%.2f\n", (principal - previousPrincipal));
            System.out.printf("Ended with $%.2f\n", principal);
            System.out.println();
        }
    }

    /**
     * Take int input, ensuring its validity.
     * @param question The question to ask
     * @return The valid user int input
     */
    private static float takeValidFloatInput(String question) {

        Scanner inputReader = new Scanner(System.in);

        //Desired input
        float number;

        //Try take input until valid.
        do {
            System.out.print(question + " ");
            try {
                number = inputReader.nextFloat();
                return number; //Return valid input.

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again");
            }

        } while(true);

    }
}
