package mthree.academy.c458.vrishti.dvd_library.service;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.util.List;

public interface DVDLibraryService {

    void addDVD(DVD dvd) throws
            DVDLibraryPersistenceException,
            DVDLibraryDuplicateIdException,
            DVDLibraryValidationException;

    List<DVD> getAllDVDs() throws
            DVDLibraryPersistenceException;

    DVD getDVD(long id) throws
            DVDLibraryPersistenceException;

    List<DVD> searchForDVDsByTitle(String title) throws
            DVDLibraryPersistenceException;

    DVD removeDVD(long id) throws
            DVDLibraryPersistenceException;

    DVD editDVD(long id, DVD edittedDVD) throws
            DVDLibraryPersistenceException,
            DVDLibraryValidationException;

}
