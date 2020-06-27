package ru.kkk.calculator.interfaces;

/**
 * Интерфейс, содержащий строковые константы
 *
 * @author Кручинина Кристина 17ИТ17
 */
public interface IConstants {
    String SIGN_PATTERN = "[-+/*^]";
    String TRIG_PATTERN = "(\\(?)(sin|cos|tg|asin|acos|atg)(\\(\\d?)";
    String ERROR = "Ошибка";
    String SIDE_A = "сторона a = %s;";
    String SIDE_B = " сторона b = %s";
    String HEIGHT = " высота = %s";
    String FOUNDATION = "основание = %s;";
    String TRIANGLE_INPUT_DATA = FOUNDATION + HEIGHT;
    String RECTANGLE_INPUT_DATA = SIDE_A + SIDE_B;
}
