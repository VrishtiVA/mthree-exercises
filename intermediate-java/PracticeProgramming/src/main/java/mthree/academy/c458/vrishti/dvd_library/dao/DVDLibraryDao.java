package mthree.academy.c458.vrishti.dvd_library.dao;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.util.List;
import java.util.Map;

public interface DVDLibraryDao {

    /**
     * Allow the user to add a DVD to the collection.
     *
     * Add the DVD to the library.
     * @return previously stored DVD.
     */
    public DVD addDVD(DVD dvd);

    /**
     * Allow the user to remove a DVD from the collection
     *
     * Remove the DVD from the library
     * @return the removed DVD
     */
    public DVD removeDVD(long id);

    /**
     * Allow the user to edit the information for an existing DVD in the collection
     *
     * Update the DVD with the edittedDVD (a put style edit)
     * @return The previous version of the DVD.
     */
    public DVD editDVD(long id, DVD edittedDVD);

    /**
     * Allow the user to list the DVDs in the collection.
     *
     * @return A list of DVDs, never null.
     */
    public List<DVD> getDVDs();

    /**
     * Allow the user to display the information for a particular DVD.
     *
     * Get DVD by id.
     */
    public DVD getDVD(long id);

    /**
     * Allow the user to search for DVDs by title.
     *
     * Return matching DVDs
     */
    public List<DVD> findDVDsByTitle(String title);

    /**
     * Find all movies released in the last N years.
     */
    public List<DVD> findDVDsUnderNYearsOld(int nYears);

    /**
     * Find all the movies with a given MPAA rating.
     */
    public List<DVD> findDVDsByMpaaRating(String mpaaRating);

    /**
     * Find all the movies by a given director.
     * When searching by director, the movies should be sorted into separate data structures by MPAA rating.
     */
    public Map<String, List<DVD>> findDVDsByDirector(String directorName);

    /**
     * Find all the movies released by a particular studio.
     */
    public List<DVD> findDVDsByStudio(String studio);

    /**
     * Find the average age of the movies in the collection.
     *
     * @return null if empty
     */
    public Integer getDVDsAverageAgeInDays();

    /**
     * Find the newest movie in your collection.
     *
     * @return null if no DVDs
     */
    public DVD getNewestDVD();

    /**
     * Find the oldest movie in your collection.
     *
     * @return null if no DVDs
     */
    public DVD getOldestDVD();

}
