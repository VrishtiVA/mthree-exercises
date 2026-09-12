package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dao;

import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dto.Student;

import java.util.*;

public class StudentQuizScoresDaoMapImpl implements StudentQuizScoresDao {

    private Map<Long, Student> students = new HashMap<>();
    private Map<Long, List<Double>> studentQuizScores = new HashMap<>();

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public boolean addStudent(Student student) {

        Student previousStudent = students.put(student.getId(), student);

        //Only put quiz scores if no previous student to override here
        if (previousStudent == null) {
            studentQuizScores.put(student.getId(), new ArrayList<>());
        }

        return previousStudent == null;
    }

    @Override
    public boolean addQuizScore(long studentId, double score) {

        if (studentQuizScores.containsKey(studentId)) {
            studentQuizScores.get(studentId).add(score);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeStudent(long studentId) {
        if (students.containsKey(studentId)) {
            students.remove(studentId);
            studentQuizScores.remove(studentId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Double> getStudentQuizScores(long studentId) {

        return studentQuizScores.get(studentId);
    }

    @Override
    public Double getStudentAverageQuizScore(long studentId) {

        //Get scores
        List<Double> scores = studentQuizScores.get(studentId);

        //Return null if no scores
        if (scores == null || scores.isEmpty()) return null;

        //Find average score
        double totalAverage = 0;
        for (Double score : scores) {
            totalAverage += score;
        }
        totalAverage /= scores.size();

        //Return average
        return totalAverage;
    }

    @Override
    public List<Student> getStudentsWithHighestScore(int quizNo) {

        //Desired output
        List<Student> highScorers = new ArrayList<>();

        //Find what is the highest score
        double maxScore = 0;
        for (List<Double> scores : studentQuizScores.values()) {
            maxScore = Math.max(maxScore, scores.get(quizNo));
        }

        //Find students who have this max score
        for (Student student : getAllStudents()) {
            if (studentQuizScores.get(student.getId()).get(quizNo) == maxScore)
                highScorers.add(student);
        }

        //Return high scorers
        return highScorers;
    }

    @Override
    public List<Student> getStudentsWithLowestScore(int quizNo) {

        //Desired output
        List<Student> lowScorers = new ArrayList<>();

        //Find what is the lowest score
        double minScore = 0;
        for (List<Double> scores : studentQuizScores.values()) {
            minScore = Math.min(minScore, scores.get(quizNo));
        }

        //Find students who have this low score
        for (Student student : getAllStudents()) {
            if (studentQuizScores.get(student.getId()).get(quizNo) == minScore)
                lowScorers.add(student);
        }

        //Return low scorers
        return lowScorers;
    }

}
