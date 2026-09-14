package mthree.academy.c458.vrishti.birthday_calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner inputReader = new Scanner(System.in);

        //Desired input
        String birthdayString;
        LocalDate birthday;

        //Get valid date for birthday
        do {
            try {
                //Ask for birthday
                System.out.print("When's your birthday? (dd/MM/yyyy): ");
                birthdayString = inputReader.nextLine().trim();

                //Try to convert birthday
                birthday = LocalDate.parse(birthdayString, formatter);
                break;

            } catch (DateTimeParseException e) {
                System.out.println("Unable to parse date, try again?");
            }
        } while (true);

        /* ----- Calculations ----- */

        //To tell the day of birthday born
        DayOfWeek dayBorn = BirthdayCalculator.findDayOfWeekFromDate(birthday);

        //To tell the day of birthday this year
        DayOfWeek birthdayThisYear = BirthdayCalculator.findDayOfWeekThisYearFromDate(birthday);

        //To tell today's date and days until next birthday.
        LocalDate today = LocalDate.now();
        Long daysToBirthday = BirthdayCalculator.findDaysToNextDate(birthday);

        //To tell age they will be
        Integer nextAge = BirthdayCalculator.findNextBirthdayAge(birthday);

        //Display
        System.out.println("That means you were born on a " + dayBorn + "!");
        System.out.println("This year it falls on a " + birthdayThisYear + "...");
        System.out.println("And since today is " + today.format(formatter) + ", there's only " + daysToBirthday + " day(s) until the next one!");
        System.out.println("Bet yer excited to be turning " + nextAge);

    }
}
