package com.mthree.academy.c458.vrishti.jco.exercises.simple_calculator;

public class SimpleCalculator {

    public Integer calculate(int operation, int leftOperand, int rightOperand) {
        switch (operation) {
            case 1: return leftOperand + rightOperand;
            case 2: return leftOperand - rightOperand;
            case 3: return leftOperand * rightOperand;
            case 4: return rightOperand == 0 ? null : leftOperand / rightOperand;
            default: return null;
        }
    }

    public String[] getCalculatorOperations() {
        return new String[] {"+", "-", "*", "/"};
    }

}
