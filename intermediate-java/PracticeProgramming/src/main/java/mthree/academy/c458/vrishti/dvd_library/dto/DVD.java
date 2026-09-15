package mthree.academy.c458.vrishti.dvd_library.dto;

import java.time.LocalDate;

public class DVD {

    //Attributes
    private long id;
    private String title;
    private String releaseDate;
    private float mpaaRating;
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
    public String getReleaseDate() {return releaseDate;}
    public float getMpaaRating() {return mpaaRating;}
    public String getDirectorName() {return directorName;}
    public String getStudio() {return Studio;}
    public String getUserRatingNote() {return userRatingNote;}

    //Setters
    public void setTitle(String title) {this.title = title;}
    public void setReleaseDate(String releaseDate) {this.releaseDate = releaseDate;}
    public void setMpaaRating(float mpaaRating) {this.mpaaRating = mpaaRating;}
    public void setDirectorName(String directorName) {this.directorName = directorName;}
    public void setStudio(String studio) {Studio = studio;}
    public void setUserRatingNote(String userRatingNote) {this.userRatingNote = userRatingNote;}

}
