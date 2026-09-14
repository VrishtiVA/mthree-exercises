package mthree.academy.c458.vrishti.birthday_calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BirthdayCalculator {

    public static DayOfWeek findDayOfWeekFromDate(LocalDate date) {
        return date.getDayOfWeek();
    }

    public static LocalDate findDateThisYearFromDate(LocalDate date) {
        int counter = 0;
        do {
            try {
                LocalDate thisYearsDate = LocalDate.of(LocalDate.now().getYear() + counter, date.getMonth(), date.getDayOfMonth());
                return thisYearsDate;
            } catch (Exception e) {
                counter ++;
            }
        } while (true);
    }

    public static DayOfWeek findDayOfWeekThisYearFromDate(LocalDate date) {
        return findDateThisYearFromDate(date).getDayOfWeek();
    }

    public static LocalDate findNextDate(LocalDate date) {

        //Find next birthdate
        LocalDate nextBirthday = findDateThisYearFromDate(date);
        if (nextBirthday.isBefore(LocalDate.now())) {
            //Next birthday is next year.
            nextBirthday = nextBirthday.plusYears(1);
        }

        return nextBirthday;
    }

    public static Long findDaysToNextDate(LocalDate date) {

        //Find next birthdate
        LocalDate nextDate = findNextDate(date);

        //Calculate Period
        return ChronoUnit.DAYS.between(LocalDate.now(), nextDate);
    }

    public static Integer findNextBirthdayAge(LocalDate birthday) {

        //Find next birthday
        LocalDate nextBirthday = findNextDate(birthday);

        //Calculate age
        return birthday.until(nextBirthday).getYears();
    }
}
