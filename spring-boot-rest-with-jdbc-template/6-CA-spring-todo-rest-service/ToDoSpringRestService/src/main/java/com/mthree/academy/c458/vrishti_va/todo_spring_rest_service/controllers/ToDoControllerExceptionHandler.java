package com.mthree.academy.c458.vrishti_va.todo_spring_rest_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@RestController
public class ToDoControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTRAINT_MESSAGE = "Could not save your item. Please ensure it is valid and try again.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(
        SQLIntegrityConstraintViolationException ex,
        WebRequest request
    ) {

        Error error = new Error();
        error.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
