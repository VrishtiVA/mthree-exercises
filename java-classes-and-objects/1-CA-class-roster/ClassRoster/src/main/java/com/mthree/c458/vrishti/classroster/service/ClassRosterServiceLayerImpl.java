package com.mthree.c458.vrishti.classroster.service;

import com.mthree.c458.vrishti.classroster.dao.ClassRosterDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterPersistenceException;
import com.mthree.c458.vrishti.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    ClassRosterDao dao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao) {
        this.dao = dao;
    }

    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {

        //Ensure incoming Student ID does not already exist.
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException("ERROR: Could not create student. Student Id " + student.getStudentId() + " already exists.");
        }

        //Validate that all fields in the incoming Student object have values.
        validateStudentData(student);

        //Pass the incoming Student object to the DAO so that it can persist.
        dao.addStudent(student.getStudentId(), student);
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.removeStudent(studentId);
    }

    /**
     * Every Student in the system must have values for First Name, Last Name, and Cohort.
     * If any of these fields are empty, the Student object should not be persisted.
     */
    private void validateStudentData(Student student) throws ClassRosterDataValidationException {

        //Checking not null, not empty or just white space.
        if (student.getFirstName() == null
            || student.getFirstName().isBlank()
            || student.getLastName() == null
            || student.getLastName().isBlank()
            || student.getCohort() == null
            || student.getCohort().isBlank()
        ) {
            throw new ClassRosterDataValidationException(
                "ERROR: All fields [First Name, Last Name, Cohort] are required."
            );
        }

    }

}
