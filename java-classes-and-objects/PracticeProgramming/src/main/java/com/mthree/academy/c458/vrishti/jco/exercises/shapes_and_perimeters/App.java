package com.mthree.academy.c458.vrishti.jco.exercises.shapes_and_perimeters;

public class App {
    public static void main(String[] args) {

        /* Trying to instantiate abstract class
           seems to try make me override its abstract methods right there.
           This is an anonymous class.

           Otherwise, it cannot be instantiated.
         */
        Shape shape = new Shape() {
            @Override
            public double getArea() {
                return 0;
            }

            @Override
            public double getPerimeter() {
                return 0;
            }
        };

        Shape shape2 = new Circle("Green", 5);

        //shape2.color also works here,
        //since protected gives package access too.
        System.out.println(
            "\n===== Circle =====" +
            "\nColour: " + shape2.getColor() +
            "\nArea: " + shape2.getArea() +
            "\nPerimeter: " + shape2.getPerimeter() +
            "\n=================="
        );

    }
}
