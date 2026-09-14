package mthree.academy.c458.vrishti.birthday_calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        //Desired input
        String birthdayString;
        LocalDate birthday;

        //Get valid date for birthday
        do {
            try {
                //Ask for birthday
                System.out.print("When's your birthday?: ");
                birthdayString = inputReader.nextLine();

                //Try to convert birthday
                birthday = LocalDate.parse(birthdayString);
                break;

            } catch (DateTimeParseException e) {
                System.out.println("Unable to parse date, try again?");
            }
        } while (true);

//        //Tell the day of birthday born
//        DayOfWeek dayBorn = 
//
//        //Tell the day of birthday this year
//
//        //Tell the day of birthday next year
//
//        //Tell today's date and days until next birthday.
//
//        //Tell age they will be

    }
}
