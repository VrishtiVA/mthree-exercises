package com.mthree.c458.vrishti.classroster.dao;

/**
 * To hide the underlying implementation exceptions so that we don't leak implementation details from our DAO.
 */
public class ClassRosterDaoException extends Exception {

    /**
     * Use in cases where something is wrong in our application,
     * but it isn't caused by another exception.
     * @param message A message describing the problem.
     */
    public ClassRosterDaoException(String message) {
        super(message); //Call super to act just like Exception
    }

    /**
     * Use in cases where something is wrong in our application
     * that is caused by another exception in the underlying implementation
     * @param message A message describing the problem.
     * @param cause The exception that occurred. Throwable type to allow potential to handle the greatest number of possible errors.
     */
    public ClassRosterDaoException(String message, Throwable cause) {
        super(message, cause); //Call super to act just like Exception
    }

}
