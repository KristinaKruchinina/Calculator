package ru.kkk.calculator.interfaces;

public interface IConstants {
    String[] signs = {"+", "-", "/", "*", "^"};
    String[] trig = {"sin(", "cos(", "tg(", "asin(",
            "acos(", "atg("};
    String ERROR = "Ошибка";
    String SIDE_A = "сторона a = %s;";
    String SIDE_B = " сторона b = %s";
    String HEIGHT = " высота = %s";
    String FOUNDATION = "основание = %s;";
    String TRIANGLE_INPUT_DATA = FOUNDATION + HEIGHT;
    String RECTANGLE_INPUT_DATA = SIDE_A + SIDE_B;
}
