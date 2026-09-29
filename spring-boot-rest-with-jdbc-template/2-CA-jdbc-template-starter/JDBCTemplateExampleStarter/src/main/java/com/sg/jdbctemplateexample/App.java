/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctemplateexample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author kylerudy
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    private static Scanner sc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sc = new Scanner(System.in);

        do {
            System.out.println("To-Do List");
            System.out.println("1. Display List");
            System.out.println("2. Add Item");
            System.out.println("3. Update Item");
            System.out.println("4. Remove Item");
            System.out.println("5. Exit");

            System.out.println("Enter an option:");
            String option = sc.nextLine();
            try {
                switch (option) {
                    case "1":
                        displayList();
                        break;
                    case "2":
                        addItem();
                        break;
                    case "3":
                        updateItem();
                        break;
                    case "4":
                        removeItem();
                        break;
                    case "5":
                        System.out.println("Exiting");
                        System.exit(0);
                    default:
                        System.out.println("I don't understand");
                }
            } catch (Exception ex) {
                System.out.println("Error communicating with database");
                System.out.println(ex.getMessage());
                System.exit(0);
            }

        } while (true);
    }

    private void displayList() throws SQLException {

        //Query and obtain results as list of ToDos.
        //Using JdbcTemplate object to query the database,
        //Which expects a String SQL query and a mapper.
        //The ToDoMapper will have filled in all data in the object,
        //so don't have to worry about missing data here.
        List<ToDo> todos = jdbcTemplate.query("SELECT * FROM todo", new ToDoMapper());

        //Display each result
        for (ToDo td : todos) {
            System.out.printf("%s: %s -- %s -- %s\n",
                td.getId(),
                td.getTodo(),
                td.getNote(),
                td.isFinished()
            );
        }
        System.out.println();
    }

    private void addItem() throws SQLException {

        //Collect Inputs
        System.out.println("Add Item");
        System.out.println("What is the task?");
        String task = sc.nextLine();
        System.out.println("Any additional notes?");
        String note = sc.nextLine();

        //Use jdbcTemplate update method to make changes in the database
        //Including INSERT, UPDATE, DELETE calls go through here.
        //Additional parameters after the mapper position.
        //No mapper needed here, since we're not expecting results back from the INSERT.
        jdbcTemplate.update("INSERT INTO todo (todo, note) VALUES (?, ?)", task, note);

    }

    private void updateItem() throws SQLException {

        //Collect input
        System.out.println("Update Item");
        System.out.println("Which item do you want to update?");
        String itemId = sc.nextLine();

        //Using queryForObject method to retrieve a single object from the database.
        //If no object or multiple objects, an Exception will be thrown.
        ToDo item = jdbcTemplate.queryForObject(
            "SELECT * FROM todo WHERE id = ?",
            new ToDoMapper(),
            itemId
        );
        if (item == null) return;

        //Update menu to fetch single update from user.
        System.out.println("1. ToDo - " + item.getTodo());
        System.out.println("2. Note - " + item.getNote());
        System.out.println("3. Finished - " + item.isFinished());
        System.out.println("What would you like to change?");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter new ToDo:");
                String todo = sc.nextLine();
                item.setTodo(todo);
                break;
            case "2":
                System.out.println("Enter new Note:");
                String note = sc.nextLine();
                item.setNote(note);
                break;
            case "3":
                System.out.println("Toggling Finished to " + !item.isFinished());
                item.setFinished(!item.isFinished());
                break;
            default:
                System.out.println("No change made");
                return;
        }

        //Make the update to database.
        //Using update method since changing data.
        //Safe this way, old data will just override itself here.
        jdbcTemplate.update("UPDATE todo SET todo = ?, note = ?, finished = ? WHERE id = ?",
            item.getTodo(),
            item.getNote(),
            item.isFinished(),
            item.getId()
        );

        System.out.println("Update Complete");
    }

    private void removeItem() throws SQLException {

        //Collect input
        System.out.println("Remove Item");
        System.out.println("Which item would you like to remove?");
        String itemId = sc.nextLine();

        //Make deletion update using the update method to perform it.
        jdbcTemplate.update("DELETE FROM todo WHERE id = ?", itemId);

        System.out.println("Remove Complete");
    }

    /**
     * RowMapper of type ToDo (generic), so it knows what object type we're processing,
     * Letting it know what mapRow it should return.
     * Creating as a private internal class, meaning it will only accessible from App class.
     * Okay since only ever need here in this case.
     * Often see this pattern with mappers - tying them directly to the class that is primarily using them.
     * Can't be changed or re-overridden in any way
     */
    private static final class ToDoMapper implements RowMapper<ToDo> {

        /**
         * Looking for a single row of the Result set,
         * so we can immediately pull the data out of it and create our object
         *
         * @param rs     the {@code ResultSet} to map (pre-initialized for the current row)
         * @param rowNum the number of the current row
         * @return Return the object (indicated by the generic above)
         * @throws SQLException
         */
        @Override
        public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {

            //Create an instance of the object from fields in the result set.
            //Field names should match what we're getting from the database.
            ToDo td = new ToDo();
            td.setId(rs.getInt("id"));
            td.setTodo(rs.getString("todo"));
            td.setNote(rs.getString("note"));
            td.setFinished(rs.getBoolean("finished"));

            return td;
        }
    }
}
