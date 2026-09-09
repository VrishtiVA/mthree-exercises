package com.mthree.academy.c458.vrishti.jco.exercises.shapes_and_perimeters;

public class Triangle extends Shape {

    //Attributes
    private double height;
    private double base;

    //Constructors
    public Triangle() {}
    public Triangle(String color, double height, double base) {
        super(color);
        this.height = height;
        this.base = base;
    }

    /* ----- Method Overrides ----- */

    @Override
    public double getArea() {
        return (height * base) / 2;
    }

    @Override
    public double getPerimeter() {
        return height + base + (Math.sqrt(Math.pow(height, 2) + Math.pow(base, 2)));
    }

    //Getter Methods
    public double getHeight() {return this.height;}
    public double getBase() {return this.base;}

    //Setter Methods
    public void setHeight(double height) {this.height = height;}
    public void setBase(double base) {this.base = base;}

}
