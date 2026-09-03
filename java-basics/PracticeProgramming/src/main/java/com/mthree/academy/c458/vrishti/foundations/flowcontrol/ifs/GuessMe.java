package com.mthree.academy.c458.vrishti.foundations.flowcontrol.ifs;

import java.util.Random;
import java.util.Scanner;

public class GuessMe {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);
        Random randomGenerator = new Random();

        //It asks me to choose, but I want to try random :D
        int randomNumber = randomGenerator.nextInt(100) + 1;

        //Ask user to guess, and take input.
        //(Exception handling omit for now, should be added though)
        System.out.println("I've chosen a number. Betcha can't guess it!");
        System.out.print("Your guess: ");
        int guess = inputReader.nextInt();
        System.out.println();

        //Compare and output appropriate response
        if (guess == randomNumber) { //Equal
            System.out.println("Wow, nice guess! That was it!");
        } else if (guess < randomNumber) { //Too Low
            System.out.printf("%d? Ha, nice try - too low! I chose %d.\n", guess, randomNumber);
        } else { //Too high
            System.out.printf("%d? Too bad, way too high. I chose %d.\n", guess, randomNumber);
        }

    }
}
