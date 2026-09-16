package mthree.academy.c458.vrishti.dvd_library.service;

public class DVDLibraryPersistenceException extends RuntimeException {

    public DVDLibraryPersistenceException(String message) {
        super(message);
    }

    public DVDLibraryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
