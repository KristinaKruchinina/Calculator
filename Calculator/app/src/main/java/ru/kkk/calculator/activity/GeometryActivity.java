package ru.kkk.calculator.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.kkk.calculator.R;
import ru.kkk.calculator.interfaces.IConstants;

public class GeometryActivity extends AppCompatActivity implements IConstants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometry);
    }
}
