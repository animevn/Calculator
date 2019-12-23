package com.haanhgs.enumcalculatordemo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    private Calculator.Operator operator;
    private final Calculator calculator = new Calculator();

    private boolean isOperatorsClicked = false;
    private boolean isResultClicked = false;
    private boolean isFirstOperand = true;
    private boolean isSecondOperand = false;


    private void hideActionBarAndForcePortraitMode() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void setupButtons() {
        bnAdd.setOnClickListener(this);
        bnSub.setOnClickListener(this);
        bnDiv.setOnClickListener(this);
        bnMul.setOnClickListener(this);

        bnResult.setOnClickListener(this);

        bnCE.setOnClickListener(this);
        bnDel.setOnClickListener(this);

        bnDot.setOnClickListener(this);
        bnSign.setOnClickListener(this);

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

    private void resetDisplay() {
        etDisplay.setText("");
    }

    private void resetTextHelper() {
        tvTest.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionBarAndForcePortraitMode();
        setupButtons();
        resetDisplay();
        resetTextHelper();
    }

    private void appendToDisplay(String string) {
        etDisplay.append(string);
    }

    private String returnString(int bnId) {
        switch (bnId) {
            case R.id.bnZero:
                return "0";
            case R.id.bnOne:
                return "1";
            case R.id.bnTwo:
                return "2";
            case R.id.bnThree:
                return "3";
            case R.id.bnFour:
                return "4";
            case R.id.bnFive:
                return "5";
            case R.id.bnSix:
                return "6";
            case R.id.bnSeven:
                return "7";
            case R.id.bnEight:
                return "8";
            case R.id.bnNine:
                return "9";

            case R.id.bnDot:
                return ".";
        }
        return null;
    }

    private String getFirstChar(String string) {
        String result = "";
        if (!TextUtils.isEmpty(string)) {
            result = string.substring(0, 1);
        }
        return result;
    }

    private boolean checkSignOfDisplay() {
        String firstChar = getFirstChar(etDisplay.getText().toString());
        return firstChar.equals("-");
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
            if (checkSignOfDisplay()) {
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
            String text = operand1 + " " + operator.sign + " " + etDisplay.getText().toString();
            tvTest.setText(String.format("%s", text));
        }
    }

    private void handleNumberButtonClick(View view) {
        if (returnString(view.getId()) != null) {
            if (isResultClicked) {
                resetDisplay();
                appendToDisplay(returnString(view.getId()));
                isResultClicked = false;
                isOperatorsClicked = false;
            } else if (isOperatorsClicked) {
                if (etDisplay.getText().toString().equals("-")) {
                    appendToDisplay(returnString(view.getId()));
                } else {
                    resetDisplay();
                    appendToDisplay(returnString(view.getId()));
                    isOperatorsClicked = false;
                }

            } else {
                appendToDisplay(returnString(view.getId()));
            }
            setupTextHelperforOperands();
        }
    }

    private void delCharFromDisplay() {
        int length = etDisplay.getText().length();
        if (length >= 1) {
            etDisplay.getText().delete(length - 1, length);
        }
    }

    private void handleDelButtonClick(View view) {
        if (view.getId() == R.id.bnDel) delCharFromDisplay();
    }

    private void handleCeButtonClick(View view) {
        if (view.getId() == R.id.bnCE) {
            resetDisplay();
            resetTextHelper();
            operand1 = null;
            operand2 = null;
            isFirstOperand = true;
            isSecondOperand = false;
        }
    }

    private static BigDecimal getOperand(EditText editText) {
        return new BigDecimal(editText.getText().toString());
    }

    private void returnOperator(View view) {
        switch (view.getId()) {
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
    }

    private void handleOperatorsButtonClick(View view) {
        if (view.getId() == R.id.bnAdd
                || view.getId() == R.id.bnSub
                || view.getId() == R.id.bnDiv
                || view.getId() == R.id.bnMul) {
            returnOperator(view);
            try {
                operand1 = getOperand(etDisplay);
                isOperatorsClicked = true;
                isFirstOperand = false;
                isSecondOperand = true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            tvTest.setText(String.format("%s", operand1 + " " + operator.sign));
        }
    }

    private void calculateResult() {
        String result;
        switch (operator) {
            case Add:
                result = calculator.add(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            case Sub:
                result = calculator.sub(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            case Div:
                result = calculator.div(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            case Mul:
                result = calculator.mul(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            default:
                result = "Computational error";
                break;
        }
        etDisplay.setText(String.format("%s", result));
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
                calculateResult();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                etDisplay.setText(getResources().getText(R.string.error));
            }
            String text = "" + operand1 + " " + operator.sign + " " + operand2 + " = ";
            tvTest.setText(String.format("%s", text));
        }
    }

    @Override
    public void onClick(View view) {
        handleSignButton(view);
        handleNumberButtonClick(view);
        handleDelButtonClick(view);
        handleCeButtonClick(view);
        handleOperatorsButtonClick(view);
        handleEqualButton(view);
    }
}
