package com.mthree.academy.c458.vrishti.jco.exercises.shapes_and_perimeters;

public abstract class Shape {

    //Attributes
    protected String color;

    //Constructors
    public Shape() {}
    public Shape(String color) {
        this.color = color;
    }

    //Abstract Methods
    public abstract double getArea();
    public abstract double getPerimeter();

    //Getter Methods
    public String getColor() {return color;}

    //Setter Methods
    public void setColor(String color) {this.color = color;}
}
