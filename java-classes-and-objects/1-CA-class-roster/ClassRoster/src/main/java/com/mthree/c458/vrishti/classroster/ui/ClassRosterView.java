package com.mthree.c458.vrishti.classroster.ui;

/**
 * This class handles all the UI logic.
 */
public class ClassRosterView {

    //- Composition
    //- Coding to the interface, as ClassRosterView is unaware that it's writing/reading from the console.
    //  It only knows its interacting with the user.
    public UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection() {

        //Desired input
        int menuSelection;

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

        //Return selection
        return menuSelection;
    }

}
