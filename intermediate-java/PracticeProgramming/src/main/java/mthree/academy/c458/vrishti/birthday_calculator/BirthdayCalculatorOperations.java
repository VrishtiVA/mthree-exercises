package mthree.academy.c458.vrishti.birthday_calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public interface BirthdayCalculatorOperations {

    DayOfWeek findDayOfWeekFromDate(LocalDate date);

    DayOfWeek findDayOfWeekNextYearFromDate(LocalDate date);

    Period findPeriodToNextDate(LocalDate date);

    Integer findNextBirthdayAge(LocalDate birthday);
}
