package com.mthree.academy.c458.vrishti.foundations.flowcontrol.random;

import java.util.Random;

public class ALittleChaos {

    public static void main(String[] args) {

        Random randomizer = new Random();

        System.out.println("Random can make integers: " + randomizer.nextInt());
        System.out.println("Or a double: " + randomizer.nextDouble());
        System.out.println("Or even a boolean: " + randomizer.nextBoolean());

        int num = randomizer.nextInt(100);

        System.out.println("You can store a randomised result: " + num);
        System.out.println("And you can use it over and over again: " + num + ", " + num);
        System.out.println("Or just keep generating new values");

        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.print(randomizer.nextInt(101) + ", ");

        //Range 50 to 100.
        //The random generates from [0, 51), then shifting up by 50 so [50, 101).
        System.out.println(randomizer.nextInt(51) + 50);

        //Yes can do maths with random numbers
        //Decimal [0-50) rounded up
        System.out.println(Math.round(randomizer.nextDouble() * 50));

    }

}
