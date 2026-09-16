package mthree.academy.c458.vrishti.dvd_library.service;

public class DVDLibraryValidationException extends RuntimeException {

    public DVDLibraryValidationException(String message) {
        super(message);
    }

    public DVDLibraryValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
