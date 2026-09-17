package mthree.academy.c458.vrishti.dvd_library.dao;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;
import mthree.academy.c458.vrishti.dvd_library.ui.UserIO;

import java.time.LocalDate;

public class DVDSamples {

    public static DVD getDVDSample1() {
        DVD dvd = new DVD(1L);
        dvd.setTitle("Harry Potter and the Philosophers Stone");
        dvd.setReleaseDate(LocalDate.parse("16-11-2001", UserIO.DATE_FORMAT));
        dvd.setMpaaRating("PG");
        dvd.setDirectorName("Chris Columbus");
        dvd.setStudio("Warner Bros");
        dvd.setUserRatingNote("");
        return dvd;
    }

    public static DVD getDVDSample2() {
        DVD dvd = new DVD(2L);
        dvd.setTitle("Harry Potter and the Chamber of Secrets");
        dvd.setReleaseDate(LocalDate.parse("15-11-2002", UserIO.DATE_FORMAT));
        dvd.setMpaaRating("PG");
        dvd.setDirectorName("Chris Columbus");
        dvd.setStudio("Warner Bros");
        dvd.setUserRatingNote("");
        return dvd;
    }

    public static DVD getDVDSample3() {
        DVD dvd = new DVD(3L);
        dvd.setTitle("Despicable Me");
        dvd.setReleaseDate(LocalDate.parse("14-12-2010", UserIO.DATE_FORMAT));
        dvd.setMpaaRating("PG");
        dvd.setDirectorName("Chris Renauld");
        dvd.setStudio("Illumination Entertainment");
        dvd.setUserRatingNote("");
        return dvd;
    }
}
