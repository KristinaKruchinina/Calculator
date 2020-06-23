package ru.kkk.calculator.calculator;

public class GeometricCalculator {

    public static Double startCalculate(Integer[] args, Function function) {
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

    private static Double getRectangleSquare(Integer[] args) {
        return (double) (args[0] * args[1]);
    }

    private static Double getTriangleSquare(Integer[] args) {
        return 0.5 * args[0] * args[1];
    }

    public enum Function {
        TRIANGLE_SQUARE,
        RECTANGLE_SQUARE
    }
}
