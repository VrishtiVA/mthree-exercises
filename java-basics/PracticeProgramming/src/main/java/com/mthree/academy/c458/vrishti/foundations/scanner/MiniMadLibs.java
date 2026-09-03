package com.mthree.academy.c458.vrishti.foundations.scanner;

import java.util.Scanner;

public class MiniMadLibs {

    /**
     * Thoughts about trade-offs in where this object is created:
     * Say making a method to collect user input, what's best.
     * - Pass scanner object in, potentially higher coupling.
     * - Create scanner object there, memory inconvenience when they could just use same scanner.
     * - Create constant: final static scanner object and sit lazily -> scanner wouldn't be released when not used.
     */
    final static Scanner inputReader = new Scanner(System.in);

    public static void main(String[] args) {

//        Scanner inputReader = new Scanner(System.in);

        //Desired inputs
        String _1, _2, _3, _4, _5, _6, _7, _8, _9, _10;

        //Introduction
        System.out.println("Let's play MAD LABS!");
        System.out.println();

        //Collect all inputs
        //I would just use an array but I'm holding back.
        _1 = takeStringInput(inputReader, "I need a noun");
        _2 = takeStringInput(inputReader, "Now an adjective");
        _3 = takeStringInput(inputReader, "Another noun");
        _4 = takeStringInput(inputReader, "And a number");
        _5 = takeStringInput(inputReader, "Another adjective");
        _6 = takeStringInput(inputReader, "A plural noun");
        _7 = takeStringInput(inputReader, "Another one");
        _8 = takeStringInput(inputReader, "One more");
        _9 = takeStringInput(inputReader, "A verb (infinitive form)");
        _10 = takeStringInput(inputReader, "Same verb (past participle)");

        //Output
        System.out.println();
        System.out.println("*** NOW LETS GET MAD (libs) ***");
        System.out.printf(
            "%s: the %s frontier. These are the voyages of the starship %s. Its %s-year mission: to explore strange %s %s, to seek out %s %s and %s %s, to boldly %s, where no one has %s before.\n",
            _1, _2, _3, _4, _5, _6, _5, _7, _5, _8, _9, _10
        );
    }

    public static String takeStringInput(Scanner scanner, String question) {
        //Ask question
        System.out.printf("%s: ", question);

        //Take and return input
        return scanner.nextLine();
    }
}
