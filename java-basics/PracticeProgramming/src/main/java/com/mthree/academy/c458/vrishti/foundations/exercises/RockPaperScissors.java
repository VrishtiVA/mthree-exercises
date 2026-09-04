package com.mthree.academy.c458.vrishti.foundations.exercises;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);
        Random randomGenerator = new Random();
        String[] CHOICES = new String[]{"Rock", "Paper", "Scissors"};

        //Desired inputs
        int rounds;

        //Tracking variables
        int wins = 0;
        int ties = 0;
        int losses = 0;

        //Utility variables
        int userChoice;
        int computerChoice;
        int roundResult;
        String overallWinner;

        //Play loop, using do-while to attempt play at least once.
        do {

            //Ask for number of rounds to play
            rounds = takeValidIntInput(inputReader, "How many rounds would you like to play?", 1, 10, true);
            System.out.println();

            //Quit early if requested rounds was out-of-range.
            if (rounds == -1) break;

            //Play number of rounds
            for (int i = 0; i < rounds; i++) {

                //User and computer choice
                userChoice = takeValidIntInput(inputReader, "Select: Rock (1), Paper (2), or Scissors (3)?", 1, 3, false);
                computerChoice = randomGenerator.nextInt(3);
                System.out.printf("I picked %s. ", CHOICES[computerChoice]);

                //Evaluate result from choices, passing both in 0-index form
                roundResult = evaluateRockPaperScissors(userChoice - 1, computerChoice);

                //Track result
                switch (roundResult) {
                    case -1:
                        System.out.println("Haha! You lost.");
                        losses++;
                        break;
                    case 0:
                        System.out.println("A tie!");
                        ties++;
                        break;
                    case 1:
                        System.out.println("You won!");
                        wins++;
                        break;
                }
            }

            //Calculate overall result
            if (wins == losses) {
                overallWinner = "No One";
            } else {
                overallWinner = (wins > losses) ? "YOU" : "COMPUTER";
            }

            //Output results
            System.out.println("That was the end of the round.");
            System.out.println();
            System.out.println(
                "Here are your results: " +
                "\nWINS: " + wins +
                "\nTIES: " + ties +
                "\nLOSSES: " + losses +
                "\nOVERALL WINNER: " + overallWinner
            );
            System.out.println();

            //Ask if user would like to play again.
            if (!askYesNoQuestion(inputReader, "Would you like to play again?")) {
                System.out.println();
                System.out.println("Thanks for playing!");
                break; //Exit play loop if not.
            }

            //Reset variables for replay
            wins = ties = losses = 0;

        } while (true);

        System.out.println("The End.");
    }

    /**
     * A method to evaluate user result in rock paper scissors.
     * Parameters are 0-indexed in this method (Rock = 0, Paper = 1, Scissors = 2).
     * <br/>
     *
     * The way it works is,
     * <ul>
     *     <li> if same choice -> tie, </li>
     *     <li> if the users choice was 1 higher than the computers choice -> the user wins, </li>
     *     <li> if the users choice was 1 lower than the computers choice -> the computer wins, </li>
     * </ul>
     * <br/>
     *
     * Modulus is used to allow this, cyclically in universe 3 (0 to 2). e.g.
     * <ul>
     *  <li> if computer picked R/0, and user picked one greater P/1, user wins, otherwise computer wins. </li>
     *  <li> if computer picked P/1, and user picked one greater S/2, user wins, otherwise computer wins. </li>
     *  <li> if computer picked S/2, and user picked one greater (2+1 = 3, 3%3 = 0) R/0, user wins, otherwise computer wins. </li>
     * </ul>
     *
     * @param userChoice users choice as a 0-indexed integer.
     * @param computerChoice computers choice as a 0-indexed integer.
     * @return 1 if user wins, 0 if a tie, -1 if user losses.
     */
    private static int evaluateRockPaperScissors(int userChoice, int computerChoice) {

        if (userChoice == computerChoice) {
            return 0; //Tie
        } else if (computerChoice == (userChoice + 1) % 3) {
            return -1; //Loss
        } else {
            return 1; //Win
        }

    }

    /**
     * Method to collect a valid integer input that is in bounds.
     * The method also vocalizes when you do something wrong.
     * @param inputReader A scanner object
     * @param question The question to ask for this input.
     * @param lowerBound The inclusive lower bound.
     * @param upperBound The inclusive upper bound.
     * @param checkRangeQuitEarly If indicated as true, method will return -1 if first try is out of range.
     * @return A valid integer
     */
    private static int takeValidIntInput(Scanner inputReader, String question, int lowerBound, int upperBound, boolean checkRangeQuitEarly) {

        //Desired input
        int number;

        //Repeatedly ask until valid input is provided.
        do {
            try {
                //Take user input
                System.out.print(question + ": ");
                number = inputReader.nextInt();

                //Range validation
                if (number < lowerBound || number > upperBound) {
                    //If method parameter indicates quit early, return -1, otherwise try again.
                    if (checkRangeQuitEarly) {
                        System.out.printf("Your input: %d, was out of range.\n", number);
                        return -1;
                    } else {
                        System.out.printf("Invalid input. The number should be in range %d <= x <= %d\n", lowerBound, upperBound);
                        continue; //Try again
                    }
                }

                //If reached here, return valid integer found.
                return number;

            } catch (InputMismatchException ex) { //Using the exception thrown by scanner nextInt()
                System.out.printf("Invalid input. The input should be a number in range %d <= x < %d\n", lowerBound, upperBound);
            } finally {
                //Ensuring full line was read so can move on, preventing issues in subsequent reads.
                inputReader.nextLine();
            }
        } while (true);

    }

    /**
     * Method to ask a YES/NO question.
     * The user input could take a variety of forms, e.g. y/n, Yes/No.
     * But as long as it starts with y, it will be considered as a Yes.
     * @param inputReader A scanner object
     * @param question The relevant question to ask
     * @return true if yes, otherwise no.
     */
    private static boolean askYesNoQuestion(Scanner inputReader, String question) {

        //Desired input
        String input;

        //Ask question and collect user input
        System.out.print(question + " (y/n): ");
        input = inputReader.nextLine().trim();

        //Only consider first letter, using Math.min() to protect against empty string error w/substring
        input = input.substring(0, Math.min(input.length(), 1));

        //Return true if started with Y
        return input.equalsIgnoreCase("Y");

    }

}
