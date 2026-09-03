package com.mthree.academy.c458.vrishti.foundations.flowcontrol.whiles;

import java.util.Scanner;

public class DoOrDoNot {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Should I do it? (y/n) ");
        boolean doIt;

        if (input.next().equals("y")) {
            doIt = true; // DO IT!
        } else {
            doIt = false; // DONT YOU DARE!
        }

        boolean iDidIt = false;

        do {
            iDidIt = true;
            break; //It will never loop, always just runs once.
        } while (doIt);
        //If the above was just a while loop, it will only become did if told to.

        if (doIt && iDidIt) {
            System.out.println("I did it!"); //Prints this if you tell to do it.
        } else if (!doIt && iDidIt) {
            //Prints this if you tell it not to
            System.out.println("I know you said not to ... but I totally did anyways.");
        } else {
            //Prints this if just had a while loop and tell not to
            System.out.println("Don't look at me, I didn't do anything!");
        }
    }
}
