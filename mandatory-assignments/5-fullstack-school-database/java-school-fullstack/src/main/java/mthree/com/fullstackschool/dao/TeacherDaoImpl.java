package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.TeacherMapper;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE

        final String INSERT_TEACHER = """
            INSERT INTO teacher (tFName, tLName, dept)
            VALUES (?, ?, ?)
        """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        //Perform insertion update according to prepared statement,
        //obtaining the generated id in the keyholder.
        jdbcTemplate.update((Connection conn) -> {

            //Prepare statement
            PreparedStatement preparedStatement = conn.prepareStatement(
                    INSERT_TEACHER,
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, teacher.getTeacherFName());
            preparedStatement.setString(2, teacher.getTeacherLName());
            preparedStatement.setString(3, teacher.getDept());
            return preparedStatement;

        }, keyHolder);

        //Complete teacher object with newly generated id
        teacher.setTeacherId(keyHolder.getKey().intValue());

        //Return added teacher
        return teacher;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        //Set up, fetch, and return query results
        final String SELECT_ALL_TEACHERS = "SELECT * FROM teacher";
        return jdbcTemplate.query(SELECT_ALL_TEACHERS, new TeacherMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Teacher findTeacherById(int id) {
        //YOUR CODE STARTS HERE

        //Set up, fetch, and return query result
        final String SELECT_TEACHER_BY_ID = "SELECT * FROM teacher WHERE tid = ?";
        return jdbcTemplate.queryForObject(SELECT_TEACHER_BY_ID, new TeacherMapper(), id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateTeacher(Teacher t) {
        //YOUR CODE STARTS HERE

        //Set up update statement
        final String UPDATE_TEACHER = """
            UPDATE teacher
            SET
                tFName = ?,
                tLName = ?,
                dept = ?
            WHERE tid = ?
        """;

        //Apply update
        jdbcTemplate.update(UPDATE_TEACHER,
            t.getTeacherFName(),
            t.getTeacherLName(),
            t.getDept(),
            t.getTeacherId()
        );

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteTeacher(int id) {
        //YOUR CODE STARTS HERE

        //Null related course teacher ids first
        final String DETACH_TEACHER_COURSES = """
            UPDATE course SET
                teacherId = null
            WHERE teacherId = ?
        """;
        jdbcTemplate.update(DETACH_TEACHER_COURSES, id);

        //Delete teachers themselves
        final String DELETE_TEACHER = """
            DELETE FROM teacher
            WHERE tid = ?
        """;
        jdbcTemplate.update(DELETE_TEACHER, id);

        //YOUR CODE ENDS HERE
    }
}
