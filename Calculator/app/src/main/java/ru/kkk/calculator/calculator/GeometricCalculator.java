package ru.kkk.calculator.calculator;

public class GeometricCalculator {

    public Double startCalculate(int[] args, Function function) {
        Double result = null;
        switch (function) {
            case TRIANGLE_SQUARE:
                result = getTriangleSquare(args);
                break;
            case RECTANGLE_SQUARE:
                result = getRectangleSquare(args);
                break;
        }
        return result;
    }

    private Double getRectangleSquare(int[] args) {
        return (double) (args[0] * args[1]);
    }

    private Double getTriangleSquare(int[] args) {
        return 0.5 * args[0] * args[1];
    }

    public enum Function {
        TRIANGLE_SQUARE,
        RECTANGLE_SQUARE
    }
}
