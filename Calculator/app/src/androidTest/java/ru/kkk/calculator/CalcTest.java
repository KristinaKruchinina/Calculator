package ru.kkk.calculator;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.kkk.calculator.calculator.ArithmeticCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalcTest {
    private ArithmeticCalculator calculator;

    /**
     * Метод, инициализирующий данные для тестов
     */
    @Before
    public void initData() {
        calculator = new ArithmeticCalculator();
    }

    /**
     * Тест на сложение двух чисел
     */
    @Test
    public void sumTest() {
        int expected = 2 + 2;
        int actual = calculator.startCalculate("2 + 2").intValue();
        assertEquals(expected, actual);
    }

    /**
     * Тест на разность двух чисел
     */
    @Test
    public void subTest() {
        int expected = 27 - 13;
        int actual = calculator.startCalculate("27 - 13").intValue();
        assertEquals(expected, actual);
    }

    /**
     * Тест на деление двух чисел
     */
    @Test
    public void divTest() {
        int expected = 15 / 3;
        int actual = calculator.startCalculate("15 / 3").intValue();
        assertEquals(expected, actual);
    }

    /**
     * Тест на умножение двух чисел
     */
    @Test
    public void multiTest() {
        int expected = 15 * 13;
        int actual = calculator.startCalculate("15 * 13").intValue();
        assertEquals(expected, actual);
    }

    /**
     * Тест на возведение в степень одного числа
     * на другое
     */
    @Test
    public void powTest() {
        int expected = (int) Math.pow(10, 3);
        int actual = calculator.startCalculate("10 ^ 3").intValue();
        assertEquals(expected, actual);
    }

    /**
     * Тест №1 на вычисление выражения
     */
    @Test
    public void exprTestOne() {
        int expected = 122 - (27 - 13);
        int actual = calculator.startCalculate("122 - (27 - 13)").intValue();
        assertEquals(expected, actual);
    }
    /**
     * Тест №2 на вычисление выражения
     */
    @Test
    public void exprTestTwo() {
        int expected = 15 - (33 + 44) * 3;
        int actual = calculator.startCalculate("15 - (33 + 44) * 3").intValue();
        assertEquals(expected, actual);
    }
    /**
     * Тест №3 на вычисление выражения
     */
    @Test
    public void exprTestThree() {
        int expected = (int) (3 + 3 - 10 * 22 / Math.pow(2, 2) - (115 * 2));
        int actual = calculator.startCalculate("3 + 3 - 10 * 22 / 2 ^ 2 - (115 * 2)").intValue();
        assertEquals(expected, actual);
    }
}
