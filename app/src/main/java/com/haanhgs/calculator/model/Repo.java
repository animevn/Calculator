package com.haanhgs.calculator.model;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.haanhgs.calculator.R;
import java.math.BigDecimal;
import java.math.RoundingMode;
import androidx.lifecycle.MutableLiveData;

import static com.haanhgs.calculator.model.Operator.Add;
import static com.haanhgs.calculator.model.Operator.Div;
import static com.haanhgs.calculator.model.Operator.Mul;
import static com.haanhgs.calculator.model.Operator.Sub;

public class Repo {

    private Calculator calculator = new Calculator();
    private MutableLiveData<Calculator> liveData = new MutableLiveData<>();

    public Repo() {
        liveData.setValue(calculator);
    }

    public MutableLiveData<Calculator> getLiveData() {
        return liveData;
    }

    private boolean limitMain(){
        return calculator.getStringMain().length() < Constants.MAIN_LIMIT;
    }

    public void clickZero(){
        if (!TextUtils.isEmpty(calculator.getStringMain()) && limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "0");
            liveData.setValue(calculator);
        }
    }


    public void clickOne(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "1");
            liveData.setValue(calculator);
        }

    }

    public void clickTwo(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "2");
            liveData.setValue(calculator);
        }

    }

    public void clickThree(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "3");
            liveData.setValue(calculator);
        }
    }

    public void clickFour(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "4");
            liveData.setValue(calculator);
        }
    }

    public void clickFive(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "5");
            liveData.setValue(calculator);
        }

    }

    public void clickSix(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "6");
            liveData.setValue(calculator);
        }

    }

    public void clickSeven(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "7");
            liveData.setValue(calculator);
        }

    }

    public void clickEight(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "8");
            liveData.setValue(calculator);
        }

    }

    public void clickNine(){
        if (limitMain()){
            calculator.setStringMain(calculator.getStringMain() + "9");
            liveData.setValue(calculator);
        }

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

    public static boolean checkSignOfDisplay(TextView tvDisplay) {
        String firstChar = getFirstChar(tvDisplay.getText().toString());
        return firstChar.equals("-");
    }

    public static BigDecimal getOperand(TextView editText) {
        return new BigDecimal(editText.getText().toString());
    }

    public static void appendToDisplay(String string, TextView etDisplay) {
        etDisplay.append(string);
    }

    public static void calculateResult(Operator operator, BigDecimal operand1,
                                       BigDecimal operand2, TextView etDisplay) {
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