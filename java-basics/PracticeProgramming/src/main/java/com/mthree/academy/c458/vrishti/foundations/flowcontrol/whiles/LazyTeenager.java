package com.mthree.academy.c458.vrishti.foundations.flowcontrol.whiles;

import java.util.Random;

public class LazyTeenager {
    
    /**
     * Notes:
     * - Do-while until room clean.
     * - Parent tells teen to clean room
     * - Increase change by 10%
     * - 7th time, grounded.
    */
    public static void main(String[] args) {

        Random randomGenerator = new Random();

        //Variables
        float chance = 0f;
        int reminders = 1;

        //Do while until room clean or scolded
        do {

            //Tell teen to clean
            System.out.printf("Clean your room!! (x%d)\n", reminders);

            //Increase chance
            chance += 0.1f;

            //Decide if teen will clean or not
            if (randomGenerator.nextDouble() < chance) {
                System.out.println("FINE! I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.");
                break;
            }

            //Increment for next, so 7th time isn't asked here.
            reminders++;

        } while (reminders < 7);

        //Run out of patience
        if (reminders == 7) {
            System.out.println("That's IT, I'm doing it!!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
        }

    }
}
