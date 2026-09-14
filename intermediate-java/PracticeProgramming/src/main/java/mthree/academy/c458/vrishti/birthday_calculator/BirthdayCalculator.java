package mthree.academy.c458.vrishti.birthday_calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class BirthdayCalculator implements BirthdayCalculatorOperations {

    @Override
    public DayOfWeek findDayOfWeekFromDate(LocalDate date) {
        return date.getDayOfWeek();
    }

    @Override
    public DayOfWeek findDayOfWeekNextYearFromDate(LocalDate date) {
        LocalDate thisYearsBirthday = LocalDate.of(LocalDate.now().getYear(), date.getMonth(), date.getDayOfMonth());
        return thisYearsBirthday.getDayOfWeek();
    }

    private LocalDate findNextBirthday(LocalDate date) {

        //Find next birthdate
        LocalDate nextBirthday = LocalDate.of(LocalDate.now().getYear(), date.getMonth(), date.getDayOfMonth());
        if (nextBirthday.isBefore(LocalDate.now())) {
            //Next birthday is next year.
            nextBirthday = nextBirthday.plusYears(1);
        }

        return nextBirthday;
    }

    @Override
    public Period findPeriodToNextDate(LocalDate date) {

        //Find next birthdate
        LocalDate nextBirthday = findNextBirthday(date);

        //Calculate Period
        return LocalDate.now().until(nextBirthday);
    }

    @Override
    public Integer findNextBirthdayAge(LocalDate birthday) {

        //Find next birthday
        LocalDate nextBirthday = findNextBirthday(birthday);

        //Calculate age
        return birthday.until(nextBirthday).getYears();
    }
}
