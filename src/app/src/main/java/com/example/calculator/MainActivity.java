package com.example.calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  String[] buttonValue = {"C", "%", "/", "x", "+", "-", "7", "8", "9", "4", "5", "6",
            "1", "2", "3", ".", "0", "="};
    private Button[] buttons = new Button[buttonValue.length];
    private TextView tv;
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv1);
        tv.setFocusable(false);

        gv = (GridView) findViewById(R.id.grid1);

        ButtonAdapter adapter;
        adapter = new ButtonAdapter(getApplicationContext(), buttons, buttonValue, tv);
        gv.setAdapter(adapter);
    }
}