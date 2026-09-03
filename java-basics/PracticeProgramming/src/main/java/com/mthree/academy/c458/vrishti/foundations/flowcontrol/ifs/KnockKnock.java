package com.mthree.academy.c458.vrishti.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class KnockKnock {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Knock Knock! Guess who!! ");
        String nameGuess = inputReader.nextLine();

        //If change to equals, doesn't work. They don't have the same memory reference.
        if (nameGuess.equalsIgnoreCase("Marty McFly")){
            System.out.println("Hey! That's right! I'm back!");
            System.out.println(".... from the Future."); // Sorry, had to!
        } else{
            System.out.println("Dude, do I -look- like " + nameGuess);
        }
    }

}
