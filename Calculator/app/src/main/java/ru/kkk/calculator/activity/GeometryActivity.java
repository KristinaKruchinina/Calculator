package ru.kkk.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.kkk.calculator.R;
import ru.kkk.calculator.calculator.GeometricCalculator;
import ru.kkk.calculator.interfaces.IConstants;

public class GeometryActivity extends AppCompatActivity
        implements MenuItem.OnMenuItemClickListener, IConstants {

    private String[] funcData;
    private int[] numberData;
    private String currentNumber;
    private TextView data;
    private TextView result;
    private int currentID;
    private GeometricCalculator geometricCalculator;
    private GeometricCalculator.Function currentFunction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry);
        data = findViewById(R.id.data);
        result = findViewById(R.id.result);
        funcData = new String[0];
        numberData = new int[0];
        currentID = 0;
        currentNumber = "";
        geometricCalculator = new GeometricCalculator();
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


    public void clear(View view) {
        currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        numberData[currentID] = Integer.parseInt(currentNumber);
        String inputData = String.format(funcData[currentID], currentNumber);
        String[] arrayCurrentData = data.getText().toString().split(";");
        arrayCurrentData[currentID] = inputData.replace(";", "");
        data.setText(getNewData(arrayCurrentData));

    }

    public void getResult(View view) {
        Log.d("d", Arrays.toString(numberData));
        Double res = geometricCalculator.startCalculate(numberData, currentFunction);
        result.setText(String.valueOf(res));

    }

    public void onNumberClick(View view) {
        currentNumber += ((Button) view).getText().toString();
        numberData[currentID] = Integer.parseInt(currentNumber);
        String inputData = String.format(funcData[currentID], currentNumber);
        String[] arrayCurrentData = data.getText().toString().split(";");
        arrayCurrentData[currentID] = inputData.replace(";", "");
        data.setText(getNewData(arrayCurrentData));

    }

    private String getNewData(String[] arrayCurrentData) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayCurrentData.length; i++) {
            builder.append(arrayCurrentData[i]);
            if (i != arrayCurrentData.length - 1) builder.append(";");
        }
        return builder.toString();
    }

    public void clearData(View view) {
        data.setText("");
        result.setText("");
    }

    public void onNextData(View view) {
        currentNumber = "";
        if (currentID == funcData.length - 1) {
            currentID = 0;
        } else {
            currentID++;
        }
    }

    public void onTriangleClick(View view) {
        String initInputData = String.format(TRIANGLE_INPUT_DATA, "", "");
        data.setText(initInputData);
        funcData = new String[2];
        numberData = new int[2];
        funcData[0] = FOUNDATION;
        funcData[1] = HEIGHT;
        currentFunction = GeometricCalculator.Function.TRIANGLE_SQUARE;
    }

    public void onRectangleClick(View view) {
        String initInputData = String.format(RECTANGLE_INPUT_DATA, "", "");
        data.setText(initInputData);
        funcData = new String[2];
        numberData = new int[2];
        funcData[0] = SIDE_A;
        funcData[1] = SIDE_B;
        currentFunction = GeometricCalculator.Function.RECTANGLE_SQUARE;
    }


}
