package com.mthree.academy.c458.vrishti.foundations.flowcontrol.random;

import java.util.Random;

public class FortuneCookie {

    public static void main(String[] args) {

        //Hold them in the bowl
        String[] FORTUNES = new String[]{
                "Those aren't the droids you're looking for.",
                "Never go in against a Sicilian when death is on the line!",
                "Goonies never say die.",
                "With great power, there must also come — great responsibility.",
                "Never argue with the data.",
                "Try not. Do, or do not. There is no try.",
                "You are a leaf on the wind, watch how you soar.",
                "Do absolutely nothing, and it will be everything that you thought it could be.",
                "Kneel before Zod.",
                "Make it so."
        };

        Random randomGenerator = new Random();
        System.out.println("Your Geek Fortune: " + FORTUNES[randomGenerator.nextInt(FORTUNES.length)]);

    }

}
