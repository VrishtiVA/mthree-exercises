package com.mthree.academy.c458.vrishti.foundations.flowcontrol.fors;

import java.util.Scanner;

public class ForTimesFor {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        //Desired inputs
        int table;
        int answer;

        //Desired output
        int score = 0;

        //Ask which table
        System.out.print("Which times table shall I recite? ");
        table = inputReader.nextInt();
        System.out.println();

        //Loop for each question
        for (int i = 1; i <= 15; i++) {

            //Ask question
            System.out.printf("%d * %d is: ", table, i);
            answer = inputReader.nextInt();

            //Check if correct
            if (answer == table * i) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Sorry no, the answer is: " + (table * i));
            }
        }

        //Final score output
        System.out.println();
        System.out.printf("You got %d correct.\n", score);

        //Bonus message
        float grade = score / 15f;
        if (grade > 0.9f) {
            System.out.println("Well done.");
        } else if (grade < 0.5f) {
            System.out.println("You should study more.");
        }

    }
}
