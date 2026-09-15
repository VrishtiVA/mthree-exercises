package com.mthree.c458.vrishti.classroster.dto;

import java.util.Objects;

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

    /* ----- Other Supporting Methods ----- */

    /**
     * Useful for testing, as will allow asserting equality of whole Student objects.
     * @param o   the reference object with which to compare.
     * @return true if equal contents, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(studentId, student.studentId)
                && Objects.equals(cohort, student.cohort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, studentId, cohort);
    }

    /**
     * Allow us to print out all the object's property values.
     * This can allow for faster insight into issues when reading test logs!
     * @return String format of object.
     */
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", cohort='" + cohort + '\'' +
                '}';
    }
}
