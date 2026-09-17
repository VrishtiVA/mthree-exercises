package mthree.academy.c458.vrishti.dvd_library.service;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.util.List;
import java.util.Map;

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

    List<DVD> findDVDsUnderNYearsOld(int nYears);

    List<DVD> findDVDsByMpaaRating(String mpaaRating);

    Map<String, List<DVD>> findDVDsByDirector(String directorName);

    List<DVD> findDVDsByStudio(String studio);

    Integer getDVDsAverageAgeInDays();

    DVD getNewestDVD();

    DVD getOldestDVD();

}
