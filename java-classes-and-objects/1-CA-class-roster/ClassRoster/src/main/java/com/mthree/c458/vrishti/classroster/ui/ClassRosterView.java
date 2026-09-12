package com.mthree.c458.vrishti.classroster.ui;

import com.mthree.c458.vrishti.classroster.dto.Student;

import java.util.List;

/**
 * This class handles all the UI logic.
 */
public class ClassRosterView {

    //- Composition
    //- Coding to the interface, as ClassRosterView is unaware that it's writing/reading from the console.
    //  It only knows its interacting with the user.
    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {

        //Desired input
        int menuSelection;

        //Show the menu
        io.print(
            "\nMain Menu" +
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

    /**
     * Get information from the user to create a student object.
     * @return A newly created student object.
     */
    public Student getNewStudentInfo() {

        //Gather details
        String studentId = io.readString("Please enter Student ID : ");
        String firstName = io.readString("Please enter First Name : ");
        String lastName = io.readString("Please enter Last Name : ");
        String cohort = io.readString("Please enter Cohort : ");

        //Build new student object
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);

        //Return newly created student object
        return currentStudent;

    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    public void displayCreateStudentSuccessBanner() {
        io.readString("Student successfully created. Please hit enter to continue...");
    }

    public void displayStudentList(List<Student> studentList) {

        //Output formatted student details
        for (Student currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                    currentStudent.getStudentId(),
                    currentStudent.getFirstName(),
                    currentStudent.getLastName()
            );
            io.print(studentInfo);
        }

        io.readString("Please hit enter to continue...");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayStudentBanner() {
        io.print("=== Display Student ===");
    }

    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID : ");
    }

    public void displayStudent(Student student) {
        if (student != null) {
            String studentInfo = String.format("#%s \n%s %s \n%s",
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getCohort()
            );
            io.print(studentInfo);
        } else {
            io.print("No such student.");
        }
    }

    public void displayRemoveStudentBanner() {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveResult(Student studentRecord) {
        if (studentRecord != null) {
            io.print("Student successfully removed");
        } else {
            io.print("No such student");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("GOOD BYE");
    }

    public void displayUnknownCommandBanner() {
        io.print("UNKNOWN COMMAND");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
