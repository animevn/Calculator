package com.haanhgs.calculator.view;

import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.haanhgs.calculator.R;
import com.haanhgs.calculator.model.Calculator;
import com.haanhgs.calculator.model.Operator;
import com.haanhgs.calculator.viewmodel.CalculatorViewModel;

import java.math.BigDecimal;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvDisplay)
    TextView tvDisplay;
    @BindView(R.id.tvSecond)
    TextView tvSecond;
    @BindView(R.id.bnAdd)
    Button bnAdd;
    @BindView(R.id.bnThree)
    Button bnThree;
    @BindView(R.id.bnFour)
    Button bnFour;
    @BindView(R.id.bnSeven)
    Button bnSeven;
    @BindView(R.id.bnSign)
    Button bnSign;
    @BindView(R.id.bnZero)
    Button bnZero;
    @BindView(R.id.bnDot)
    Button bnDot;
    @BindView(R.id.bnNine)
    Button bnNine;
    @BindView(R.id.bnEight)
    Button bnEight;
    @BindView(R.id.bnResult)
    Button bnResult;
    @BindView(R.id.bnFive)
    Button bnFive;
    @BindView(R.id.bnSix)
    Button bnSix;
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
    @BindView(R.id.bnSquare)
    Button bnSquare;

//
//    private BigDecimal operand1;
//    private BigDecimal operand2;
//    private Operator operator;
//
//    private boolean isOperatorsClicked = false;
//    private boolean isResultClicked = false;
//    private boolean isFirstOperand = true;
//    private boolean isSecondOperand = false;

    private CalculatorViewModel viewModel;

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        viewModel.getData().observe(this, calculator -> {
            tvDisplay.setText(calculator.getStringMain());
            tvSecond.setText(calculator.getStringSecond());
        });
    }


    private void hideActionBar() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
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
        initViewModel();
    }

    @OnClick({R.id.bnAdd, R.id.bnThree, R.id.bnFour, R.id.bnSeven, R.id.bnSign, R.id.bnZero,
            R.id.bnDot, R.id.bnNine, R.id.bnEight, R.id.bnResult, R.id.bnFive,
            R.id.bnSix, R.id.bnDel, R.id.bnCE, R.id.bnOne, R.id.bnTwo, R.id.bnSub, R.id.bnDiv,
            R.id.bnMul, R.id.bnSquare})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnZero:
                viewModel.clickZero();
                break;
            case R.id.bnOne:
                viewModel.clickOne();
                break;
            case R.id.bnTwo:
                viewModel.clickTwo();
                break;
            case R.id.bnThree:
                viewModel.clickThree();
                break;
            case R.id.bnFour:
                viewModel.clickFour();
                break;
            case R.id.bnFive:
                viewModel.clickFive();
                break;
            case R.id.bnSix:
                viewModel.clickSix();
                break;
            case R.id.bnSeven:
                viewModel.clickSeven();
                break;
            case R.id.bnEight:
                viewModel.clickEight();
                break;
            case R.id.bnNine:
                viewModel.clickNine();
                break;
            case R.id.bnSign:
                viewModel.clickMinus();
                break;
            case R.id.bnDot:
                viewModel.clickDot();
                break;
            case R.id.bnAdd:
                viewModel.clickAdd();
                break;
            case R.id.bnSub:
                viewModel.clickSub();
                break;
            case R.id.bnDiv:
                viewModel.clickDiv();
                break;
            case R.id.bnMul:
                viewModel.clickMul();
                break;
            case R.id.bnSquare:
                break;
            case R.id.bnResult:
                viewModel.clickEqual();
                break;
            case R.id.bnDel:
                viewModel.clickDelete();
                break;
            case R.id.bnCE:
                viewModel.clickCancel();
                break;

        }
    }

//    private void removeMinusSignOffDisplay() {
//        if (!TextUtils.isEmpty(tvDisplay.getText())) {
//            String string = tvDisplay.getText().toString();
//            tvDisplay.setText(string.substring(0, 1));
//        }
//    }
//
//    private void addMinusSignToDisplay() {
//        if (TextUtils.isEmpty(tvDisplay.getText())) {
//            tvDisplay.setText("-");
//        } else if (isOperatorsClicked) {
//            tvDisplay.setText("-");
//        } else {
//            tvDisplay.setText(String.format("%s", "-" + tvDisplay.getText().toString()));
//        }
//    }
//
//    private void handleSignButton(View view) {
//        if (view.getId() == R.id.bnSign) {
//            if (checkSignOfDisplay(tvDisplay)) {
//                removeMinusSignOffDisplay();
//            } else {
//                addMinusSignToDisplay();
//            }
//        }
//    }
//
//    private void setupTextHelperforOperands() {
//        if (isFirstOperand) {
//            tvSecond.setText(tvDisplay.getText().toString());
//        } else if (isSecondOperand) {
//            String text = operand1.toPlainString() + " " + operator.sign + " "
//                    + tvDisplay.getText().toString();
//            tvSecond.setText(text);
//        }
//    }
//
//    private void handleNumberButtons(View view) {
//        if (returnString(view.getId()) != null) {
//            if (isResultClicked) {
//                resetDisplay();
//                appendToDisplay(returnString(view.getId()), tvDisplay);
//                isResultClicked = false;
//                isOperatorsClicked = false;
//            } else if (isOperatorsClicked) {
//                if (tvDisplay.getText().toString().equals("-")) {
//                    appendToDisplay(returnString(view.getId()), tvDisplay);
//                } else {
//                    resetDisplay();
//                    appendToDisplay(returnString(view.getId()), tvDisplay);
//                    isOperatorsClicked = false;
//                }
//
//            } else {
//                appendToDisplay(returnString(view.getId()), tvDisplay);
//            }
//            setupTextHelperforOperands();
//        }
//    }
//
//    private void handleOperatorsButtons(View view) {
//        if (view.getId() == R.id.bnAdd || view.getId() == R.id.bnSub
//                || view.getId() == R.id.bnDiv || view.getId() == R.id.bnMul) {
//            operator = returnOperator(view);
//            try {
//                operand1 = getOperand(tvDisplay);
//                isOperatorsClicked = true;
//                isFirstOperand = false;
//                isSecondOperand = true;
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//            tvSecond.setText(String.format("%s", operand1.toPlainString() + " " + operator.sign));
//        }
//    }
//
//    private void handleEqualButton(View view) {
//        if (operand1 != null && view.getId() == R.id.bnResult) {
//            try {
//                operand2 = getOperand(tvDisplay);
//                isResultClicked = true;
//                isFirstOperand = true;
//                isSecondOperand = false;
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                return;
//            }
//
//            try {
//                calculateResult(operator, operand1,operand2, tvDisplay);
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//                tvDisplay.setText(getResources().getText(R.string.error));
//            }
//            String text = "" + operand1.toPlainString() + " " + operator.sign + " "
//                    + operand2.toPlainString() + " = ";
//            tvSecond.setText(String.format("%s", text));
//        }
//    }
//
//    private void handleDelButton(View view) {
//        if (view.getId() == R.id.bnCE) {
//            resetDisplay();
//            resetTextHelper();
//            operand1 = null;
//            operand2 = null;
//            isFirstOperand = true;
//            isSecondOperand = false;
//        }
//    }
//
//    private void handleCeButton(View view) {
//        if (view.getId() == R.id.bnDel){
//            int length = tvDisplay.getText().length();
//            if (length >= 1) {
//                String string = tvDisplay.getText().toString();
//                tvDisplay.setText(string.substring(0, 1));
//            }
//        }
//    }


}
