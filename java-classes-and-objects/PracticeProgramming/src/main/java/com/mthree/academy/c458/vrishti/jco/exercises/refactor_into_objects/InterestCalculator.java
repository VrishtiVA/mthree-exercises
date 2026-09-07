package com.mthree.academy.c458.vrishti.jco.exercises.refactor_into_objects;

import java.util.Scanner;

public class InterestCalculator {

    public void runInterestCalculator() {

        Scanner inputReader = new Scanner(System.in);

        //Desired inputs
        float annualInterestRate;
        float principal;
        float yearsToStayInFund;
        float mode;

        //Utility variables
        float brokenInterestRate;
        float previousPrincipal;

        //Take inputs
        principal = takeValidFloatInput(inputReader, "How much money do you want to invest?");
        yearsToStayInFund = takeValidFloatInput(inputReader, "How many years are you investing?");
        annualInterestRate = takeValidFloatInput(inputReader, "What is the annual interest rate % growth?");
        inputReader.nextLine(); //Absorb line.

        //Take mode input
        do {
            System.out.print("Do you want to apply your interest quarterly, monthly, or daily? ");
            try {
                switch (inputReader.nextLine().substring(0, 1).toUpperCase()) {
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
    private static float takeValidFloatInput(Scanner inputReader, String question) {

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

        } while (true);

    }
}
