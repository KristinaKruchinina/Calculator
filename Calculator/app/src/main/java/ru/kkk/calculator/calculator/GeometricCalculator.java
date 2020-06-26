package ru.kkk.calculator.calculator;

/**
 * Класс, представляющий собой геометрический калькулятор
 *
 * @author Кручинина Кристина 17ИТ17
 */
public class GeometricCalculator {
    /**
     * Метод, вызывающий определенную функцию расчета относительно
     * входныз данных
     *
     * @param args     массив числовых аргументов
     * @param function вызываемая функция
     * @return результат вычислений
     */
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

    /**
     * Метод, расчитывающий площадь прямоугольника
     *
     * @param args массив числовых аргументов
     * @return результат вычислений
     */
    private static Double getRectangleSquare(Integer[] args) {
        return (double) (args[0] * args[1]);
    }

    /**
     * Метод, расчитывающий площадь треугольника
     *
     * @param args массив числовых аргументов
     * @return результат вычислений
     */
    private static Double getTriangleSquare(Integer[] args) {
        return 0.5 * args[0] * args[1];
    }

    /**
     * Вложенный список, содержит элементы для определения типа функции,
     * которую выбрал пользователь
     *
     * @author Кручинина Кристина
     */
    public enum Function {
        TRIANGLE_SQUARE,
        RECTANGLE_SQUARE
    }
}
