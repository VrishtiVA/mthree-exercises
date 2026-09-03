package com.mthree.academy.c458.vrishti.foundations.flowcontrol.random;

import java.util.Random;
import java.util.Scanner;

public class GuessMeMore {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);
        Random randomGenerator = new Random();

        int count = 0;

        //Generate between -100 and 100
        int randomNumber = randomGenerator.nextInt(201) - 100;

        //Ask to guess until the get it.
        System.out.println("I've chosen a number. Betcha can't guess it!");
        do {
            //Ask user to guess, and take input.
            //(Exception handling omit for now, should be added though)
            System.out.print("Your guess: ");
            int guess = inputReader.nextInt();
            count++; //Increment guesses counter
            System.out.println();

            //Compare and output appropriate response
            if (guess == randomNumber) { //Equal
                System.out.printf("Wow, nice guess! That was it! Took you %d guess(es).", count);
                break;
            } else if (guess < randomNumber) { //Too Low
                System.out.println("Ha, nice try - too low!");
            } else { //Too high
                System.out.println("Too bad, way too high.");
            }
        } while (true);

    }
}
