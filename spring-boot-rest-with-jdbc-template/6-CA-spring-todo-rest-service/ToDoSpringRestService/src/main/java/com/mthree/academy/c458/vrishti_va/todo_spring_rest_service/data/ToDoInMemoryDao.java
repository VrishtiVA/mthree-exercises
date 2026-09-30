package com.mthree.academy.c458.vrishti_va.todo_spring_rest_service.data;

import com.mthree.academy.c458.vrishti_va.todo_spring_rest_service.models.ToDo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//Removed repository annotation from here so spring DI doesn't get confused.
//Prevent ambiguity
public class ToDoInMemoryDao implements ToDoDao {

    private static final List<ToDo> todos = new ArrayList<>();

    @Override
    public ToDo add(ToDo todo) {

        int nextId = todos.stream()
            .mapToInt(i -> i.getId())
            .max()
            .orElse(0) + 1;

        todo.setId(nextId);
        todos.add(todo);
        return todo;
    }

    @Override
    public List<ToDo> getAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public ToDo findById(int id) {
        return todos.stream()
            .filter(i -> i.getId() == id)
            .findFirst()
            .orElse(null);
    }

    @Override
    public boolean update(ToDo todo) {

        //Linear search for index.
        int index = 0;
        while (index < todos.size() && todos.get(index).getId() != todo.getId()) {
            index ++;
        }

        if (index < todos.size()) {
            todos.set(index, todo);
        }
        return index < todos.size();
    }

    @Override
    public boolean deleteById(int id) {
        return todos.removeIf(i -> i.getId() == id);
    }
}
