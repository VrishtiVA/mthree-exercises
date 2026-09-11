package com.mthree.c458.vrishti.classroster.controller;

import com.mthree.c458.vrishti.classroster.dao.ClassRosterDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterPersistenceException;
import com.mthree.c458.vrishti.classroster.dto.Student;
import com.mthree.c458.vrishti.classroster.ui.ClassRosterView;

import java.util.List;

/**
 * This is the orchestrator of the application. It knows what needs to be done, when it needs to be done, and what component can do the job.
 */
public class ClassRosterController {

    private ClassRosterView view;
    private ClassRosterDao dao;

    public ClassRosterController(ClassRosterDao dao, ClassRosterView view) {
        this.dao = dao;
        this.view = view;
    }

    /**
     * Ask for user selection and route the request to a private controller method.
     */
    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            do {
                //Take user menu choice
                menuSelection = getMenuSelection();

                //Action user choice
                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            } while (keepGoing);

            exitMessage();

        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        //Fetch new student from user
        Student newStudent = view.getNewStudentInfo();
        //Store the new student
        dao.addStudent(newStudent.getStudentId(), newStudent);
        //Feedback to the user
        view.displayCreateStudentSuccessBanner();
    }

    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        //Fetch list of all students
        List<Student> studentList = dao.getAllStudents();
        //Display them
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayStudentBanner();
        //Fetch student ID choice
        String studentId = view.getStudentIdChoice();
        //Get student
        Student student = dao.getStudent(studentId);
        //Display student
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        //Fetch student ID choice
        String studentId = view.getStudentIdChoice();
        //Remove student
        Student removedStudent = dao.removeStudent(studentId);
        //Check if was removed, by knowing if above returned a student.
        view.displayRemoveResult(removedStudent);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExistBanner();
    }

}
