package com.mthree.academy.c458.vrishti.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class TriviaNight {

    private static class Question {

        //Attributes
        public String question;
        public String[] options;
        public int answer;

        //Constructors
        public Question() {}
        public Question(String question, String[] options, int answer) {
            this.question = question;
            this.options = options;
            this.answer = answer;
        }
    }

    public static void main(String[] args) {

        //Setting up questions
        Question[] QUESTIONS = new Question[]{
                new Question(
                        "What is the Lowest Level Programming Language?",
                        new String[]{
                                "Source Code",
                                "Assembly Language",
                                "C#",
                                "Machine Code"
                        },
                        4
                ),
                new Question(
                        "Website Security CAPTCHA Forms Are Descended From the Work of?",
                        new String[]{
                                "Grace Hopper",
                                "Alan Turing",
                                "Charles Babbage",
                                "Larry Page"
                        },
                        1
                ),
                new Question(
                        "Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?",
                        new String[]{
                                "Serenity",
                                "The Battlestar Galatica",
                                "The USS Enterprise",
                                "The Millenium Falcon"
                        },
                        3
                )
        };

        boolean correct;
        int score = 0;

        //Introduction
        System.out.println("It's TRIVIA NIGHT! Are you ready?!");
        System.out.println();

        //Run Quiz
        for (int i = 0; i < 3; i++) {

            //Ask question and evaluate user
            System.out.printf("Question %d:\n", i+1);
            correct = askQuestion(QUESTIONS[i]);

            //If correct, increment score and let know progress
            if (correct) {
                score++;
                System.out.println("CORRECT! CURRENT SCORE: " + score);
            } else {
                System.out.println("INCORRECT!");
            }
            System.out.println();

        }

        //Final verdict
        if (score == QUESTIONS.length) {
            System.out.println("Nice job - you got all questions correct!");
        } else {
            System.out.printf("You could do better. %d out of %d\n", score, QUESTIONS.length);
        }

    }

    /**
     * Method to ask question and handle response
     * @return true if user is correct, otherwise false
     */
    private static boolean askQuestion(Question question) {

        Scanner inputReader = new Scanner(System.in);

        //Display question
        System.out.println(question.question);

        //Display options
        for (int i = 0; i < question.options.length; i++) {
            //Number is no. spaces used for embedded string.
            System.out.printf("%d) %-25s %s", i+1, question.options[i], (i % 2 == 0) ? "\t" : "\n");
        }

        //Take input
        System.out.println();
        System.out.print("YOUR ANSWER: ");
        int input = inputReader.nextInt();

        //Check correctness and return whether right or not.
        return (input == question.answer);

    }

}
