package com.haanhgs.enumcalculatordemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etDisplay;
    private Button bnAdd;
    private Button bnSub;
    private Button bnDiv;
    private Button bnMul;
    private Button bnOne;
    private Button bnTwo;
    private Button bnThree;
    private Button bnResult;
    private Button bnFour;
    private Button bnFive;
    private Button bnSix;
    private Button bnSeven;
    private Button bnEight;
    private Button bnNine;
    private Button bnZero;
    private Button bnDot;
    private Button bnDel;

    private void initalizeViews(){
        etDisplay = findViewById(R.id.etDisplay);
        bnAdd = findViewById(R.id.bnAdd);
        bnSub = findViewById(R.id.bnSub);
        bnDiv = findViewById(R.id.bnDiv);
        bnMul = findViewById(R.id.bnMul);
        bnResult = findViewById(R.id.bnResult);
        bnDel = findViewById(R.id.bnDel);
        bnDot = findViewById(R.id.bnDot);
        bnZero = findViewById(R.id.bnZero);
        bnOne = findViewById(R.id.bnOne);
        bnTwo = findViewById(R.id.bnTwo);
        bnThree = findViewById(R.id.bnThree);
        bnFour = findViewById(R.id.bnFour);
        bnFive = findViewById(R.id.bnFive);
        bnSix = findViewById(R.id.bnSix);
        bnSeven = findViewById(R.id.bnSeven);
        bnEight = findViewById(R.id.bnEight);
        bnNine = findViewById(R.id.bnNine);
    }

    private void setupButtons(){
        bnAdd.setOnClickListener(this);
        bnSub.setOnClickListener(this);
        bnDiv.setOnClickListener(this);
        bnMul.setOnClickListener(this);
        bnResult.setOnClickListener(this);
        bnDel.setOnClickListener(this);
        bnDot.setOnClickListener(this);
        bnZero.setOnClickListener(this);
        bnOne.setOnClickListener(this);
        bnTwo.setOnClickListener(this);
        bnThree.setOnClickListener(this);
        bnFour.setOnClickListener(this);
        bnFive.setOnClickListener(this);
        bnSix.setOnClickListener(this);
        bnSeven.setOnClickListener(this);
        bnEight.setOnClickListener(this);
        bnNine.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeViews();
        setupButtons();
    }

    @Override
    public void onClick(View v) {

    }
}
