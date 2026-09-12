package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dto;

import java.util.List;

public class Student {

    //Attributes
    private int id;
    private String firstName;
    private String lastName;
    private List<Double> quizScores;

    /* ----- Constructors ----- */

    public Student() {}

    public Student(int id) {
        this.id = id;
    }

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters
    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public List<Double> getQuizScores() {return quizScores;}

    //Setters
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setQuizScores(List<Double> quizScores) {this.quizScores = quizScores;}

}
