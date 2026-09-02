package com.foundations.variables;

public class MoreBucketsMoreFun {
    public static void main(String[] args) {

        // Yay, can declare all one line
        // Sometimes good idea to declare at beginning of program.
        int butterflies, beetles, bugs;

        // Initializing some
        butterflies = 5;
        beetles = 9;
        bugs = butterflies + beetles;

        System.out.printf("There's only %d butterflies,\n", butterflies);
        System.out.println("Oh no, my dog ate one.");
        butterflies--;
        System.out.printf("Now there's only %d butterflies left.\n", butterflies);
        System.out.printf("But there are still %d bugs left.\n", bugs);

    }
}
