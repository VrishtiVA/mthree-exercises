package mthree.academy.c458.vrishti.dvd_library.dto;

import java.time.LocalDate;
import java.util.Objects;

public class DVD {

    //Attributes
    private long id;
    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String Studio;
    private String userRatingNote; //allows the user to enter additional information, e.g., "Good family movie"

    //Constructors
    public DVD() {}
    public DVD(long id) {
        this.id = id;
    }

    //Getters
    public long getId() {return id;}
    public String getTitle() {return title;}
    public LocalDate getReleaseDate() {return releaseDate;}
    public String getMpaaRating() {return mpaaRating;}
    public String getDirectorName() {return directorName;}
    public String getStudio() {return Studio;}
    public String getUserRatingNote() {return userRatingNote;}

    //Setters
    public void setTitle(String title) {this.title = title;}
    public void setReleaseDate(LocalDate releaseDate) {this.releaseDate = releaseDate;}
    public void setMpaaRating(String mpaaRating) {this.mpaaRating = mpaaRating;}
    public void setDirectorName(String directorName) {this.directorName = directorName;}
    public void setStudio(String studio) {Studio = studio;}
    public void setUserRatingNote(String userRatingNote) {this.userRatingNote = userRatingNote;}

    /* ----- Other Supporting Methods ----- */

    @Override
    public String toString() {
        return "DVD{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mpaaRating='" + mpaaRating + '\'' +
                ", directorName='" + directorName + '\'' +
                ", Studio='" + Studio + '\'' +
                ", userRatingNote='" + userRatingNote + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DVD dvd = (DVD) o;
        return id == dvd.id && Objects.equals(title, dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(mpaaRating, dvd.mpaaRating) && Objects.equals(directorName, dvd.directorName) && Objects.equals(Studio, dvd.Studio) && Objects.equals(userRatingNote, dvd.userRatingNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, mpaaRating, directorName, Studio, userRatingNote);
    }

}
