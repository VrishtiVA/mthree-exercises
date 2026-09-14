package mthree.academy.c458.vrishti.enums;

public class IntMath {

    public Integer calculate(MathOperator operator, int op1, int op2) {
        switch (operator) {
            case PLUS: return op1 + op2;
            case MINUS: return op1 - op2;
            case MULTIPLY: return op1 * op2;
            case DIVIDE: return op2 == 0 ? null : op1 / op2;
            default: throw new UnsupportedOperationException();
        }
    }

}
