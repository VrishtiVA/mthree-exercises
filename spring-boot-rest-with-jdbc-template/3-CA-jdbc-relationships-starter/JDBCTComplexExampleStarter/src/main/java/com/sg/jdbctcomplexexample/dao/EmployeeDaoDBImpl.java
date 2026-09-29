package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoDBImpl implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Public so can access by meeting DAO later
     * e.g. when adding list of employees to meeting.
     */
    public static final class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

            Employee emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setFirstName(rs.getString("firstName"));
            emp.setLastName(rs.getString("lastName"));

            return emp;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {

        //Create query string
        final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
        //Call query method with the query string and employee mapper
        //to create list of employee objects.
        return jdbcTemplate.query(SELECT_ALL_EMPLOYEES, new EmployeeMapper());
    }

    @Override
    public Employee getEmployeeById(int id) {
        try {
            final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";
            //Query for single object
            return jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_ID, new EmployeeMapper(), id);
        } catch (DataAccessException e) {
            //Exception if nothing found in query, return null to indicate nothing found.
            return null;
        }
    }

    /**
     * Transactional: To be sure all queries in method run in a single transaction.
     *      All queries To retrieve last insert id, no operation get in the way.
     * @param employee
     * @return
     */
    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {

        //Add
        final String INSERT_EMPLOYEE = "INSERT INTO employee (firstName, lastName) VALUES (?, ?)";
        jdbcTemplate.update(INSERT_EMPLOYEE,
                employee.getFirstName(),
                employee.getLastName()
        );

        //Get last insert id
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        //Return added employee
        employee.setId(newId);
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {

        final String UPDATE_EMPLOYEE = "UPDATE employee SET firstName = ?, lastName = ? WHERE id = ?";
        jdbcTemplate.update(UPDATE_EMPLOYEE,
                employee.getFirstName(),
                employee.getFirstName(),
                employee.getId()
        );
    }

    @Override
    @Transactional //Multiple operations to be completed in a transaction.
    public void deleteEmployeeById(int id) {

        //Deleting bridge table entries first
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee WHERE employeeId = ?";
        jdbcTemplate.update(DELETE_MEETING_EMPLOYEE, id);

        //Delete employee entry itself
        final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate.update(DELETE_EMPLOYEE, id);
    }
}
