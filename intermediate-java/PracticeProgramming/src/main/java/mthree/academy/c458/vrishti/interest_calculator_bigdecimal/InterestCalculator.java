package mthree.academy.c458.vrishti.interest_calculator_bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class InterestCalculator {

    public static void main(String[] args) {

        //Utility objects
        Scanner inputReader = new Scanner(System.in);

        //Desired inputs
        BigDecimal annualInterestRate;
        BigDecimal principal;
        float yearsToStayInFund;
        BigDecimal mode;

        //Utility variables
        BigDecimal brokenInterestRate;
        BigDecimal previousPrincipal;

        //Take inputs
        principal = takeBigDecimalInput(inputReader, "How much money do you want to invest?");
        yearsToStayInFund = takeValidFloatInput(inputReader, "How many years are you investing?");
        annualInterestRate = takeBigDecimalInput(inputReader, "What is the annual interest rate % growth?");

        //Take mode input
        do {
            System.out.print("Do you want to apply your interest quarterly, monthly, or daily? ");
            try {
                switch (inputReader.nextLine().substring(0, 1).toUpperCase()) {
                    case "Q":
                        System.out.println("Applying interest Quarterly");
                        mode = new BigDecimal(4);
                        break;
                    case "M":
                        System.out.println("Applying interest Monthly");
                        mode = new BigDecimal(12);
                        break;
                    case "D":
                        System.out.println("Applying interest Daily");
                        mode = new BigDecimal("365.25");
                        break;
                    default:
                        System.out.println("Applying interest Annually");
                        mode = new BigDecimal(1);
                        break;
                }
                break;
            } catch (Exception ex) {
                System.out.println("Invalid input. Please try again");
            }
        } while (true);
        System.out.println();

        //Calculate quarterly interest rate
        brokenInterestRate = annualInterestRate.divide(mode, 2, RoundingMode.HALF_UP);

        //Calculations per year
        System.out.println("Calculating...");
        for (int i = 1; i <= yearsToStayInFund; i++) {

            //Calculate principal at end, and earnings
            previousPrincipal = principal;
            principal = principal.multiply(
                 (brokenInterestRate
                     .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP) //Need to keep some extra places here for maths.
                     .add(BigDecimal.valueOf(1))
                 ).pow(4)
            );
//            principal = (float) (principal * Math.pow(1 + (brokenInterestRate / 100), 4));

            //Output year summary
            System.out.printf("Year %d:\n", i);
            System.out.printf("Began with $%.2f\n", previousPrincipal);
            System.out.printf("Earned $%.2f\n", (principal.subtract(previousPrincipal)));
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
            } finally {
                inputReader.nextLine(); //Absorb to prevent issues
            }

        } while (true);

    }

    private static BigDecimal takeBigDecimalInput(Scanner inputReader, String question) {

        //Desired input
        String number;

        //Desired output
        BigDecimal bigDecimal;

        //Try take input until valid.
        do {
            try {
                System.out.print(question + " ");
                number = inputReader.nextLine();

                bigDecimal = new BigDecimal(number);
                return bigDecimal; //Return valid input.

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again");
            }

        } while (true);

    }
}
