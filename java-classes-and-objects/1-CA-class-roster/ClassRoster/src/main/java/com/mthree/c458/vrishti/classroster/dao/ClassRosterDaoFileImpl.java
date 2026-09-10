package com.mthree.c458.vrishti.classroster.dao;

import com.mthree.c458.vrishti.classroster.dto.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the text file-specific implementation of the ClassRosterDao interface.
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {

    //Using a Map for now, since looking up students by ID.
    private Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) {
        //Map .put() can return the previous value mapped to that key, or null if it wasn't.
        Student prevStudent = students.put(studentId, student);
        return prevStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        //Coding to the interface example.
        //This is fine since ArrayList implements List interface, so can be treated as a List.
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) {
        return students.remove(studentId);
    }
}
