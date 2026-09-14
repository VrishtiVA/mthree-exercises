package mthree.academy.c458.vrishti.enums;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        Day dayInput = null;
        String dayInputString;
        boolean isValidDay = false;

        //Take valid day input
        do {
            try {
                //Take user input
                System.out.print("Enter a day : ");
                //Has to be exact
                dayInputString = inputReader.nextLine().trim().toUpperCase();

                //Try convert to day
                dayInput = Day.valueOf(dayInputString);
                isValidDay = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid day. Please try again.");
            }
        } while (!isValidDay);

        //Output how many days until Friday
        switch (dayInput) {
            case MONDAY:
                System.out.println("4 days until Friday!");
                break;
            case TUESDAY:
                System.out.println("3 days until Friday!");
                break;
            case WEDNESDAY:
                System.out.println("2 days until Friday!");
                break;
            case THURSDAY:
                System.out.println("1 day until Friday!");
                break;
            case FRIDAY:
                System.out.println("It's Friday!");
                break;
            case SATURDAY:
                System.out.println("6 days until Friday!");
                break;
            case SUNDAY:
                System.out.println("5 days until Friday!");
                break;
            default:
                break;
        }

        /* -------------------------------------------------- */

        IntMath mathematician = new IntMath();

        System.out.println("\nOkay, now lets do maths!");

        //Desired inputs
        int num1 = takeValidIntInput(inputReader, "Enter a number: ");
        int num2 = takeValidIntInput(inputReader, "Enter another number: ");

        //Calculate and display
        System.out.println(
            "\nPLUS: " + mathematician.calculate(MathOperator.PLUS, num1, num2) +
            "\nMINUS: " + mathematician.calculate(MathOperator.MINUS, num1, num2) +
            "\nMULTIPLY: " + mathematician.calculate(MathOperator.MULTIPLY, num1, num2) +
            "\nDIVIDE: " + mathematician.calculate(MathOperator.DIVIDE, num1, num2)
        );

    }

    public static int takeValidIntInput(Scanner inputReader, String prompt) {
        do {
            try {
                System.out.print(prompt + ": ");
                return inputReader.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input, please try again.");
            }
        } while (true);
    }
}
