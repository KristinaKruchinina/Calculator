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
import ru.kkk.calculator.calculator.ArithmeticCalculator;
import ru.kkk.calculator.interfaces.IConstants;

/**
 * Класс, который представляет собой алгебраическое окно
 * калькулятора
 *
 * @author Кручинина Кристина 17ИТ17
 */
public class AlgebraActivity extends AppCompatActivity
        implements MenuItem.OnMenuItemClickListener, IConstants {
    private TextView expr;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebra);
        expr = findViewById(R.id.expression);
        result = findViewById(R.id.result);
    }

    /**
     * Метод, который вводит цифру нажатой числовой кнопки.
     *
     * @param view интерфейсный компонент системы
     */
    public void onNumberClick(View view) {
        String exprData = expr.getText().toString();
        String newExprData = exprData + ((Button) view).getText();
        expr.setText(newExprData);
    }

    /**
     * Метод, который вводит знак операции нажатой кнопки-операции.
     *
     * @param view интерфейсный компонент системы
     */
    public void onSignClick(View view) {
        String exprData = expr.getText().toString();
        if (exprData.isEmpty()) {
            return;
        }
        if (SIGN_PATTERN.matcher(exprData).find()) return;
        if (TRIG_PATTERN.matcher(exprData).find()) return;
        String newExprData = exprData + " " + ((Button) view).getText() + " ";
        expr.setText(newExprData);
    }

    /**
     * Метод, который очищает поля ввода и результата
     *
     * @param view интерфейсный компонент системы
     */
    public void clearData(View view) {
        expr.setText("");
        result.setText("");
    }

    /**
     * Метод, который удаляет последний введенный пользователем
     * символ
     *
     * @param view интерфейсный компонент системы
     */
    public void clear(View view) {
        String exprData = expr.getText().toString().trim();
        if (exprData.equals("") || exprData.isEmpty()) return;
        String clearedData = exprData.substring(0, exprData.length() - 1);
        String last = String.valueOf(exprData.charAt(exprData.length() - 1));
        if (!last.matches("\\d+")) clearedData = clearedData.trim();
        expr.setText(clearedData);
    }

    /**
     * Метод, который запускает функцию для решения выражения и
     * выводит результат вычислений пользователю
     *
     * @param view интерфейсный компонент системы
     */
    public void getResult(View view) {
        try {
            Double result = ArithmeticCalculator.startCalculate(expr.getText().toString());
            if (result == null) this.result.setText(ERROR);
            else this.result.setText(String.valueOf(result));
        } catch (Exception e) {
            result.setText(ERROR);
        }
    }

    /**
     * Метод, который вводит скобку нажатой кнопки со скобкой.
     *
     * @param view интерфейсный компонент системы
     */
    public void onParenthesisClick(View view) {
        Button btn = (Button) view;
        String exprData = expr.getText().toString();
        int lefts = getQuantityOfSimilar(exprData, "(");
        int rights = getQuantityOfSimilar(exprData, ")");
        boolean bigger = lefts > rights;
        boolean equals = lefts == rights;
        if (bigger && !btn.getText().equals(")")) return;
        if (equals && btn.getText().equals(")")) return;
        String newExprData = exprData + ((Button) view).getText();
        expr.setText(newExprData);
    }

    /**
     * Метод для поиска количества совпадений в искомой строке, разбимтой на массив
     * при помощи разделителя - пробела.
     *
     * @param string искомая строка
     * @param c      - набор символов для поиска
     * @return количество найденных совпадений
     */
    private int getQuantityOfSimilar(String string, String c) {
        int q = 0;
        String[] arrData = string.split("");
        for (String arrDatum : arrData) {
            if (arrDatum.contains(c)) q++;
        }
        return q;
    }

    /**
     * Метод, который вводит тригонометрическую функцию нажатой
     * тригонометрической кнопки.
     *
     * @param view интерфейсный компонент системы
     */
    public void onTrigClick(View view) {
        String exprData = expr.getText().toString();
        if (TRIG_PATTERN.matcher(exprData).find()) return;
        String newExprData = exprData + ((Button) view).getText() + "(";
        expr.setText(newExprData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.arithmetic_tb_menu, menu);
        menu.findItem(R.id.geometry_type).setOnMenuItemClickListener(this);
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent(this, GeometryActivity.class);
        startActivity(intent);
        finish();
        return true;
    }
}
