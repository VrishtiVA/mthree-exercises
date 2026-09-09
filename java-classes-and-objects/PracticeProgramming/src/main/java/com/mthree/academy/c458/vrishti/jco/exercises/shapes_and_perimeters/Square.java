package com.mthree.academy.c458.vrishti.jco.exercises.shapes_and_perimeters;

public class Square extends Shape {

    //Attributes
    private double length;

    //Constructors
    public Square() {}
    public Square(String color, double length) {
        super(color);
        this.length = length;
    }

    /* ----- Method Overrides ----- */

    @Override
    public double getArea() {
        return length * length;
    }

    @Override
    public double getPerimeter() {
        return length * 4;
    }

    //Getter Methods
    public double getLength() {return this.length;}

    //Setter Methods
    public void setLength(double length) {this.length = length;}

}
