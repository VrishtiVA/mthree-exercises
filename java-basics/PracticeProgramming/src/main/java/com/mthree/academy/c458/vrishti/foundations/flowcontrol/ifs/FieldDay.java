package com.mthree.academy.c458.vrishti.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class FieldDay {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        //Desired input
        String name;

        //Desired output
        String team;

        //Take name
        System.out.print("What's your last name? ");
        name = inputReader.nextLine();

        //Figure out team
        if (name.compareTo("Baggins") <= 0) {
            team = "Red Dragons";
        } else if (name.compareTo("Dresden") <= 0) {
            team = "Dark Wizards";
        } else if (name.compareTo("Howl") <= 0) {
            team = "Moving Castles";
        } else if (name.compareTo("Potter") <= 0) {
            team = "Golden Snitches";
        } else if (name.compareTo("Vimes") <= 0) {
            team = "Night Guards";
        } else {
            team = "Black Holes";
        }

        //Output team to user
        System.out.printf("Aha! You're on the team \"%s\"!\n", team);

        //Fair well
        System.out.println("Good luck in the games!");
    }
}
