package mthree.academy.c458.vrishti.dvd_library.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    //Attributes
    Scanner inputReader;

    //Constructor (Injection)
    public UserIOConsoleImpl() {
        this.inputReader = new Scanner(System.in);
    }

    /* ----- Method Overrides ----- */

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {

        //Desired input
        String userInput;

        //Accept user input after prompt
        System.out.print(prompt);
        userInput = this.inputReader.nextLine();

        //Return read string
        return userInput;
    }

    @Override
    public int readInt(String prompt) {

        //Desired input
        int userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextInt();

                //If reached here, return valid input
                return userInput;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }
        } while (true);

    }

    @Override
    public int readInt(String prompt, int min, int max) {

        //Desired input
        int userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextInt();

                //Range check
                if (userInput < min || userInput > max) {
                    throw new IllegalArgumentException();
                }

            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.printf("Invalid input, it should be %d <= x <= %d. Please try again.\n", min, max);
                continue; //Failed format so retry

            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }

            //If reached here, return valid input.
            return userInput;

        } while (true);
    }

    @Override
    public double readDouble(String prompt) {

        //Desired input
        double userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextDouble();

                //If reached here, return valid input
                return userInput;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }
        } while (true);

    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        //Desired input
        double userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextDouble();

                //Range check
                if (userInput < min || userInput > max) {
                    throw new IllegalArgumentException();
                }

            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.printf("Invalid input, it should be %f <= x <= %f. Please try again.\n", min, max);
                continue; //Failed format so retry

            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }

            //If reached here, return valid input.
            return userInput;

        } while (true);
    }

    @Override
    public float readFloat(String prompt) {

        //Desired input
        float userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextFloat();

                //If reached here, return valid input
                return userInput;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }
        } while (true);

    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        //Desired input
        float userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextFloat();

                //Range check
                if (userInput < min || userInput > max) {
                    throw new IllegalArgumentException();
                }

            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.printf("Invalid input, it should be %f <= x <= %f. Please try again.\n", min, max);
                continue; //Failed format so retry

            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }

            //If reached here, return valid input.
            return userInput;

        } while (true);
    }

    @Override
    public long readLong(String prompt) {

        //Desired input
        long userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextLong();

                //If reached here, return valid input
                return userInput;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again.");
            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }
        } while (true);
    }

    @Override
    public long readLong(String prompt, long min, long max) {

        //Desired input
        long userInput;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInput = this.inputReader.nextLong();

                //Range check
                if (userInput < min || userInput > max) {
                    throw new IllegalArgumentException();
                }

            } catch (InputMismatchException | IllegalArgumentException e) {
                //Added comma separators in long numbers
                System.out.printf("Invalid input, it should be %,d <= x <= %,d. Please try again.\n", min, max);
                continue; //Failed format so retry

            } finally {
                //Absorb rest of line to prevent problems for subsequent reads.
                this.inputReader.nextLine();
            }

            //If reached here, return valid input.
            return userInput;

        } while (true);
    }

    @Override
    public LocalDate readDate(String prompt, boolean isOptional, LocalDate min, LocalDate max) {

        //Desired input
        LocalDate userInput;
        String userInputString;

        //Keep trying until valid input
        do {
            try {
                //Accept user input after prompt
                System.out.print(prompt);
                userInputString = this.inputReader.nextLine().trim();

                //Handle if optional
                if (isOptional) {
                    userInput = userInputString.isBlank() ? null : LocalDate.parse(userInputString, DATE_FORMAT);
                } else {
                    userInput = LocalDate.parse(userInputString, DATE_FORMAT);
                }
                //If provided null since optional, escape now with null
                if (userInput == null) return null;

                //Range check
                if ((min != null && userInput.isBefore(min)) || (min != null && userInput.isAfter(max))) {
                    throw new IllegalArgumentException();
                }

            } catch (DateTimeParseException | IllegalArgumentException e) {
                if (min != null && max != null) {
                    System.out.printf("Invalid input, it should be a valid date in dd-MM-yyyy format between %s and %s. Please try again.\n", min, max);
                } else if (min != null) {
                    System.out.printf("Invalid input, it should be a valid date in dd-MM-yyyy format after %s. Please try again.\n", min);
                } else if (max != null ){
                    System.out.printf("Invalid input, it should be a valid date in dd-MM-yyyy format before %s. Please try again.\n", max);
                } else {
                    System.out.println("Invalid input, it should be a valid date in dd-MM-yyyy format");
                }
                continue;
            }

            //If reached here, return valid input.
            return userInput;

        } while (true);
    }
}
