package ru.kkk.calculator.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ArithmeticCalculator {

    public Double startCalculate(String expression) {
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

    private String startTrigCalc(String expression) {
        expression = calcAsin(expression);
        expression = calcAcos(expression);
        expression = calcATG(expression);
        expression = calcSin(expression);
        expression = calcCos(expression);
        expression = calcTG(expression);
        return expression;
    }

    private String calcATG(String expression) {
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

    private String calcAcos(String expression) {
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

    private String calcAsin(String expression) {
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

    private String calcTG(String expression) {
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

    private String calcCos(String expression) {
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

    private String calcSin(String expression) {
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

    private String startParenthesisCalc(String expression) {
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

    private int getSignIndex(ArrayList<String> listData) {
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

    private void startArithmeticCalc(ArrayList<String> listData, int index) {
        double first = Double.parseDouble(listData.get(index - 1));
        double second = Double.parseDouble(listData.get(index + 1));
        String sign = listData.get(index);
        for (int i = 0; i < 3; i++) {
            listData.remove(index - 1);
        }
        Double result = calculate(first, second, sign);
        listData.add(index - 1, result.toString());
    }


    private Double calculate(double first, double second, String sign) {
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
