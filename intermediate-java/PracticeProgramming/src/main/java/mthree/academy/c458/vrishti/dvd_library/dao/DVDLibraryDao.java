package mthree.academy.c458.vrishti.dvd_library.dao;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.util.List;

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

}
