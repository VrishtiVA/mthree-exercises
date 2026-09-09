package com.mthree.academy.c458.vrishti.jco.exercises.shapes_and_perimeters;

public class Circle extends Shape {

    //Attributes
    private double radius;

    //Constructors
    public Circle() {}
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    /* ----- Method Overrides ----- */

    @Override
    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2.0);
    }

    @Override
    public double getPerimeter() {
        return Math.PI * this.radius * 2;
    }

    //Getter Methods
    public double getRadius() {return this.radius;}

    //Setter Methods
    public void setRadius(double radius) {this.radius = radius;}

}
