package com.haanhgs.enumcalculatordemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BigDecimal operand1;
    private BigDecimal operand2;
    private boolean isNumbersClicked = false;
    private boolean isOperatorsClicked = false;
    private boolean isResultClicked = false;
    private Calculator.Operator operator;
    private Calculator calculator = new Calculator();

    private TextView tvTest;
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
    private Button bnCE;

    private void initalizeViews(){
        tvTest = findViewById(R.id.tvTest);
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
        bnCE =findViewById(R.id.bnCE);
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
        bnCE.setOnClickListener(this);
    }

    private void resetDisplay(){
        etDisplay.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeViews();
        setupButtons();
        resetDisplay();
    }

    private void appendToDisplay(String string){
        etDisplay.append(string);
    }

    private String returnString(int bnId){
        switch (bnId){
            case R.id.bnZero: return "0";
            case R.id.bnOne: return "1";
            case R.id.bnTwo: return "2";
            case R.id.bnThree: return "3";
            case R.id.bnFour: return "4";
            case R.id.bnFive: return "5";
            case R.id.bnSix: return "6";
            case R.id.bnSeven: return "7";
            case R.id.bnEight: return "8";
            case R.id.bnNine: return "9";
            case R.id.bnDot: return ".";
        }
        return null;
    }



    private void handleNumberButtonClick(View view){
        if (returnString(view.getId()) != null){
            if (isOperatorsClicked){
                etDisplay.setText("");
                appendToDisplay(returnString(view.getId()));
                isOperatorsClicked = false;
            }else {
                appendToDisplay(returnString(view.getId()));
            }
        }
    }

    private void delCharFromDisplay(){
        int length = etDisplay.getText().length();
        if (length >= 1){
            etDisplay.getText().delete(length -1, length);
        }
    }

    private void handleDelButtonClick(View view){
        if (view.getId() == R.id.bnDel) delCharFromDisplay();
    }

    private void handleCeButtonClick(View view){
        if (view.getId() == R.id.bnCE) resetDisplay();
    }

    private static BigDecimal getOperand(EditText editText){
        return new BigDecimal(editText.getText().toString());
    }

    private void handleOperatorsButtonClick(View view){
        if (    view.getId() == R.id.bnAdd
                || view.getId() == R.id.bnSub
                || view.getId() == R.id.bnDiv
                || view.getId() == R.id.bnMul){

            switch (view.getId()){
                case R.id.bnAdd:
                    operator = Calculator.Operator.Add;
                    break;
                case R.id.bnSub:
                    operator = Calculator.Operator.Sub;
                    break;
                case R.id.bnDiv:
                    operator = Calculator.Operator.Div;
                    break;
                case R.id.bnMul:
                    operator = Calculator.Operator.Mul;
                    break;
            }

            try{
                operand1 = getOperand(etDisplay);
                isOperatorsClicked = true;
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            tvTest.setText(operand1 + " " + operator);
        }
    }



    private void handleEqualButton(View view){
        if (operand1 != null && view.getId() == R.id.bnResult){

            try{
                operand2 = getOperand(etDisplay);
            }catch (NumberFormatException e){
                e.printStackTrace();
                return;
            }

            String result = "";
            switch (operator){
                case Add:
                    result = calculator.add(operand1, operand2).toString();
                    break;
                case Sub:
                    result = calculator.sub(operand1, operand2).toString();
                    break;
                case Div:
                    try{
                        result = calculator.div(operand1, operand2).toString();
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                        result = "divide by zero???";
                    }
                    break;
                case Mul:
                    result = calculator.mul(operand1, operand2).toString();
                    break;
                default:
                    result = "Computational error";
                    break;
            }
            etDisplay.setText(result);
            tvTest.setText(operand1 + " " + operator + " " + operand2);
        }
    }

    @Override
    public void onClick(View view) {
        handleNumberButtonClick(view);
        handleDelButtonClick(view);
        handleCeButtonClick(view);
        handleOperatorsButtonClick(view);
        handleEqualButton(view);
    }
}
