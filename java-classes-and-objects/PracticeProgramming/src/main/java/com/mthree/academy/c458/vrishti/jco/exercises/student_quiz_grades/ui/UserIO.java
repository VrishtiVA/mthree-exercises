package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.ui;

public interface UserIO {

    /**
     * Print a given String to the console.
     * @param message The String value displayed.
     */
    void print(String message);

    /**
     * Display a given message String to prompt the user to enter in a String,
     * then read in the user response as a String.
     * @param prompt The prompt message.
     * @return The String value read in.
     */
    String readString(String prompt);

    /**
     * Display a given message String to prompt the user to enter in an integer,
     * then read in the user response and return that integer value.
     * @param prompt The prompt message value.
     * @return The value that is read in.
     */
    int readInt(String prompt);

    /**
     * Display a prompt to the user to enter an integer between a specified min and max range,
     * and read in an integer.
     * If the user's number does not fall within the range, keep prompting the user for new input until it does.
     * @param prompt The prompt message.
     * @param min The min value.
     * @param max The max value.
     * @return The value read in from the console.
     */
    int readInt(String prompt, int min, int max);

    /**
     * Display a given message String to prompt the user to enter in a double,
     * then read in the user response.
     * @param prompt The prompt message value.
     * @return The double value that is read in.
     */
    double readDouble(String prompt);

    /**
     * Display a prompt to the user to enter a double between a specified min and max range,
     * and read in a double.
     * If the user's number does not fall within the range, keep prompting the user for new input until it does.
     * @param prompt The prompt message.
     * @param min The min value.
     * @param max The max value.
     * @return The value read in from the console.
     */
    double readDouble(String prompt, double min, double max);

    /**
     * Display a given message String to prompt the user to enter a float and
     * then read in the user response.
     * @param prompt The prompt message value.
     * @return The float value that is read in.
     */
    float readFloat(String prompt);

    /**
     * Display a prompt to the user to enter a float between a specified min and max range,
     * and read in a float.
     * If the user's number does not fall within the range, keep prompting the user for new input until it does.
     * @param prompt The prompt message.
     * @param min The min value.
     * @param max The max value.
     * @return The value read in from the console.
     */
    float readFloat(String prompt, float min, float max);

    /**
     * Display a given message String to prompt the user to enter in a long,
     * then read in the user response.
     * @param prompt The prompt message value.
     * @return The long value that is read in.
     */
    long readLong(String prompt);

    /**
     * Display a prompt to the user to enter a long between a specified min and max range,
     * and read in a long.
     * If the user's number does not fall within the range, keep prompting the user for new input until it does.
     * @param prompt The prompt message.
     * @param min The min value.
     * @param max The max value.
     * @return The value read in from the console.
     */
    long readLong(String prompt, long min, long max);

}
