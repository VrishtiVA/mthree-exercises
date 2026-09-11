package com.mthree.c458.vrishti.classroster.service;

import com.mthree.c458.vrishti.classroster.dao.ClassRosterPersistenceException;
import com.mthree.c458.vrishti.classroster.dto.Student;

import java.util.List;

/**
 * Services that will be used by the controller.
 */
public interface ClassRosterServiceLayer {

    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;

    List<Student> getAllStudents() throws
            ClassRosterPersistenceException;

    Student getStudent(String studentId) throws
            ClassRosterPersistenceException;

    Student removeStudent(String studentId) throws
            ClassRosterPersistenceException;
}
