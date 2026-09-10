package com.mthree.c458.vrishti.classroster.dto;

/**
 * This is the DTO that holds all the Student info.
 */
public class Student {

    //Attributes
    private String firstName;
    private String lastName;
    private String studentId;
    // Programming Language + Cohort month/year
    private String cohort;

    //Constructor
    public Student(String studentId) {
        this.studentId = studentId;
    }

    //Getter Methods
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getCohort() {return cohort;}
    //Read-Only
    public String getStudentId() {return studentId;}

    //Setter Methods
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setCohort(String cohort) {this.cohort = cohort;}

}
