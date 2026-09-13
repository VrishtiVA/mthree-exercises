package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dao;

import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dto.Student;

import java.util.List;

public interface StudentQuizScoresDao {

    List<Student> getAllStudents();

    /**
     * Method to add student to system. This should not override another user.
     * @param student The student to add
     * @return true could add, otherwise false.
     */
    boolean addStudent(Student student);

    /**
     * Method to add quiz score for student
     * @param studentId The student to add quiz score to
     * @param score The score to add.
     * @return true could add, otherwise false.
     */
    boolean addQuizScore(long studentId, double score);

    /**
     * Method to remove student from system.
     * @param studentId The student to remove from the system
     * @return true if could, otherwise false
     */
    boolean removeStudent(long studentId);

    List<Double> getStudentQuizScores(long studentId);

    Double getStudentAverageQuizScore(long studentId);

    List<Student> getStudentsWithHighestScore(int quizNo);

    List<Student> getStudentsWithLowestScore(int quizNo);

    int getQuizCount();

}
