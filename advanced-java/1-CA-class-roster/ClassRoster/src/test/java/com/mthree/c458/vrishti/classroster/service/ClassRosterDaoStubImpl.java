package com.mthree.c458.vrishti.classroster.service;

import com.mthree.c458.vrishti.classroster.dao.ClassRosterDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterPersistenceException;
import com.mthree.c458.vrishti.classroster.dto.Student;

import java.util.List;

/**
 * Stub Dao. Mocking the Dao.
 * Would have at least 1 student in this case.
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao {

    public Student onlyStudent;

    public ClassRosterDaoStubImpl() {
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("Ada");
        onlyStudent.setLastName("Lovelace");
        onlyStudent.setCohort("Java-May-1845");
    }

    public ClassRosterDaoStubImpl(Student testStudent) {
        this.onlyStudent = testStudent;
    }

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        return onlyStudent.getStudentId().equals(studentId) ? onlyStudent : null;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return List.of(onlyStudent);
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return onlyStudent.getStudentId().equals(studentId) ? onlyStudent : null;
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        return onlyStudent.getStudentId().equals(studentId) ? onlyStudent : null;
    }

}
