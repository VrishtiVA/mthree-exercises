package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dto;

import java.util.List;

public class Student {

    //Attributes
    private long id;
    private String firstName;
    private String lastName;

    /* ----- Constructors ----- */

    public Student() {}

    public Student(long id) {
        this.id = id;
    }

    public Student(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters
    public long getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}

    //Setters
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

}
