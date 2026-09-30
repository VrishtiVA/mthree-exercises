package com.mthree.academy.c458.vrishti_va.todo_spring_rest_service.data;

import com.mthree.academy.c458.vrishti_va.todo_spring_rest_service.models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Profile("database")
public class ToDoDatabaseDao implements ToDoDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ToDoDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ToDo add(ToDo todo) {

        final String sql = "INSERT INTO todo (todo, note) VALUES (?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        //Using jdbcTemplate overload to execute an SQL insert and grab the generated keys.
        //This takes 2 parameters, a PreparedStatementCreator, and a KeyHolder.
        //Both are Spring JDBC types.
        //PreparedStatementCreator is a functional interface with one method signature
        //that generates a PreparedStatement from a Connection.
        //Both PreparedStatement and Connection are plain old JDBC classes.
        //We opt to satisfy the PreparedStatementCreator parameter with a lambda.
        //The lambda is an unnamed method that takes a Connection as a parameter and returns a
        //completed PreparedStatement.
        //A KeyHolder is an interface that defines something that holds keys.
        //We opt for the concrete class GeneratedKeyHolder.
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, todo.getTodo());
            statement.setString(2, todo.getNote());
            return statement;

        }, keyHolder);

        //After the SQL insert has been executed, we can grab the generated keys from the GeneratedKeyHolder.
        todo.setId(keyHolder.getKey().intValue());
        return todo;
    }

    @Override
    public List<ToDo> getAll() {

        //Final local variables prevent us modifying variables that shouldn't be modified.
        //And allows compiler to optimize our class.
        final String sql = "SELECT id, todo, note, finished FROM todo;";
        return jdbcTemplate.query(sql, new ToDoMapper());
    }

    @Override
    public ToDo findById(int id) {

        final String sql = "SELECT id, todo, note, finished FROM todo WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), id);
    }

    @Override
    public boolean update(ToDo todo) {

        final String sql = "UPDATE todo SET todo = ?, node = ?, finished = ? WHERE id = ?";
        return jdbcTemplate.update(sql, todo.getTodo(), todo.getNote(), todo.isFinished(), todo.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {

        final String sql = "DELETE FROM todo WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private static final class ToDoMapper implements RowMapper<ToDo> {

        @Override
        public ToDo mapRow(ResultSet rs, int index) throws SQLException {

            ToDo td = new ToDo();
            td.setId(rs.getInt("id"));
            td.setTodo(rs.getString("todo"));
            td.setNote(rs.getString("note"));
            td.setFinished(rs.getBoolean("finished"));

            return td;
        }
    }
}
