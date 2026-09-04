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

    final static Scanner INPUT_READER = new Scanner(System.in);

    public static void main(String[] args) {

        //Desired inputs
        float annualInterestRate;
        float principal;
        float yearsToStayInFund;
        float mode;

        //Utility variables
        float brokenInterestRate;
        float previousPrincipal;

        //Take inputs
        principal = takeValidFloatInput("How much money do you want to invest?");
        yearsToStayInFund = takeValidFloatInput("How many years are you investing?");
        annualInterestRate = takeValidFloatInput("What is the annual interest rate % growth?");
        INPUT_READER.nextLine(); //Absorb line.

        //Take mode input
        do {
            System.out.print("Do you want to apply your interest quarterly, monthly, or daily? ");
            try {
                switch (INPUT_READER.nextLine().substring(0, 1).toUpperCase()) {
                    case "Q":
                        System.out.println("Applying interest Quarterly");
                        mode = 4f;
                        break;
                    case "M":
                        System.out.println("Applying interest Monthly");
                        mode = 12f;
                        break;
                    case "D":
                        System.out.println("Applying interest Daily");
                        mode = 365.25f;
                        break;
                    default:
                        System.out.println("Applying interest Annually");
                        mode = 1f;
                        break;
                }
                break;
            } catch (Exception ex) {
                System.out.println("Invalid input. Please try again");
            }
        } while (true);
        System.out.println();

        //Calculate quarterly interest rate
        brokenInterestRate = annualInterestRate / mode;

        //Calculations per year
        System.out.println("Calculating...");
        for (int i = 1; i <= yearsToStayInFund; i++) {

            //Calculate principal at end, and earnings
            previousPrincipal = principal;
            principal = (float) (principal * Math.pow(1 + (brokenInterestRate / 100), 4));

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

        //Desired input
        float number;

        //Try take input until valid.
        do {
            System.out.print(question + " ");
            try {
                number = INPUT_READER.nextFloat();
                return number; //Return valid input.

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again");
            }

        } while (true);

    }
}
