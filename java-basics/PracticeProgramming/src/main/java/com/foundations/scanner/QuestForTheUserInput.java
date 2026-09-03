package com.foundations.scanner;

import java.util.Scanner;

public class QuestForTheUserInput {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        String yourName;
        String yourQuest;
        double velocityOfSwallow;

        // Use next line to read strings
        System.out.println("What is your name?");
        yourName = inputReader.nextLine();
        System.out.println("What is your quest?");
        yourQuest = inputReader.nextLine();

        // Use next double to take and parse double itself.
        System.out.println("What is the airspeed velocity of an unladen swallow?");
        velocityOfSwallow = inputReader.nextDouble();

        //Space and output.
        System.out.println();
        System.out.printf("How do you know %.2f is correct, %s?\n", velocityOfSwallow, yourName);
        System.out.printf("Maybe skip answering things about birds and instead go %s", yourQuest);
    }
}
