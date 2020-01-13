package com.haanhgs.enumcalculatordemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.haanhgs.enumcalculatordemo.Operator.Add;
import static com.haanhgs.enumcalculatordemo.Operator.Div;
import static com.haanhgs.enumcalculatordemo.Operator.Mul;
import static com.haanhgs.enumcalculatordemo.Operator.Sub;

public class Calculator {

    public static void setPortraitMode(Activity activity){
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static BigDecimal add(BigDecimal operand1, BigDecimal operand2){
        return operand1.add(operand2);
    }

    public static BigDecimal sub(BigDecimal operand1, BigDecimal operand2){
        return operand1.subtract(operand2);
    }

    public static BigDecimal mul(BigDecimal operand1, BigDecimal operand2){
        return operand1.multiply(operand2);
    }

    public static BigDecimal div(BigDecimal operand1, BigDecimal operand2){
        if (operand2.equals(new BigDecimal("0"))){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return operand1.divide(operand2, 9, RoundingMode.HALF_UP);
    }

    public static String returnString(int bnId){
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

    private static String getFirstChar(String string) {
        String result = "";
        if (!TextUtils.isEmpty(string)) {
            result = string.substring(0, 1);
        }
        return result;
    }

    public static boolean checkSignOfDisplay(EditText etDisplay) {
        String firstChar = getFirstChar(etDisplay.getText().toString());
        return firstChar.equals("-");
    }

    public static BigDecimal getOperand(EditText editText) {
        return new BigDecimal(editText.getText().toString());
    }

    public static void appendToDisplay(String string, EditText etDisplay) {
        etDisplay.append(string);
    }

    public static void calculateResult(Operator operator, BigDecimal operand1,
                                       BigDecimal operand2, EditText etDisplay) {
        String result;
        switch (operator) {
            case Add:
                result = add(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            case Sub:
                result = sub(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            case Div:
                result = div(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            case Mul:
                result = mul(operand1, operand2).stripTrailingZeros().toPlainString();
                break;
            default:
                result = "Computational error";
                break;
        }
        etDisplay.setText(String.format("%s", result));
    }

    public static Operator returnOperator(View view) {
        if (view.getId() == R.id.bnAdd) return Add;
        if (view.getId() == R.id.bnSub) return Sub;
        if (view.getId() == R.id.bnDiv) return Div;
        if (view.getId() == R.id.bnMul) return Mul;
        return null;
    }
}
