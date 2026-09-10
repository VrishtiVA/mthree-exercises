package com.mthree.c458.vrishti.classroster.controller;

import com.mthree.c458.vrishti.classroster.ui.UserIO;
import com.mthree.c458.vrishti.classroster.ui.UserIOConsoleImpl;

/**
 * This is the orchestrator of the application. It knows what needs to be done, when it needs to be done, and what component can do the job.
 */
public class ClassRosterController {

    public UserIO io = new UserIOConsoleImpl();

    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;

        do {

            //Show the menu
            io.print(
                "Main Menu" +
                "\n1. List Student IDs" +
                "\n2. Create New Student" +
                "\n3. View a Student" +
                "\n4. Remove a Student" +
                "\n5. Exit"
            );

            //Allow user selection
            menuSelection = io.readInt("Please select from the above choices : ", 1, 5);

            //Action user choice
            switch (menuSelection) {
                case 1:
                    io.print("LIST STUDENTS");
                    break;
                case 2:
                    io.print("CREATE STUDENT");
                    break;
                case 3:
                    io.print("VIEW STUDENT");
                    break;
                case 4:
                    io.print("REMOVE STUDENT");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        } while (keepGoing);

        io.print("GOOD BYE");

    }

}
