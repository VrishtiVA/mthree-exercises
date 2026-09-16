package mthree.academy.c458.vrishti.dvd_library.dao;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private String DELIMITER = "::";

//    long lastDVDId;
    String dvdLibraryFileName;
    Map<Long, DVD> dvdMap;

    public DVDLibraryDaoFileImpl(String dvdLibraryFileName) {
        this.dvdMap = new HashMap<>();
        this.dvdLibraryFileName = dvdLibraryFileName;

        //Load DVDs initially
        loadDVDs();
//        this.lastDVDId = dvdMap.size();
    }

//    public long getNewDVDId() {
//        return ++lastDVDId;
//    }

    @Override
    public DVD addDVD(DVD dvd) {
        DVD previousDVD = dvdMap.put(dvd.getId(), dvd);
        saveDVDs();
        return previousDVD;
    }

    @Override
    public DVD removeDVD(long id) {
        DVD removedDVD = dvdMap.remove(id);
        saveDVDs();
        return removedDVD;
    }

    @Override
    public DVD editDVD(long id, DVD edittedDVD) {
        DVD replacedDVD = dvdMap.replace(id, edittedDVD);
        saveDVDs();
        return replacedDVD;
    }

    @Override
    public List<DVD> getDVDs() {
        return new ArrayList<>(dvdMap.values());
    }

    @Override
    public DVD getDVD(long id) {
        return dvdMap.get(id);
    }

    @Override
    public List<DVD> findDVDsByTitle(String title) {

        List<DVD> matchingResults = new ArrayList<>();

        //Go through all DVDs
        for (DVD dvd : dvdMap.values()) {

            //Add DVD if title fits
            if (dvd.getTitle().toUpperCase().contains(title.toUpperCase())) {
                matchingResults.add(dvd);
            }
        }

        return matchingResults;
    }

    private String marshallDVD(DVD dvd) {
        return dvd.getId() + DELIMITER +
            dvd.getTitle() + DELIMITER +
            dvd.getReleaseDate() + DELIMITER +
            dvd.getMpaaRating() + DELIMITER +
            dvd.getDirectorName() + DELIMITER +
            dvd.getStudio() + DELIMITER +
            dvd.getUserRatingNote();
    }

    private DVD unmarshallDVD(String dvdString) {

        //Split line
        String[] dvdLine = dvdString.split(DELIMITER);

        //Rebuild DVD
        DVD dvd = new DVD(Long.parseLong(dvdLine[0]));
        dvd.setTitle(dvdLine[1]);
        dvd.setReleaseDate(dvdLine[2]);
        dvd.setMpaaRating(dvdLine[3]);
        dvd.setDirectorName(dvdLine[4]);
        dvd.setStudio(dvdLine[5]);
        dvd.setUserRatingNote(dvdLine[6]);

        //Return DVD
        return dvd;
    }

    private void loadDVDs() throws DVDLibraryPersistenceException {

        //Utility object(s)
        Scanner fileReader;

        //Open file for reading
        try {
            fileReader = new Scanner(new BufferedReader(new FileReader(dvdLibraryFileName)));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Unable to load DVDs");
        }

        //Read file
        String dvdString;
        DVD dvd;
        while (fileReader.hasNextLine()) {

            //Read line
            dvdString = fileReader.nextLine();
            dvd = unmarshallDVD(dvdString);

            //Populate map
            dvdMap.put(dvd.getId(), dvd);
        }

        //Cleanup
        fileReader.close();
    }

    private void saveDVDs() throws DVDLibraryPersistenceException {

        //Utility object(s)
        PrintWriter printWriter;

        //Open file for writing
        try {
            printWriter = new PrintWriter(new FileWriter(dvdLibraryFileName));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Unable to save DVDs");
        }

        //Write DVDs back to file
        String dvdString;
        for (DVD dvd : getDVDs()) {
            dvdString = marshallDVD(dvd);
            printWriter.println(dvdString);
        }

        //Cleanup
        printWriter.close();
    }

}
