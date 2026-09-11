package com.mthree.c458.vrishti.classroster.service;

import com.mthree.c458.vrishti.classroster.dao.ClassRosterAuditDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterPersistenceException;
import com.mthree.c458.vrishti.classroster.dto.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Business Rules to test (all enforced in createStudent):
 * 1. Creation of a student with an existing student ID is prohibited
 * 2. All fields on the Student object must have non-empty values
 *
 * Test cases :
 * 1. Create a valid student - all fields have values and the student does not have an existing student ID
 * 2. Create a student with an existing student ID
 * 3. Create a student with one or more empty field values
 */
class ClassRosterServiceLayerImplTest {

    private ClassRosterServiceLayer service;

    public ClassRosterServiceLayerImplTest() {
        //Stub daos to use to make service
        //Daos already got us set up.
        ClassRosterDao dao = new ClassRosterDaoStubImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
        //Create service
        this.service = new ClassRosterServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testCreateValidStudent() {

        //Arrange
        Student student = new Student("0002");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        //Act
        try {
            service.createStudent(student);

        } catch (ClassRosterPersistenceException
                | ClassRosterDuplicateIdException
                | ClassRosterDataValidationException e
        ) {
            //Assert
            fail("Student was valid. No exception should have been thrown.");
        }
    }

    @Test
    public void testCreateStudentDuplicateId() {

        //Arrange - Knowing already have ID 1 stored.
        Student student = new Student("0001");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        //Act
        try {
            service.createStudent(student);
        } catch (ClassRosterPersistenceException | ClassRosterDataValidationException e) {
            //Assert
            fail("Incorrect exception was thrown.");
        } catch (ClassRosterDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testCreateStudentInvalidData() throws Exception {

        //Arrange
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        //Act
        try {
            service.createStudent(student);
            //Assert
            fail("Expected validation exception was not thrown.");
        } catch (ClassRosterPersistenceException | ClassRosterDuplicateIdException e) {
            //Assert
            fail("Incorrect exception was thrown.");
        } catch (ClassRosterDataValidationException e) {
            return;
        }
    }

    @Test
    public void testGetAllStudents() throws Exception {

        //Arrange
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");

        //Act & Assert
        assertEquals(1, service.getAllStudents().size(), "Should only have 1 student.");
        assertTrue(service.getAllStudents().contains(testClone), "The one student should be Ada.");
    }

    @Test
    public void testGetExistingStudent() throws Exception {

        //Arrange
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");

        //Act
        Student retrievedStudent = service.getStudent("0001");

        //Assert
        assertNotNull(retrievedStudent, "Getting 0001 should not be null.");
        assertEquals(testClone, retrievedStudent, "Student stored under 0001 should be Ada.");
    }

    @Test
    public void testGetNonExistingStudent() throws Exception {

        //Act
        Student retrievedStudent = service.getStudent("0042");

        //Assert
        assertNull(retrievedStudent, "Getting 0042 should be null.");
    }

    @Test
    public void testRemoveExistingStudent() throws Exception {

        //Arrange
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");

        //Act
        Student removedStudent = service.removeStudent("0001");

        //Assert
        assertNotNull(removedStudent, "Removing 0001 should not be null.");
        assertEquals(removedStudent, testClone, "Student removed from 0001 should be Ada.");
    }

    @Test
    public void testRemoveNonExistingStudent() throws Exception {

        //Act
        Student removedStudent = service.removeStudent("0042");

        //Assert
        assertNull(removedStudent, "Removing 0042 should be null.");
    }

}