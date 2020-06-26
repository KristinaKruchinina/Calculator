package ru.kkk.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.kkk.calculator.R;
import ru.kkk.calculator.calculator.GeometricCalculator;
import ru.kkk.calculator.interfaces.IConstants;

/**
 * Класс, представляющее собой окно геометрического типа калькулятора
 *
 * @author Кручинина Кристина 17ИТ17
 */
public class GeometryActivity extends AppCompatActivity
        implements MenuItem.OnMenuItemClickListener, IConstants {

    private String[] funcData;
    private Integer[] numberData;
    private String currentNumber;
    private TextView data;
    private TextView result;
    private int currentID;
    private GeometricCalculator.Function currentFunction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry);
        data = findViewById(R.id.data);
        result = findViewById(R.id.result);
        funcData = new String[0];
        numberData = new Integer[0];
        currentID = 0;
        currentNumber = "";
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent(this, AlgebraActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.geometry_tb_menu, menu);
        menu.findItem(R.id.algebra_type).setOnMenuItemClickListener(this);
        return true;
    }

    /**
     * Метод, который удаляет последний введенный пользователем
     * символ
     *
     * @param view интерфейсный компонент системы
     */
    public void clear(View view) {
        if (data.getText().length() == 0) return;
        if (currentNumber.isEmpty()) return;
        currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        if (currentNumber.equals("")) {
            numberData[currentID] = null;
        } else {
            numberData[currentID] = Integer.parseInt(currentNumber);
        }
        setNewData();

    }

    /**
     * Метод, который запускает функцию для вычисления площади фигуры и
     * выводит результат вычислений пользователю
     *
     * @param view интерфейсный компонент системы
     */
    public void getResult(View view) {
        if (numberData.length == 0) return;
        Double res = GeometricCalculator.startCalculate(numberData, currentFunction);
        result.setText(String.valueOf(res));

    }

    /**
     * Метод, который вводит цифру нажатой числовой кнопки.
     *
     * @param view интерфейсный компонент системы
     */
    public void onNumberClick(View view) {
        if (funcData.length == 0) return;
        currentNumber += ((Button) view).getText().toString();
        numberData[currentID] = Integer.parseInt(currentNumber);
        setNewData();

    }

    /**
     * Метод, который обновляет поле ввода и массив данных новыми
     * введенными пользователем данными
     */
    private void setNewData() {
        String inputData = String.format(funcData[currentID], currentNumber);
        String[] arrayCurrentData = data.getText().toString().split(";");
        arrayCurrentData[currentID] = inputData.replace(";", "");
        data.setText(getNewData(arrayCurrentData));
    }

    /**
     * Формирует новую строку относительно новых введенных данных
     *
     * @param arrayCurrentData массив данных
     * @return обновленная строка данных
     */
    private String getNewData(String[] arrayCurrentData) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayCurrentData.length; i++) {
            builder.append(arrayCurrentData[i]);
            if (i != arrayCurrentData.length - 1) builder.append(";");
        }
        return builder.toString();
    }

    /**
     * Метод, который очищает поля ввода и результата
     *
     * @param view интерфейсный компонент системы
     */
    public void clearData(View view) {
        data.setText("");
        result.setText("");
        funcData = new String[0];
        numberData = new Integer[0];
    }

    /**
     * Метод, который устанавливает курсор ввода данных
     * на следующий уровень
     *
     * @param view интерфейсный коспонент системы
     */
    public void onNextData(View view) {
        if (funcData.length == 0) return;
        currentNumber = "";
        if (currentID == funcData.length - 1) {
            currentID = 0;
        } else {
            currentID++;
        }
    }

    /**
     * Метод, который устанавливает ввод текущей функции как
     * нахождение площади треугольника
     *
     * @param view интерфейсный коспонент системы
     */
    public void onTriangleClick(View view) {
        String initInputData = String.format(TRIANGLE_INPUT_DATA, "", "");
        data.setText(initInputData);
        funcData = new String[2];
        numberData = new Integer[2];
        funcData[0] = FOUNDATION;
        funcData[1] = HEIGHT;
        currentFunction = GeometricCalculator.Function.TRIANGLE_SQUARE;
    }

    /**
     * Метод, который устанавливает ввод текущей функции как
     * нахождение площади параллелограмма
     *
     * @param view интерфейсный коспонент системы
     */
    public void onRectangleClick(View view) {
        String initInputData = String.format(RECTANGLE_INPUT_DATA, "", "");
        data.setText(initInputData);
        funcData = new String[2];
        numberData = new Integer[2];
        funcData[0] = SIDE_A;
        funcData[1] = SIDE_B;
        currentFunction = GeometricCalculator.Function.RECTANGLE_SQUARE;
    }


}
