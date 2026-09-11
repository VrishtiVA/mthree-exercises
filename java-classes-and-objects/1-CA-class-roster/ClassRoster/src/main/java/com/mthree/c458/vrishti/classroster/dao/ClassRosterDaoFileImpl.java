package com.mthree.c458.vrishti.classroster.dao;

import com.mthree.c458.vrishti.classroster.dto.Student;

import java.io.*;
import java.util.*;

/**
 * This is the text file-specific implementation of the ClassRosterDao interface.
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {

    //Constants
    public static final String DELIMITER = "::";

    public final String ROSTER_FILE; //Only declaration

    //Using a Map for now, since looking up students by ID.
    private Map<String, Student> students = new HashMap<>();

    /**
     * No-args constructor.
     */
    public ClassRosterDaoFileImpl() {
        //Constructor assign where we're saving to.
        ROSTER_FILE = "roster.txt";
    }

    /**
     * Overloaded constructor.
     * Allow different instances of this class to use different files.
     * File reference can be injected upon construction - perfect for testing,
     * as it ensures we don't overwrite our production application data.
     * @param rosterTextFile The file to use.
     */
    public ClassRosterDaoFileImpl(String rosterTextFile) {
        ROSTER_FILE = rosterTextFile;
    }

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {

        //Ensure roster loaded
        loadRoster();
        //Add the student, .put() can return the previous value mapped to that key, or null if it wasn't.
        Student prevStudent = students.put(studentId, student);
        //Write all students back
        writeRoster();
        //Return previous student
        return prevStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {

        //Ensure roster loaded
        loadRoster();
        //Coding to the interface example - this is fine since ArrayList implements List interface, so can be treated as a List.
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {

        //Ensure roster loaded
        loadRoster();
        //Return specific student
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {

        //Ensure roster loaded
        loadRoster();
        //Remove specific student
        Student removedStudent = students.remove(studentId);
        //Update roster
        writeRoster();
        //Return removed student
        return removedStudent;
    }

    private Student unmarshallStudent(String studentAsText) {

        //Split the line - using.split to split by delimiter into an array of strings.
        String[] studentTokens = studentAsText.split(DELIMITER);

        //Build new student object
        Student studentFromFile = new Student(studentTokens[0]);
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);

        //Return student object
        return studentFromFile;
    }

    private void loadRoster() throws ClassRosterPersistenceException {

        //Create scanner for reading file
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            //Convert/translate the exception
            throw new ClassRosterPersistenceException("Could not load roster data into memory", e);
        }

        //Utility variables
        //For most recent unmarshalled student
        String currentLine;
        Student currentStudent;

        //While most students in file, unmarshal and populate map.
        while (scanner.hasNextLine()) {
            //Unmarshal student
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);
            //Populate map
            students.put(currentStudent.getStudentId(), currentStudent);
        }

        //Close scanner.
        scanner.close();
    }

    private String marshallStudent(Student aStudent) {

        //If no student to marshal.
        if (aStudent == null) return "";

        //Turn student details into string
        String studentAsText = aStudent.getStudentId() + DELIMITER + aStudent.getFirstName() + DELIMITER + aStudent.getLastName() + DELIMITER + aStudent.getCohort();

        //Return text version of student
        return studentAsText;
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE.
     * See loadRoster for file format.
     *
     * @throws ClassRosterPersistenceException if an error occurs before writing to the file
     */
    private void writeRoster() throws ClassRosterPersistenceException {

        //Try create print writer for writing to file
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            //Convert/translate the exception
            throw new ClassRosterPersistenceException("Could not save student data.", e);
        }

        //Override the file with all students in the map.
        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList) {
            //Marshal student into a string
            studentAsText = marshallStudent(currentStudent);
            //Write student object to file
            out.println(studentAsText);
            //Force print writer to write line to file
            out.flush();
        }

        //Clean up
        out.close();
    }
}
