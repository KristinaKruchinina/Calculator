package ru.kkk.calculator.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, выполнящий функцию алгебраического калькулятора
 * для решения математических выражений
 *
 * @author Кручинина Кристина17ИТ17
 */
public class ArithmeticCalculator {
    /**
     * Метод, запускающий цепочку действий, приводящие к постепенному
     * решению алгебраического выражения
     *
     * @param expression алгебраическое выражение
     * @return результат вычислений
     */
    public static Double startCalculate(String expression) {
        if (expression == null) return null;
        if (expression.isEmpty()) return null;
        if (expression.equals("()")) return null;
        Double result;
        double first;
        double second;
        String sign;
        int index;
        expression = startTrigCalc(expression);
        expression = startParenthesisCalc(expression);
        String[] data = expression.split(" ");
        ArrayList<String> listData = new ArrayList<>(Arrays.asList(data));
        while (listData.contains("*") || listData.contains("^") ||
                listData.contains("/")) {
            index = getSignIndex(listData);
            startArithmeticCalc(listData, index);
        }
        if (listData.size() == 1) {
            return Double.valueOf(listData.get(0));
        }
        first = Double.parseDouble(listData.get(0));
        second = Double.parseDouble(listData.get(2));
        sign = listData.get(1);
        result = calculate(first, second, sign);
        if (listData.size() == 3) return result;
        for (int i = 3; i < listData.size(); i += 2) {
            second = Double.parseDouble(listData.get(i + 1));
            sign = listData.get(i);
            result = calculate(result, second, sign);
        }
        return result;
    }

    /**
     * Метод, который вызывает цепочку действий для
     * вычисления результатов тригонометрических функций выражения
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String startTrigCalc(String expression) {
        expression = calcAsin(expression);
        expression = calcAcos(expression);
        expression = calcATG(expression);
        expression = calcSin(expression);
        expression = calcCos(expression);
        expression = calcTG(expression);
        return expression;
    }

    /**
     * Метод, который ищет в выражении все арктангенсы, производит вычисления и
     * обновляет математическое выражение в строковом виде.
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String calcATG(String expression) {
        double result;
        String replacement;
        Pattern pattern = Pattern.compile("(?<=atg\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result = Math.toDegrees(Math.atan(startCalculate(matcher.group())));
            replacement = String.format("atg(%s)", matcher.group());
            expression = expression.replace(replacement, Double.toString(result));
        }
        return expression;
    }

    /**
     * Метод, который ищет в выражении все арккосинусы производит вычисления и
     * обновляет математическое выражение в строковом виде.
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String calcAcos(String expression) {
        double result;
        String replacement;
        Pattern pattern = Pattern.compile("(?<=acos\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result = Math.toDegrees(Math.acos(startCalculate(matcher.group())));
            replacement = String.format("acos(%s)", matcher.group());
            expression = expression.replace(replacement, Double.toString(result));
        }
        return expression;
    }

    /**
     * Метод, который ищет в выражении все арксинусы, производит вычисления и
     * обновляет математическое выражение в строковом виде.
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String calcAsin(String expression) {
        double result;
        String replacement;
        Pattern pattern = Pattern.compile("(?<=asin\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result = Math.toDegrees(Math.asin(startCalculate(matcher.group())));
            replacement = String.format("asin(%s)", matcher.group());
            expression = expression.replace(replacement, Double.toString(result));
        }
        return expression;
    }

    /**
     * Метод, который ищет в выражении все тангенсы, производит вычисления и
     * обновляет математическое выражение в строковом виде.
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String calcTG(String expression) {
        double result;
        String replacement;
        Pattern pattern = Pattern.compile("(?<=tg\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result = Math.tan(Math.toRadians(startCalculate(matcher.group())));
            replacement = String.format("tg(%s)", matcher.group());
            expression = expression.replace(replacement, Double.toString(result));
        }
        return expression;
    }

    /**
     * Метод, который ищет в выражении все косинусы, производит вычисления и
     * обновляет математическое выражение в строковом виде.
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String calcCos(String expression) {
        double result;
        String replacement;
        Pattern pattern = Pattern.compile("(?<=cos\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result = Math.cos(Math.toRadians(startCalculate(matcher.group())));
            replacement = String.format("cos(%s)", matcher.group());
            expression = expression.replace(replacement, Double.toString(result));
        }
        return expression;
    }

    /**
     * Метод, который ищет в выражении все синусы, производит вычисления и
     * обновляет математическое выражение в строковом виде.
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String calcSin(String expression) {
        double result;
        String replacement;
        Pattern pattern = Pattern.compile("(?<=sin\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            result = Math.sin(Math.toRadians(startCalculate(matcher.group())));
            replacement = String.format("sin(%s)", matcher.group());
            expression = expression.replace(replacement, Double.toString(result));
        }
        return expression;
    }

    /**
     * Метод, который ищет в выражении все скобки, производит вычисления
     * выражения в скобках и обновляет математическое выражение в строковом виде.
     *
     * @param expression математическое выражение
     * @return обновленное математическое выражение в строковм виде
     */
    private static String startParenthesisCalc(String expression) {
        Double result;
        Pattern pattern = Pattern.compile("(?<=\\().+?(?=\\))");
        Matcher matcher = pattern.matcher(expression);
        String temp;
        while (matcher.find()) {
            temp = matcher.group();
            result = startCalculate(temp);
            expression = expression.replace("(" + temp + ")",
                    result.toString());
        }

        return expression;
    }

    /**
     * Метод, который возвращает индекс знака операции,
     * в зависимости от позиции знака в массиве или самого знака
     *
     * @param listData массив данных
     * @return индекс знака ^, если имеется, или *, если его позиция впередви всех или /,
     * если также его позиция впереди всех
     */
    private static int getSignIndex(ArrayList<String> listData) {
        int index;
        index = listData.indexOf("^");
        if (index != -1) return index;
        int indexTwo;
        index = listData.indexOf("/");
        indexTwo = listData.indexOf("*");
        if (index == -1 && indexTwo != -1) {
            return indexTwo;
        }
        if (index != -1 && indexTwo == -1) {
            return index;
        }
        if (index < indexTwo) {
            return index;
        }
        if (index > indexTwo) {
            return indexTwo;
        }
        return -1;
    }

    /**
     * Метод, который производит арифметический расчет выражения
     * относительно математических правил (к примеру первым происходит умножение, деление)
     *
     * @param listData массив данных
     * @param index    индекс знака операции
     */
    private static void startArithmeticCalc(ArrayList<String> listData, int index) {
        double first = Double.parseDouble(listData.get(index - 1));
        double second = Double.parseDouble(listData.get(index + 1));
        String sign = listData.get(index);
        for (int i = 0; i < 3; i++) {
            listData.remove(index - 1);
        }
        Double result = calculate(first, second, sign);
        listData.add(index - 1, result.toString());
    }

    /**
     * Метод, который производит расчет простого выражения, состоящего из
     * знака операции и двух чисел
     *
     * @param first  первое число
     * @param second второе число
     * @param sign   знак операции
     * @return результат вычисления
     */
    private static Double calculate(double first, double second, String sign) {
        Double result = null;
        switch (sign) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "/":
                result = first / second;
                break;
            case "*":
                result = first * second;
                break;
            case "^":
                result = Math.pow(first, second);
                break;

        }
        return result;
    }
}
