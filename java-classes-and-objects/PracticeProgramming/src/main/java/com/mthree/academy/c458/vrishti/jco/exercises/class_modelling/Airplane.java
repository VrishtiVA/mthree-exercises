package com.mthree.academy.c458.vrishti.jco.exercises.class_modelling;

public class Airplane {

    //Attributes
    private String model;
    private String registrationNumber;
    private String airline;
    private float altitude;

    //Constructors
    public Airplane() {}
    public Airplane(String model, String airline, String registrationNumber) {
        this.model = model;
        this.airline = airline;
        this.registrationNumber = registrationNumber;
    }

    //Getters
    public String getModel() {return model;}
    public String getAirline() {return airline;}
    public float getAltitude() {return altitude;}
    public String getRegistrationNumber() {return registrationNumber;}

    //Setters
    public void setModel(String model) {this.model = model;}
    public void setAirline(String airline) {this.airline = airline;}
    public void setAltitude(float altitude) {this.altitude = altitude;}
    public void setRegistrationNumber(String registrationNumber) {this.registrationNumber = registrationNumber;}

}
