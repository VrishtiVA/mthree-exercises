package com.mthree.academy.c458.vrishti.jco.exercises.class_modelling;

public class Car {

    //Attributes
    private String engine;
    private float fuel;
    private float water;
    private boolean engineOn;
    private boolean breaksActivated;
    private int wheelsCount;
    private String colour;
    private String seatMaterial;
    private String numberPlate;

    /* ----- Constructors ----- */

    public Car() {}

    public Car(int wheelsCount, String colour, String seatMaterial, String numberPlate) {
        this.wheelsCount = wheelsCount;
        this.colour = colour;
        this.seatMaterial = seatMaterial;
        this.numberPlate = numberPlate;
    }

    public Car(String engine, float fuel, float water, String colour, int wheelsCount, String seatMaterial, String numberPlate) {
        this.engine = engine;
        this.fuel = fuel;
        this.water = water;
        this.colour = colour;
        this.wheelsCount = wheelsCount;
        this.seatMaterial = seatMaterial;
        this.numberPlate = numberPlate;
    }

    //Getters
    public String getEngine() {return engine;}
    public float getFuel() {return fuel;}
    public float getWater() {return water;}
    public boolean isEngineOn() {return engineOn;}
    public boolean isBreaksActivated() {return breaksActivated;}
    public int getWheelsCount() {return wheelsCount;}
    public String getColour() {return colour;}
    public String getSeatMaterial() {return seatMaterial;}
    public String getNumberPlate() {return numberPlate;}

    //Setters
    public void setEngine(String engine) {this.engine = engine;}
    public void setFuel(float fuel) {this.fuel = fuel;}
    public void setWater(float water) {this.water = water;}
    public void setWheelsCount(int wheelsCount) {this.wheelsCount = wheelsCount;}
    public void setColour(String colour) {this.colour = colour;}
    public void setSeatMaterial(String seatMaterial) {this.seatMaterial = seatMaterial;}
    public void setNumberPlate(String numberPlate) {this.numberPlate = numberPlate;}

    /* ----- Additional Methods ----- */

    public void activateBreaks() {
        this.breaksActivated = true;
    }

    public void releaseBreaks() {
        this.breaksActivated = false;
    }

    public void setEngineOn() {
        this.engineOn = true;
    }

    public void setEngineOff() {
        this.engineOn = false;
    }

    public void refuelCar(float fuel) {
        //Can later improve car to allow preventing the overfuelling of car
        this.fuel += fuel;
    }

}
