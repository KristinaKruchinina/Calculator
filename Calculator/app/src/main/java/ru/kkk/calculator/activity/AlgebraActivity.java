package ru.kkk.calculator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ru.kkk.calculator.calculator.ArithmeticCalculator;
import ru.kkk.calculator.R;
import ru.kkk.calculator.interfaces.IConstants;

public class AlgebraActivity extends AppCompatActivity
        implements MenuItem.OnMenuItemClickListener, IConstants {
    private TextView expr;
    private TextView result;
    private ArithmeticCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebra);
        expr = findViewById(R.id.expression);
        result = findViewById(R.id.result);
        calculator = new ArithmeticCalculator();
    }

    public void onNumberClick(View view) {
        String exprData = expr.getText().toString();
        String newExprData = exprData + ((Button) view).getText();
        expr.setText(newExprData);
    }

    public void onSignClick(View view) {
        String exprData = expr.getText().toString();
        if (exprData.isEmpty()) {
            return;
        }
        String[] dataArray = exprData.split(" ");
        String sign = dataArray[dataArray.length - 1];
        Log.d("sign", sign);
        for (String s : signs) {
            if (sign.equals(s)) return;
        }
        for (String t : trig) {
            if (sign.equals(t)) return;
        }
        String newExprData = exprData + " " + ((Button) view).getText() + " ";
        expr.setText(newExprData);
    }

    public void clearData(View view) {
        expr.setText("");
        result.setText("");
    }

    public void clear(View view) {
        String exprData = expr.getText().toString().trim();
        if (exprData.equals("") || exprData.isEmpty()) return;
        String clearedData = exprData.substring(0, exprData.length() - 1);
        String last = String.valueOf(exprData.charAt(exprData.length() - 1));
        if (!last.matches("\\d+")) clearedData = clearedData.trim();
        expr.setText(clearedData);
    }

    public void getResult(View view) {
        try {
            Double result = calculator.startCalculate(expr.getText().toString());
            if (result == null) this.result.setText(ERROR);
            else this.result.setText(String.valueOf(result));
        } catch (Exception e) {
            result.setText(ERROR);
        }
    }

    public void onParenthesisClick(View view) {
        Button btn = (Button) view;
        String exprData = expr.getText().toString();
        String[] lefts = getSymbols(exprData, "(");
        String[] rights = getSymbols(exprData, ")");
        boolean bigger = lefts.length > rights.length;
        boolean equals = lefts.length == rights.length;
        if (bigger && !btn.getText().equals(")")) return;
        if (equals && btn.getText().equals(")")) return;
        String newExprData = exprData + ((Button) view).getText();
        expr.setText(newExprData);
    }

    private String[] getSymbols(String exprData, String s) {
        ArrayList<String> list = new ArrayList<>();
        String[] arrData = exprData.split("");
        for (String arrDatum : arrData) {
            if (arrDatum.equals(s)) list.add(arrDatum);
        }
        return list.toArray(new String[0]);
    }

    public void onTrigClick(View view) {
        String exprData = expr.getText().toString();
        String[] arrData = exprData.split(" ");
        for (String t : trig) {
            if (arrData[0].equals(t)) {
                return;
            }
        }
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
