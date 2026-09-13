package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.ui;

import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dto.Student;

import java.util.List;
import java.util.Scanner;

public class StudentQuizScoresView {

    private Scanner inputReader;
    private UserIO userIO;

    public StudentQuizScoresView(UserIO userIO) {
        this.inputReader = new Scanner(System.in);
        this.userIO = userIO;
    }

    public int printAndGetMenuSelection() {
        userIO.print(
            "\nSelect what you would like to do:" +
            "\n1. View a list of students" +
            "\n2. Add a student" +
            "\n3. Remove a student" +
            "\n4. Add quiz score to student" +
            "\n5. View a list of quiz scores for a given student" +
            "\n6. View average quiz score for a given student" +
            "\n7. View highest scoring student in quiz" +
            "\n8. View lowest scoring student in quiz" +
            "\n9. Quit program"
        );
        return userIO.readInt("Your selection : ", 1, 9);
    }

    public void printWelcomeBanner() {
        userIO.print("\n    _.*= Student Quiz Scores =*._    ");
    }

    public void displayAllStudents(List<Student> students) {
        for (Student student : students) {
            userIO.print("- " + student.getId() + ": " + student.getFirstName() + " " + student.getLastName());
        }
    }

    public void displaySuccessMessage(String message) {
        userIO.print("\nSuccess: " + message);
    }

    public void displayWarnMessage(String message) {
        userIO.print("\nWarning: " + message);
    }

    public Student getNewStudentDetails() {

        //Collect data
        long id = userIO.readInt("Enter Student ID : ");
        String firstName = userIO.readString("Enter First Name : ");
        String lastName = userIO.readString("Enter Last Name : ");

        //Return student object
        return new Student(id, firstName, lastName);
    }

    public long getStudentId() {
        return userIO.readLong("Enter Student ID : ");
    }

    public double getQuizScore() {
        return userIO.readLong("Enter Quiz Score : ");
    }

    public int getQuizNumber(int maxQuizNumber) {
        return userIO.readInt("Enter Quiz Number : ", 1, maxQuizNumber);
    }

    public void displayStudentScores(Long studentId, List<Double> scores) {

        if (scores == null) {
            userIO.print("Student " + studentId + " has no quiz data.");
            return;
        }

        userIO.print("Quiz Scores for Student " + studentId + ":");
        for (int i = 0; i < scores.size(); i++) {
            userIO.print("- Quiz " + (i+1) + ": " + scores.get(i));
        }
    }

    public void displayStudentAverageScore(Long studentId, Double averageScore) {
        userIO.print("Average Quiz Score for Student " + studentId + ": " + (averageScore == null ? "N/A" : averageScore));
    }

    public void displayCategoricQuizScorers(String category, int selectedQuizNumber, List<Student> students) {
        userIO.print(category + " Scorers for Quiz " + selectedQuizNumber + ":");
        for (Student student : students) {
            userIO.print("- " + student.getId() + ": " + student.getFirstName() + " " + student.getLastName());
        }
    }

    public void displayHeader(String header) {
        userIO.print("\n-.__- " + header + " -__.-");
    }

}
