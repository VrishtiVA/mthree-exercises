package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.StudentMapper;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.*;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Student createNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        final String INSERT_STUDENT = """
            INSERT INTO student (fName, lName)
            VALUES (?, ?)
        """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        //Perform insertion update according to prepared statement,
        //obtaining the generated id in the keyholder.
        jdbcTemplate.update((Connection conn) -> {

            //Prepare statement
            PreparedStatement preparedStatement = conn.prepareStatement(
                INSERT_STUDENT,
                Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, student.getStudentFirstName());
            preparedStatement.setString(2, student.getStudentLastName());
            return preparedStatement;

        }, keyHolder);

        //Complete student object with newly generated id
        student.setStudentId(keyHolder.getKey().intValue());

        //Return added student
        return student;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        //Set up, fetch, and return query results
        final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
        return jdbcTemplate.query(SELECT_ALL_STUDENTS, new StudentMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Student findStudentById(int id) {
        //YOUR CODE STARTS HERE

        //Set up, fetch, and return query result
        final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE sid = ?";
        return jdbcTemplate.queryForObject(SELECT_STUDENT_BY_ID, new StudentMapper(), id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateStudent(Student student) {
        //YOUR CODE STARTS HERE

        //Set up update statement
        final String UPDATE_STUDENT = """
            UPDATE student
            SET
                fName = ?,
                lName = ?
            WHERE sid = ?
        """;

        //Apply update
        jdbcTemplate.update(UPDATE_STUDENT,
            student.getStudentFirstName(),
            student.getStudentLastName(),
            student.getStudentId()
        );

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudent(int id) {
        //YOUR CODE STARTS HERE

        //Delete course_student relations first (by student id)
        final String DELETE_ALL_COURSE_STUDENTS_BY_STUDENT = """
            DELETE FROM course_student
            WHERE student_id = ?
        """;
        jdbcTemplate.update(DELETE_ALL_COURSE_STUDENTS_BY_STUDENT, id);

        //Then delete the student itself
        final String DELETE_STUDENT = """
            DELETE FROM student
            WHERE sid = ?
        """;
        jdbcTemplate.update(DELETE_STUDENT, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        /* While course_student fields should be PK FK in the database, as shown in the ERD, they aren't,
           so no referential integrity exception is thrown by itself and duplicates could be added.
           Hence, need to check first that relation doesn't exist, otherwise indicate e.g. by throwing,
           without updating signature outside of placeholder (to fit requirements)
           to prevent adding duplicates (as requested).
           SQL CASE statement syntax refresher: https://www.w3schools.com/sql/sql_case.asp
        */

        //Check student isn't already enrolled on course
        final String SELECT_COURSE_STUDENT = """
            SELECT 
                CASE
                    WHEN COUNT(*) = 0 THEN 1
                    ELSE 0
                END
            FROM course_student
            WHERE student_id = ? AND course_id = ?
        """;
        Boolean nonDuplicate = jdbcTemplate.queryForObject(SELECT_COURSE_STUDENT, Boolean.class, studentId, courseId);

        //If student hasn't been enrolled yet, can enroll.
        if (nonDuplicate) {
            //Set up insert statement, and perform insertion update.
            final String INSERT_COURSE_STUDENT = """
                INSERT INTO course_student (student_id, course_id)
                VALUES (?, ?)
            """;
            jdbcTemplate.update(INSERT_COURSE_STUDENT, studentId, courseId);

        } else {
            //The course_student relation already exists
            throw new DataIntegrityViolationException("Student already added to course.");
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        //Set up statement to delete specific course_student
        final String DELETE_COURSE_STUDENT = """
            DELETE FROM course_student
            WHERE student_id = ? AND course_id = ?
        """;

        //Perform the deletion update.
        jdbcTemplate.update(DELETE_COURSE_STUDENT, studentId, courseId);

        //YOUR CODE ENDS HERE
    }
}
