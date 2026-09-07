package com.mthree.academy.c458.vrishti.jco.exercises.simple_calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Purpose: Present a simple calculator UI to the user.
 * Handle all user input and output.
 *
 * How should the calculator UI work?
 * 1. Present a choice of operations (including exit)
 * 2. Provide two operands
 * 3. Display result
 * 4. Repeat
 *
 * Improvements for later:
 * - Improve to support float operand and results.
 */
public class App {
    public static void main(String[] args) {

        //Utilities
        Scanner inputReader = new Scanner(System.in);
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        StringBuilder stringBuilder = new StringBuilder();

        //Build calculator UI
        String[] operations = simpleCalculator.getCalculatorOperations();
        stringBuilder.append("Select an operation:");
        for (int i = 1; i <= operations.length; i++) {
            stringBuilder.append("\n").append(i).append(": ").append(operations[i-1]);
        }
        stringBuilder.append("\n").append(operations.length + 1).append(": Quit");
        stringBuilder.append("\n").append("Your selection (number): ");
        String operationsMenu = stringBuilder.toString();

        //Desired inputs
        int selectedOperation;
        int leftOperand;
        int rightOperand;

        //Desired outputs
        int result;

        //Do-while to use simple calculator at least one
        do {

            //1. Choice of operations
            selectedOperation = inputValidInt(inputReader, operationsMenu, 1, operations.length + 1);
            System.out.println();

            //If selected to quit.
            if (selectedOperation == operations.length + 1) {
                System.out.println("Quitting...");
                break;
            }

            //2. Provide 2 operands
            leftOperand = inputValidInt(inputReader, "Enter the left operand: ", null, null);
            rightOperand = inputValidInt(inputReader, "Enter the right operand: ", null, null);
            System.out.println();

            //3. Calculate and display result
            result = simpleCalculator.calculate(selectedOperation, leftOperand, rightOperand);
            System.out.printf("%d %s %d = %d\n", leftOperand, operations[selectedOperation - 1], rightOperand, result);
            System.out.println();

        } while (true);

        //Thank you message
        System.out.println("Thank you for using Simple Calculator. \nGoodbye!");

    }

    /**
     * Method to take in valid integer input within provided range.
     * @param inputReader The scanner object to use.
     * @param lowerBound The inclusive lower bound. If null, assuming no lower bound, and no smaller than that of int.
     * @param upperBound The inclusive upper bound. If null, assuming no upper bound, and no larger than that of int.
     * @return The valid integer
     */
    public static int inputValidInt(Scanner inputReader, String question, Integer lowerBound, Integer upperBound) {

        //Desired input
        int input = -1;

        boolean isValid;
        do {
            //Ask question
            System.out.print(question);
            try {
                //Try take input
                input = inputReader.nextInt();

                //Range check
                if ((lowerBound != null && input < lowerBound) || (upperBound != null && input > upperBound)) {
                    isValid = false;
                } else {
                    isValid = true;
                }

            } catch (InputMismatchException ex) {
                isValid = false;
            } finally {
                //Prevent issues for subsequent reads.
                inputReader.nextLine();
            }

            //Respond depending on validity
            if (isValid) {
                return input;
            } else {
                System.out.printf("Invalid input. You should enter an integer, x, where %d <= x <= %d.\n", lowerBound, upperBound);
                System.out.println();
            }

        } while (true);

    }
}
