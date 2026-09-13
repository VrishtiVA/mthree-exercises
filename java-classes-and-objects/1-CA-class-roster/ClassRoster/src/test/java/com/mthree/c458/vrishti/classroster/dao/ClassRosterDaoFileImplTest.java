package com.mthree.c458.vrishti.classroster.dao;

import com.mthree.c458.vrishti.classroster.dto.Student;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ClassRosterDaoFileImplTest {

    ClassRosterDao testDao;

    public ClassRosterDaoFileImplTest() {}

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";

        //Blank the file quickly, ready for test.
        new FileWriter(testFile);

        //Starting with empty fresh DAO object.
        testDao = new ClassRosterDaoFileImpl(testFile);
    }

//    @Test
//    public void testSomeMethod() {
//        fail("This test case is a prototype");
//    }

    /*
    Stuff to think about
    - Parameters.
    - Everything method says it does.
    - Errors it could throw.
    Recheck assumptions when beginning testing process.
     */

    @Test
    public void testAddGetStudent() throws Exception {

        //Arrange: Create a new Student object
        String studentId = "0001";
        Student student = new Student(studentId);
        student.setFirstName("Ada");
        student.setLastName("Lovelace");
        student.setCohort("Java-May-1845");

        //Act: Add student to DAO
        testDao.addStudent(studentId, student);
        //Act: Get student from DAO (to confirm it actually worked)
        Student retrievedStudent = testDao.getStudent(studentId);

        //Assert: Check data we stored is equal to data retrieved.
        assertEquals(student.getStudentId(), retrievedStudent.getStudentId(), "Checking Student ID.");
        assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "Checking Student First Name.");
        assertEquals(student.getLastName(), retrievedStudent.getLastName(), "Checking Student Last Name.");
        assertEquals(student.getCohort(), retrievedStudent.getCohort(), "Checked Student Cohort.");
    }

    @Test
    public void testAddGetAllStudents() throws Exception {

        //Arrange: Create 2 Students
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        //Act: Add both Students to DAO
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);
        testDao.addStudent(secondStudent.getStudentId(), secondStudent);
        //Act: Retrieve all students
        List<Student> allStudents = testDao.getAllStudents();

        //Assert: Check general contents of List
        assertNotNull(allStudents, "The list of students must not be null.");
        assertEquals(2, allStudents.size(), "List of students should have 2 students");
        //Assert: Check specific contents of List
        assertTrue(testDao.getAllStudents().contains(firstStudent)); //Will work thanks to overriding hashcode
        assertTrue(testDao.getAllStudents().contains(secondStudent));
    }

    @Test
    public void testRemovesStudent() throws Exception {

        //Arrange: Create 2 new students
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        //Add both to DAO
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);
        testDao.addStudent(secondStudent.getStudentId(), secondStudent);

        //Act: Remove first student
        Student removedStudent = testDao.removeStudent(firstStudent.getStudentId());
        //Act: Get all students
        List<Student> allStudents = testDao.getAllStudents();

        //Assert: Check correct object was removed
        assertEquals(removedStudent, firstStudent, "The removed student should be Ada.");
        //Assert: Other students intact
        assertNotNull(allStudents, "All students list should not be null.");
        assertEquals(1, allStudents.size(), "All students should only have 1 student.");
        assertFalse(allStudents.contains(firstStudent), "All students should NOT contain Ada.");
        assertTrue(allStudents.contains(secondStudent), "All students should NOT include Charles.");

        /* -------------------------------------------------- */

        //Act: Remove second student
        removedStudent = testDao.removeStudent(secondStudent.getStudentId());
        //Act: Get all students
        allStudents = testDao.getAllStudents();

        //Assert: That correct object was removed.
        assertEquals(removedStudent, secondStudent, "The removed student should be Charles.");
        //Assert: No students remaining
        assertTrue(allStudents.isEmpty(), "The retrieved list of students should be empty.");

        /* -------------------------------------------------- */

        //Act: Try to get first student
        Student retrievedStudent = testDao.getStudent(firstStudent.getStudentId());

        //Assert: Cannot retrieve first student
        assertNull(retrievedStudent, "Ada was removed, should be null.");

        /* -------------------------------------------------- */

        //Act: Try to get first student
        retrievedStudent = testDao.getStudent(secondStudent.getStudentId());

        //Assert: Cannot retrieve second student
        assertNull(retrievedStudent, "Charles was removed, should be null.");

    }
}