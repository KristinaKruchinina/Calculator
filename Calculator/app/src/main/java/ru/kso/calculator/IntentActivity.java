package ru.kso.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class IntentActivity extends AppCompatActivity {
    static final String EXTRA_MESSAGE = "extra message";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout = new ConstraintLayout(this);
        setContentView(layout);
        Intent intent = getIntent();
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        layout.addView(textView);
        textView.setText(intent.getStringExtra(EXTRA_MESSAGE));
    }
}
