package mthree.academy.c458.vrishti.dvd_library.service;

public class DVDLibraryDuplicateIdException extends RuntimeException {

    public DVDLibraryDuplicateIdException(String message) {
        super(message);
    }

    public DVDLibraryDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
