package com.mthree.academy.c458.vrishti.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class YourLifeInMovies {
    public static void main(String[] args) {

        String name;
        int age;

        Scanner inputReader = new Scanner(System.in);

        //Take in user inputs
        System.out.print("Hey, let's play a game! What's your name? ");
        name = inputReader.nextLine();
        System.out.println();
        System.out.printf("Okay, %s, when were you born? ", name);
        age = inputReader.nextInt();
        System.out.println();

        //Roast them conditionally
        //Easiest way would be individual if statements.
        //Not using switch case with fall-through property since not discrete enough scenario.
        System.out.printf("Well %s...\n", name);
        if (age < 2005) {
            System.out.println("Did you know that Pixar's 'Up' came out over a decade ago?");
        }
        if (age < 1995) {
            System.out.println("And that the first Harry Potter came out over 15 years ago!");
        }
        if (age < 1985) {
            System.out.println("Also, Space Jam came out not last decade, but the one before THAT.");
        }
        if (age < 1975) {
            System.out.println("The original Jurassic Park release is closer to the first lunar landing than it is to today");
        }
        if (age < 1965) {
            System.out.println("The MASH TV series has been around for almost half a century!");
        }

    }
}
