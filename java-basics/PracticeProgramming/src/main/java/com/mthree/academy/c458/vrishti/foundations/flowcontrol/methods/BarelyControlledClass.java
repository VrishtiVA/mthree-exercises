package com.mthree.academy.c458.vrishti.foundations.flowcontrol.methods;

import java.util.Random;

public class BarelyControlledClass {
    public static void main(String[] args) {

        String color = chooseRandomColor(); // call color method here
        String animal = chooseRandomAnimal(); // call animal method again here
        String colorAgain = chooseRandomColor(); // call color method again here
        float weight = chooseRandomNumber(5, 200); // call number method,
        // with a range between 5 - 200?
        float distance = chooseRandomNumber(10, 20); // call number method,
        // with a range between 10 - 20
        float number = chooseRandomNumber(10000, 20000); // call number method,
        // with a range between 10000 - 20000
        float time = chooseRandomNumber(2, 6); // call number method,
        // with a range between 2 - 6

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
                + weight + "lb " + "miniature " + animal
                + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
                + number + " " + colorAgain + " poppies for nearly "
                + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
                + "let me tell you!");
    }

    private static String chooseRandomColor() {
        Random randomGenerator = new Random();
        String[] COLORS = new String[]{"magenta", "yellow", "cyan", "indigo", "lime"};
        return COLORS[randomGenerator.nextInt(COLORS.length)];
    }

    private static String chooseRandomAnimal() {
        Random randomGenerator = new Random();
        String[] ANIMALS = new String[]{"lion", "yetti", "zebra", "unicorn", "whale"};
        return ANIMALS[randomGenerator.nextInt(ANIMALS.length)];
    }

    private static float chooseRandomNumber(float lb, float ub) {
        Random randomGenerator = new Random();
        return randomGenerator.nextFloat() * (ub - lb) + lb;
    }

    // Note: Kind of slow like this.
}
