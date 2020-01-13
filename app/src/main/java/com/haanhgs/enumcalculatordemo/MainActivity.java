package com.haanhgs.enumcalculatordemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haanhgs.enumcalculatordemo.Calculator.appendToDisplay;
import static com.haanhgs.enumcalculatordemo.Calculator.calculateResult;
import static com.haanhgs.enumcalculatordemo.Calculator.checkSignOfDisplay;
import static com.haanhgs.enumcalculatordemo.Calculator.getOperand;
import static com.haanhgs.enumcalculatordemo.Calculator.returnOperator;
import static com.haanhgs.enumcalculatordemo.Calculator.returnString;
import static com.haanhgs.enumcalculatordemo.Calculator.setPortraitMode;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etDisplay)
    EditText etDisplay;
    @BindView(R.id.bnAdd)
    Button bnAdd;
    @BindView(R.id.bnZero)
    Button bnZero;
    @BindView(R.id.bnThree)
    Button bnThree;
    @BindView(R.id.bnFour)
    Button bnFour;
    @BindView(R.id.bnSeven)
    Button bnSeven;
    @BindView(R.id.bnEight)
    Button bnEight;
    @BindView(R.id.bnNine)
    Button bnNine;
    @BindView(R.id.bnEmpty1)
    Button bnEmpty1;
    @BindView(R.id.bnFive)
    Button bnFive;
    @BindView(R.id.bnSix)
    Button bnSix;
    @BindView(R.id.bnDot)
    Button bnDot;
    @BindView(R.id.bnSign)
    Button bnSign;
    @BindView(R.id.bnDel)
    Button bnDel;
    @BindView(R.id.bnCE)
    Button bnCE;
    @BindView(R.id.bnOne)
    Button bnOne;
    @BindView(R.id.bnTwo)
    Button bnTwo;
    @BindView(R.id.bnSub)
    Button bnSub;
    @BindView(R.id.bnDiv)
    Button bnDiv;
    @BindView(R.id.bnMul)
    Button bnMul;
    @BindView(R.id.bnResult)
    Button bnResult;
    @BindView(R.id.tvTest)
    TextView tvTest;

    private BigDecimal operand1;
    private BigDecimal operand2;
    private Operator operator;

    private boolean isOperatorsClicked = false;
    private boolean isResultClicked = false;
    private boolean isFirstOperand = true;
    private boolean isSecondOperand = false;

    private void resetDisplay() {
        etDisplay.setText("");
    }

    private void resetTextHelper() {
        tvTest.setText("");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setPortraitMode(this);
        resetDisplay();
        resetTextHelper();
    }

    private void removeMinusSignOffDisplay() {
        if (!TextUtils.isEmpty(etDisplay.getText())) {
            etDisplay.getText().delete(0, 1);
        }
    }

    private void addMinusSignToDisplay() {
        if (TextUtils.isEmpty(etDisplay.getText())) {
            etDisplay.setText("-");
        } else if (isOperatorsClicked) {
            etDisplay.setText("-");
        } else {
            etDisplay.setText(String.format("%s", "-" + etDisplay.getText().toString()));
        }
    }

    private void handleSignButton(View view) {
        if (view.getId() == R.id.bnSign) {
            if (checkSignOfDisplay(etDisplay)) {
                removeMinusSignOffDisplay();
            } else {
                addMinusSignToDisplay();
            }
        }
    }

    private void setupTextHelperforOperands() {
        if (isFirstOperand) {
            tvTest.setText(etDisplay.getText().toString());
        } else if (isSecondOperand) {
            String text = operand1.toPlainString() + " " + operator.sign + " "
                    + etDisplay.getText().toString();
            tvTest.setText(text);
        }
    }

    private void handleNumberButtons(View view) {
        if (returnString(view.getId()) != null) {
            if (isResultClicked) {
                resetDisplay();
                appendToDisplay(returnString(view.getId()), etDisplay);
                isResultClicked = false;
                isOperatorsClicked = false;
            } else if (isOperatorsClicked) {
                if (etDisplay.getText().toString().equals("-")) {
                    appendToDisplay(returnString(view.getId()), etDisplay);
                } else {
                    resetDisplay();
                    appendToDisplay(returnString(view.getId()), etDisplay);
                    isOperatorsClicked = false;
                }

            } else {
                appendToDisplay(returnString(view.getId()), etDisplay);
            }
            setupTextHelperforOperands();
        }
    }

    private void handleOperatorsButtons(View view) {
        if (view.getId() == R.id.bnAdd || view.getId() == R.id.bnSub
                || view.getId() == R.id.bnDiv || view.getId() == R.id.bnMul) {
            operator = returnOperator(view);
            try {
                operand1 = getOperand(etDisplay);
                isOperatorsClicked = true;
                isFirstOperand = false;
                isSecondOperand = true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            tvTest.setText(String.format("%s", operand1.toPlainString() + " " + operator.sign));
        }
    }

    private void handleEqualButton(View view) {
        if (operand1 != null && view.getId() == R.id.bnResult) {
            try {
                operand2 = getOperand(etDisplay);
                isResultClicked = true;
                isFirstOperand = true;
                isSecondOperand = false;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            try {
                calculateResult(operator, operand1,operand2, etDisplay);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                etDisplay.setText(getResources().getText(R.string.error));
            }
            String text = "" + operand1.toPlainString() + " " + operator.sign + " "
                    + operand2.toPlainString() + " = ";
            tvTest.setText(String.format("%s", text));
        }
    }

    private void handleDelButton(View view) {
        if (view.getId() == R.id.bnCE) {
            resetDisplay();
            resetTextHelper();
            operand1 = null;
            operand2 = null;
            isFirstOperand = true;
            isSecondOperand = false;
        }
    }

    private void handleCeButton(View view) {
        if (view.getId() == R.id.bnDel){
            int length = etDisplay.getText().length();
            if (length >= 1) {
                etDisplay.getText().delete(length - 1, length);
            }
        }
    }

    @OnClick({R.id.bnAdd, R.id.bnZero, R.id.bnThree, R.id.bnFour, R.id.bnSeven, R.id.bnEight,
            R.id.bnNine, R.id.bnFive, R.id.bnSix, R.id.bnDot, R.id.bnSign, R.id.bnDel, R.id.bnCE,
            R.id.bnOne, R.id.bnTwo, R.id.bnSub, R.id.bnDiv, R.id.bnMul, R.id.bnResult})
    public void onViewClicked(View view) {
        handleSignButton(view);
        handleNumberButtons(view);
        handleOperatorsButtons(view);
        handleEqualButton(view);
        handleCeButton(view);
        handleDelButton(view);
    }
}
