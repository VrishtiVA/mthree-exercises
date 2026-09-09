package com.mthree.academy.c458.vrishti.jco.exercises.shapes_and_perimeters;

public class Rectangle extends Shape {

    //Attributes
    private double height;
    private double width;

    //Constructors
    public Rectangle() {}
    public Rectangle(String color, double height, double width) {
        super(color);
        this.height = height;
        this.width = width;
    }

    /* ----- Method Overrides ----- */

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return (height + width) * 2;
    }

    //Getter Methods
    public double getHeight() {return this.height;}
    public double getWidth() {return this.width;}

    //Setter Methods
    public void setHeight(double height) {this.height = height;}
    public void setWidth(double width) {this.width = width;}

}
