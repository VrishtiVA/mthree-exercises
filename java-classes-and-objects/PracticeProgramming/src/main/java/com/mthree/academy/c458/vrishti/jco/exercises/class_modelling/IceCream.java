package com.mthree.academy.c458.vrishti.jco.exercises.class_modelling;

import java.util.Date;

public class IceCream {

    //Attributes
    private String flavour;
    private Date expirationDate;
    private float calories;
    private float sugar;
    private float fat;
    private boolean hasNuts;
    private float idealStoringCelsiusTemperature;

    //Constructors
    public IceCream() {}
    public IceCream(String flavour, Date expirationDate, float calories, float sugar, float fat, boolean hasNuts, float idealStoringCelsiusTemperature) {
        this.flavour = flavour;
        this.expirationDate = expirationDate;
        this.calories = calories;
        this.sugar = sugar;
        this.fat = fat;
        this.hasNuts = hasNuts;
        this.idealStoringCelsiusTemperature = idealStoringCelsiusTemperature;
    }

    //Getters
    public String getFlavour() {return flavour;}
    public Date getExpirationDate() {return expirationDate;}
    public float getCalories() {return calories;}
    public float getSugar() {return sugar;}
    public float getFat() {return fat;}
    public boolean isHasNuts() {return hasNuts;}
    public float getIdealStoringCelsiusTemperature() {return idealStoringCelsiusTemperature;}

    //Setters
    public void setFlavour(String flavour) {this.flavour = flavour;}
    public void setExpirationDate(Date expirationDate) {this.expirationDate = expirationDate;}
    public void setCalories(float calories) {this.calories = calories;}
    public void setSugar(float sugar) {this.sugar = sugar;}
    public void setFat(float fat) {this.fat = fat;}
    public void setHasNuts(boolean hasNuts) {this.hasNuts = hasNuts;}
    public void setIdealStoringCelsiusTemperature(float idealStoringCelsiusTemperature) {this.idealStoringCelsiusTemperature = idealStoringCelsiusTemperature;}

}
