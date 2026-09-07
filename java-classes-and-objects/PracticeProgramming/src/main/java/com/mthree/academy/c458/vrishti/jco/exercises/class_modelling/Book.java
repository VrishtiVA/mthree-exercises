package com.mthree.academy.c458.vrishti.jco.exercises.class_modelling;

public class Book {

    //Attributes
    private String title;
    private String blurb;
    private String authorLastname;
    private String authorFirstname;
    private int minAgeRating;
    private String publisher;

    //Constructors
    public Book() {}
    public Book(String title, String blurb, String authorLastname, String authorFirstname, int minAgeRating, int pageCount, String publisher) {
        this.title = title;
        this.blurb = blurb;
        this.authorLastname = authorLastname;
        this.authorFirstname = authorFirstname;
        this.minAgeRating = minAgeRating;
        this.publisher = publisher;
    }

    //Getters
    public String getTitle() {return title;}
    public String getBlurb() {return blurb;}
    public String getAuthorLastname() {return authorLastname;}
    public String getAuthorFirstname() {return authorFirstname;}
    public int getMinAgeRating() {return minAgeRating;}
    public String getPublisher() {return publisher;}

    //Setters
    public void setTitle(String title) {this.title = title;}
    public void setBlurb(String blurb) {this.blurb = blurb;}
    public void setAuthorLastname(String authorLastname) {this.authorLastname = authorLastname;}
    public void setAuthorFirstname(String authorFirstname) {this.authorFirstname = authorFirstname;}
    public void setMinAgeRating(int minAgeRating) {this.minAgeRating = minAgeRating;}

}
