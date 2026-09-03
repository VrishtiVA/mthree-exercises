package com.foundations.scanner;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int age;
        int maxHeartRate;
        int lowerBoundHeartRate, upperBoundHeartRate;

        //Ask for users age
        System.out.print("What is your age?: ");
        age = scanner.nextInt();

        //Calculate maximum heart rate
        maxHeartRate = 220 - age;

        //Calculate target heart rate range
        lowerBoundHeartRate = (int) Math.round(maxHeartRate * 0.5);
        upperBoundHeartRate = (int) Math.round(maxHeartRate * 0.85);

        //Output
        System.out.printf("Your maximum heart rate should be %d beats per minute\n", maxHeartRate);
        System.out.printf("Your target HR Zone is %d - %d beats per minute\n", lowerBoundHeartRate, upperBoundHeartRate);
    }
}
