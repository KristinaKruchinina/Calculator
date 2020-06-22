package ru.kso.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String ENTER_INT_NUMBER = "Введите целое число";
    EditText editTextFirst;
    EditText editTextSecond;
    String valueEditFirst;
    String valueEditSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnAdd(View view) {
        editTextFirst = findViewById(R.id.edit_first_number);
        editTextSecond = findViewById(R.id.edit_second_number);
        if (isNotCorrectData(editTextFirst.getText().toString())) {
            editTextFirst.setError(ENTER_INT_NUMBER);
            return;
        }
        if (isNotCorrectData(editTextSecond.getText().toString())) {
            editTextSecond.setError(ENTER_INT_NUMBER);
            return;
        }
        int firstNumber = Integer.parseInt(editTextFirst.getText().toString());
        int secondNumber = Integer.parseInt(editTextSecond.getText().toString());
        String resultOfAdd = generateExpression(firstNumber, secondNumber) + add(firstNumber, secondNumber);

        Intent intent = new Intent(this, IntentActivity.class);
        intent.putExtra(IntentActivity.EXTRA_MESSAGE, resultOfAdd);
        startActivity(intent);

    }

    /**
     * Метод, который генерирует строковое выражение в зависимости от знака операнда
     *
     * @param firstNumber  первое число
     * @param secondNumber второе число
     * @return строковое выражение (без результата вычислений)
     */
    private String generateExpression(int firstNumber, int secondNumber) {
        if (secondNumber < 0) {
            return firstNumber + " + " + " (" + secondNumber + ") = ";
        }
        return firstNumber + " + " + secondNumber + " = ";
    }

    /**
     * Метод, который складывает два число
     *
     * @param firstNumber  первое число
     * @param secondNumber второе число
     * @return {@code} firstNumber + {@code} secondNumber
     */
    private int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    /**
     * Метод, проверяющий данные на корректность
     *
     * @param data проверяемые данные
     * @return true если данная строка целое число. false если данная строка не
     * целое число
     */
    private boolean isNotCorrectData(String data) {
        return !data.matches("[+-]?\\d+");
    }

    @Override
    public void onSaveInstanceState(Bundle savedBundle) {
        super.onSaveInstanceState(savedBundle);
        editTextFirst = findViewById(R.id.edit_first_number);
        editTextSecond = findViewById(R.id.edit_second_number);
        savedBundle.putString("firstNumber", editTextFirst.getText().toString());
        savedBundle.putString("secondNumber", editTextSecond.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle outState) {
        if (outState.containsKey("firstNumber")) {
            valueEditFirst = outState.getString("firstNumber");
            resetEditFirst();
        }
        if (outState.containsKey("secondNumber")) {
            valueEditSecond = outState.getString("secondNumber");
            resetEditSecond();
        }
    }

    /**
     * Восстановление данных поля ввода для первого числа
     */
    private void resetEditSecond() {
        editTextSecond = findViewById(R.id.edit_second_number);
        editTextSecond.setText(valueEditSecond);

    }

    /**
     * Восстановление данных поля ввода для второго число
     */
    private void resetEditFirst() {
        editTextFirst = findViewById(R.id.edit_first_number);
        editTextFirst.setText(valueEditFirst);
    }
}
