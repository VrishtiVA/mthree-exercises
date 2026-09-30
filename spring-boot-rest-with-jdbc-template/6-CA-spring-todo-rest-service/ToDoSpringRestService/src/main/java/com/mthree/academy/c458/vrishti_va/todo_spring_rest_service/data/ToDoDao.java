package com.mthree.academy.c458.vrishti_va.todo_spring_rest_service.data;

import com.mthree.academy.c458.vrishti_va.todo_spring_rest_service.models.ToDo;

import java.util.List;

public interface ToDoDao {

    ToDo add(ToDo todo);

    List<ToDo> getAll();

    ToDo findById(int id);

    /**
     * @return True if item exists and is updated.
     */
    boolean update(ToDo todo);

    /**
     * @return True if item exists and is deleted.
     */
    boolean deleteById(int id);

}
