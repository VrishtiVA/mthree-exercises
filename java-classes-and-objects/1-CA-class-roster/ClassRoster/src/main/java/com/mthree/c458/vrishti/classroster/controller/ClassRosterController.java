package com.mthree.c458.vrishti.classroster.controller;

import com.mthree.c458.vrishti.classroster.ui.ClassRosterView;
import com.mthree.c458.vrishti.classroster.ui.UserIO;
import com.mthree.c458.vrishti.classroster.ui.UserIOConsoleImpl;

/**
 * This is the orchestrator of the application. It knows what needs to be done, when it needs to be done, and what component can do the job.
 */
public class ClassRosterController {

    private ClassRosterView view = new ClassRosterView();
    private UserIO io = new UserIOConsoleImpl();

    /**
     * Ask for user selection and route the request to a private controller method.
     */
    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;

        do {
            //Take user menu choice
            menuSelection = getMenuSelection();

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

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

}
