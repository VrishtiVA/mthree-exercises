package mthree.academy.c458.vrishti.dvd_library.dao;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryPersistenceException;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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
        loadDVDs();
        DVD previousDVD = dvdMap.put(dvd.getId(), dvd);
        saveDVDs();
        return previousDVD;
    }

    @Override
    public DVD removeDVD(long id) {
        loadDVDs();
        DVD removedDVD = dvdMap.remove(id);
        saveDVDs();
        return removedDVD;
    }

    @Override
    public DVD editDVD(long id, DVD edittedDVD) {
        loadDVDs();
        DVD replacedDVD = dvdMap.replace(id, edittedDVD);
        saveDVDs();
        return replacedDVD;
    }

    @Override
    public List<DVD> getDVDs() {
        loadDVDs();
        return new ArrayList<>(dvdMap.values());
    }

    @Override
    public DVD getDVD(long id) {
        loadDVDs();
        return dvdMap.get(id);
    }

    @Override
    public List<DVD> findDVDsByTitle(String title) {
        loadDVDs();

        //Filter to only DVDs matching title and return
        return getDVDs().stream()
                .filter(dvd -> dvd.getTitle().toUpperCase().contains(title.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> findDVDsUnderNYearsOld(int nYears) {

        return getDVDs().stream()
                .filter(dvd -> dvd.getReleaseDate().isAfter(LocalDate.now().minusYears(nYears)))
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> findDVDsByMpaaRating(String mpaaRating) {

        return getDVDs().stream()
                .filter(dvd -> dvd.getMpaaRating().equalsIgnoreCase(mpaaRating))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<DVD>> findDVDsByDirector(String directorName) {

        return getDVDs().stream()
                .filter(dvd -> dvd.getDirectorName().equalsIgnoreCase(directorName))
                .collect(Collectors.groupingBy(DVD::getMpaaRating)); //Grouping lambda equivalent to `dvd -> dvd.getMpaaRating()`
    }

    @Override
    public List<DVD> findDVDsByStudio(String studio) {

        return getDVDs().stream()
                .filter(dvd -> dvd.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getDVDsAverageAgeInDays() {

        //Find average age in number of days - OptionalDouble type since DVDs could be empty.
        OptionalDouble averageDays = getDVDs().stream()
                .mapToLong(dvd -> ChronoUnit.DAYS.between(dvd.getReleaseDate(), LocalDate.now()))
                .average();

        if (averageDays.isPresent()) {
            return (int) Math.ceil(averageDays.getAsDouble());

        } else return null;
    }

    @Override
    public DVD getNewestDVD() {
        return getDVDs().stream()
                .min((a, b) ->
                        a.getReleaseDate().isBefore(b.getReleaseDate()) ? 1
                        : a.getReleaseDate().isEqual(b.getReleaseDate()) ? 0
                        : -1
                ).orElse(null);
    }

    @Override
    public DVD getOldestDVD() {
        return getDVDs().stream()
                .max((a, b) ->
                        a.getReleaseDate().isBefore(b.getReleaseDate()) ? 1
                                : a.getReleaseDate().isEqual(b.getReleaseDate()) ? 0
                                : -1
                ).orElse(null);
    }

    private String marshallDVD(DVD dvd) {
        return dvd.getId() + DELIMITER +
            (dvd.getTitle().isBlank() ? " " : dvd.getTitle()) + DELIMITER +
            (dvd.getReleaseDate() == null ? " " : dvd.getReleaseDate()) + DELIMITER +
            (dvd.getMpaaRating().isBlank() ? " " : dvd.getMpaaRating()) + DELIMITER +
            (dvd.getDirectorName().isBlank() ? " " : dvd.getDirectorName()) + DELIMITER +
            (dvd.getStudio().isBlank() ? " " : dvd.getStudio()) + DELIMITER +
            (dvd.getUserRatingNote().isBlank() ? " " : dvd.getUserRatingNote()) + DELIMITER; //Some fields could be empty, so need to careful
    }

    private DVD unmarshallDVD(String dvdString) {

        //Split line
        String[] dvdLine = dvdString.split(DELIMITER);

        //Rebuild DVD
        DVD dvd = new DVD(Long.parseLong(dvdLine[0]));
        dvd.setTitle(dvdLine[1].trim());
        dvd.setReleaseDate(dvdLine[2].isBlank() ? null : LocalDate.parse(dvdLine[2].trim()));
        dvd.setMpaaRating(dvdLine[3].trim());
        dvd.setDirectorName(dvdLine[4].trim());
        dvd.setStudio(dvdLine[5].trim());
        dvd.setUserRatingNote(dvdLine[6].trim());

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
