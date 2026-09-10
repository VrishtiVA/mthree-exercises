package com.mthree.c458.vrishti.classroster.controller;

import com.mthree.c458.vrishti.classroster.dao.ClassRosterDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterDaoFileImpl;
import com.mthree.c458.vrishti.classroster.dto.Student;
import com.mthree.c458.vrishti.classroster.ui.ClassRosterView;
import com.mthree.c458.vrishti.classroster.ui.UserIO;
import com.mthree.c458.vrishti.classroster.ui.UserIOConsoleImpl;

/**
 * This is the orchestrator of the application. It knows what needs to be done, when it needs to be done, and what component can do the job.
 */
public class ClassRosterController {

    private ClassRosterView view = new ClassRosterView();
    private UserIO io = new UserIOConsoleImpl();
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

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
                    createStudent();
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

    private void createStudent() {
        //Display banner
        view.displayCreateStudentBanner();
        //Fetch new student from user
        Student newStudent = view.getNewStudentInfo();
        //Store the new student
        dao.addStudent(newStudent.getStudentId(), newStudent);
        //Feedback to the user
        view.displayCreateStudentSuccessBanner();
    }

}
