package com.mthree.academy.c458.vrishti.jco.exercises.state_capitals;

import com.mthree.academy.c458.vrishti.jco.exercises.user_io_class_lab.UserIO;
import com.mthree.academy.c458.vrishti.jco.exercises.user_io_class_lab.UserIOImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StateCapitals2 {
    public static void main(String[] args) throws FileNotFoundException {

        //Utility objects
        //File works when in project root.
        Scanner fileReader = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));
        Random randomGenerator = new Random();
        UserIO userIO = new UserIOImpl();

        //Map to hold states and their capitals.
        Map<String, String> stateCapitals = new HashMap<>();

        //While there are more lines to read
        //Load the hashmap in from the file.
        while (fileReader.hasNextLine()) {
            //Read the line
            String line = fileReader.nextLine();
            //Split by delimiter
            String[] stateCapital = line.split("::");
            //Add the item to the map
            stateCapitals.put(stateCapital[0], stateCapital[1]);
        }

        System.out.println();

        //How many states are there?
        System.out.printf("There are %d states.\n", stateCapitals.size());
        System.out.println();

        //Print all state names
        System.out.println("======================================== All State Names ========================================");
        int counter = 0;
        for (String state : stateCapitals.keySet()) {
            //Start new line every 10 states
            if (counter == 10) {
                System.out.print("\n");
                counter = 0;
            }
            //Print the state
            System.out.print(state);
            counter++;
            //Add comma for all but last.
            if (counter != stateCapitals.size() -1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("=================================================================================================");
        System.out.println();

        //Setup quiz for user
        System.out.println("Are you ready to test you knowledge?");
        int rounds = userIO.readInt("How many states would you like to guess? : ", 0, 50);
        System.out.println();

        //Quiz utility variables
        String[] stateList;
        String selectedState;
        String userAnswer;

        //Quiz trackers
        int score = 0;

        //Play rounds specified
        for (int round = 1; round <= rounds; round++) {
            System.out.println("ROUND " + round);

            //Choose a random state out.
            //Parameter in list is the new array to store in, which will be expanded to fit.
            stateList = stateCapitals.keySet().toArray(new String[0]);
            selectedState = stateList[randomGenerator.nextInt(stateCapitals.size())];

            //Ask question
            userAnswer = userIO.readString("Do you know what the capital of " + selectedState + " is? : ");
            System.out.println();

            //Check solution
            if (userAnswer.equalsIgnoreCase(stateCapitals.get(selectedState))) {
                System.out.printf("Nice work! %s is correct!\n", stateCapitals.get(selectedState));
                score++;
            } else {
                System.out.printf("Nope, it's actually %s.\n", stateCapitals.get(selectedState));
                score--;
            }
            System.out.println();

            //Remove from map to prevent reasking
            stateCapitals.remove(selectedState);
        }

        //Display final score after playing
        if (rounds > 0) {
            System.out.printf("You scored %d points!\n", score);
        }

        //End of program message
        System.out.println("That's the end of this program. Good bye!");

    }
}
