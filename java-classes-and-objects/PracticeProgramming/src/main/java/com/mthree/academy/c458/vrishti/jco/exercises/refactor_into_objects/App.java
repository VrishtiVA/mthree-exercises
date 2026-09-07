package com.mthree.academy.c458.vrishti.jco.exercises.refactor_into_objects;

public class App {
    public static void main(String[] args) {

        //Factorizer
        System.out.println("\n\nRunning Factorizer...\n");
        Factorizer factorizer = new Factorizer();
        factorizer.runFactorizer();

        //Interest Calculator
        System.out.println("\n\nRunning Interest Calculator...\n");
        InterestCalculator interestCalculator = new InterestCalculator();
        interestCalculator.runInterestCalculator();

        //Lucky Sevens
        System.out.println("\n\nRunning Lucky Sevens...\n");
        LuckySevens luckySevens = new LuckySevens();
        luckySevens.runLuckySevens();

        //Rock Paper Sissors
        System.out.println("\n\nRunning Rock Paper Sissors...\n");
        RockPaperScissors rockPaperScissors = new RockPaperScissors();
        rockPaperScissors.runRockPaperScissors();

    }
}
