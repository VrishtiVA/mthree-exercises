package com.mthree.academy.c458.vrishti.foundations.exercises;

import java.util.Random;
import java.util.Scanner;

/**
 * Roll dice
 * Sum == 7 -> win $4 else -$1
 */
public class LuckySevens {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);
        Random randomGenerator = new Random();

        //Desired Inputs
        int bet;

        //Tracking Variables
        int rollSum = 0;
        int maxMoney;
        int maxMoneyRolls = 0;
        int rolls = 0;

        //Collect bet input
        System.out.print("How many dollars do you have? ");
        bet = inputReader.nextInt();
        maxMoney = bet;

        //Roll until money give -> while loop
        while (bet > 0) {

            //Roll
            rollSum = randomGenerator.nextInt(6) + 1;
            rollSum += randomGenerator.nextInt(6) + 1;
            rolls++;

            //Result
            if (rollSum == 7) {
                bet += 4;
            } else {
                bet -= 1;
            }

            //See if can improve max
            if (bet > maxMoney) {
                maxMoney = bet;
                maxMoneyRolls = rolls;
            }

        }

        //Output
        System.out.printf("You are broke after %d rolls.\n", rolls);
        System.out.printf("You should have quit after %d rolls when you had $%d.", maxMoneyRolls, maxMoney);

    }
}
