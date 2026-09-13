package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.controller;

import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dao.StudentQuizScoresDao;
import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dto.Student;
import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.ui.StudentQuizScoresView;

import java.util.List;

public class StudentQuizScoresController {

    private StudentQuizScoresDao dao;
    private StudentQuizScoresView view;

    public StudentQuizScoresController(StudentQuizScoresDao dao, StudentQuizScoresView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        view.printWelcomeBanner();

        boolean keepGoing = true;
        do {
            switch (view.printAndGetMenuSelection()) {
                case 1:
                    viewStudentsList();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    addStudentScore();
                    break;
                case 5:
                    viewStudentScores();
                    break;
                case 6:
                    viewStudentAverageScore();
                    break;
                case 7:
                    viewQuizHighestScorer();
                    break;
                case 8:
                    viewQuizLowestScorer();
                    break;
                case 9:
                    keepGoing = false;
                    break;

                default: break;
            }
        } while (keepGoing);

    }

    private void viewStudentsList() {
        view.displayHeader("Student List");

        //Get all students
        List<Student> students = dao.getAllStudents();

        //Display all students
        view.displayAllStudents(students);
    }

    private void createStudent() {
        view.displayHeader("Create Student");

        //Collect student details
        Student newStudent = view.getNewStudentDetails();

        //Add student to system
        boolean success = dao.addStudent(newStudent);

        //Feedback to user
        if (success) view.displaySuccessMessage("Student has been added.");
        else view.displayWarnMessage("Student could not be added.");
    }

    private void removeStudent() {
        view.displayHeader("Remove Student");

        //Collect student to remove
        long studentId = view.getStudentId();

        //Remove student from system
        boolean success = dao.removeStudent(studentId);

        //Feedback to user
        if (success) view.displaySuccessMessage("Student has been removed.");
        else view.displayWarnMessage("Student could not be removed.");
    }

    private void addStudentScore() {
        view.displayHeader("Add Student Score");

        //Collect student and score
        long studentId = view.getStudentId();
        double quizScore = view.getQuizScore();

        //Add score
        boolean success = dao.addQuizScore(studentId, quizScore);

        //Feedback to user
        if (success) view.displaySuccessMessage("Quiz score has been added for student " + studentId);
        else view.displayWarnMessage("Quiz score could not be added for student " + studentId);
    }

    private void viewStudentScores() {
        view.displayHeader("Student Scores");

        //Collect student
        long studentId = view.getStudentId();

        //Get their scores
        List<Double> scores = dao.getStudentQuizScores(studentId);

        //Display their scores
        view.displayStudentScores(studentId, scores);
    }

    private void viewStudentAverageScore() {
        view.displayHeader("Student Average");

        //Collect student
        long studentId = view.getStudentId();

        //Get their average score
        Double averageScore = dao.getStudentAverageQuizScore(studentId);

        //Display
        view.displayStudentAverageScore(studentId, averageScore);
    }

    private void viewQuizHighestScorer() {
        view.displayHeader("Quiz Highest Scorers");

        //Collect what quiz to view for
        int maxQuizNumber = dao.getQuizCount();
        int selectedQuizNumber = view.getQuizNumber(maxQuizNumber);

        //Get highest scorers
        List<Student> highScorers = dao.getStudentsWithHighestScore(selectedQuizNumber - 1);

        //Display
        view.displayCategoricQuizScorers("Highest", selectedQuizNumber, highScorers);
    }

    private void viewQuizLowestScorer() {
        view.displayHeader("Quiz Lowest Scorers");

        //Collect what quiz to view for
        int maxQuizNumber = dao.getQuizCount();
        int selectedQuizNumber = view.getQuizNumber(maxQuizNumber);

        //Get lowest scorers
        List<Student> lowScorers = dao.getStudentsWithLowestScore(selectedQuizNumber - 1);

        //Display
        view.displayCategoricQuizScorers("Lowest", selectedQuizNumber, lowScorers);
    }

}
