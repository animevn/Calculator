package com.haanhgs.calculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.math.BigDecimal;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.haanhgs.calculator.Calculator.appendToDisplay;
import static com.haanhgs.calculator.Calculator.calculateResult;
import static com.haanhgs.calculator.Calculator.checkSignOfDisplay;
import static com.haanhgs.calculator.Calculator.getOperand;
import static com.haanhgs.calculator.Calculator.returnOperator;
import static com.haanhgs.calculator.Calculator.returnString;
import static com.haanhgs.calculator.Calculator.setPortraitMode;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvDisplay)
    TextView tvDisplay;
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
    @BindView(R.id.bnSquare)
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
        tvDisplay.setText("");
    }

    private void resetTextHelper() {
        tvTest.setText("");
    }

    private void hideActionBar(){
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionBar();
        resetDisplay();
        resetTextHelper();
    }

    private void removeMinusSignOffDisplay() {
        if (!TextUtils.isEmpty(tvDisplay.getText())) {
            String string = tvDisplay.getText().toString();
            tvDisplay.setText(string.substring(0, 1));
        }
    }

    private void addMinusSignToDisplay() {
        if (TextUtils.isEmpty(tvDisplay.getText())) {
            tvDisplay.setText("-");
        } else if (isOperatorsClicked) {
            tvDisplay.setText("-");
        } else {
            tvDisplay.setText(String.format("%s", "-" + tvDisplay.getText().toString()));
        }
    }

    private void handleSignButton(View view) {
        if (view.getId() == R.id.bnSign) {
            if (checkSignOfDisplay(tvDisplay)) {
                removeMinusSignOffDisplay();
            } else {
                addMinusSignToDisplay();
            }
        }
    }

    private void setupTextHelperforOperands() {
        if (isFirstOperand) {
            tvTest.setText(tvDisplay.getText().toString());
        } else if (isSecondOperand) {
            String text = operand1.toPlainString() + " " + operator.sign + " "
                    + tvDisplay.getText().toString();
            tvTest.setText(text);
        }
    }

    private void handleNumberButtons(View view) {
        if (returnString(view.getId()) != null) {
            if (isResultClicked) {
                resetDisplay();
                appendToDisplay(returnString(view.getId()), tvDisplay);
                isResultClicked = false;
                isOperatorsClicked = false;
            } else if (isOperatorsClicked) {
                if (tvDisplay.getText().toString().equals("-")) {
                    appendToDisplay(returnString(view.getId()), tvDisplay);
                } else {
                    resetDisplay();
                    appendToDisplay(returnString(view.getId()), tvDisplay);
                    isOperatorsClicked = false;
                }

            } else {
                appendToDisplay(returnString(view.getId()), tvDisplay);
            }
            setupTextHelperforOperands();
        }
    }

    private void handleOperatorsButtons(View view) {
        if (view.getId() == R.id.bnAdd || view.getId() == R.id.bnSub
                || view.getId() == R.id.bnDiv || view.getId() == R.id.bnMul) {
            operator = returnOperator(view);
            try {
                operand1 = getOperand(tvDisplay);
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
                operand2 = getOperand(tvDisplay);
                isResultClicked = true;
                isFirstOperand = true;
                isSecondOperand = false;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            try {
                calculateResult(operator, operand1,operand2, tvDisplay);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                tvDisplay.setText(getResources().getText(R.string.error));
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
            int length = tvDisplay.getText().length();
            if (length >= 1) {
                String string = tvDisplay.getText().toString();
                tvDisplay.setText(string.substring(0, 1));
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
