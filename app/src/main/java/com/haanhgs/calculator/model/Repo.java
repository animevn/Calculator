package com.haanhgs.calculator.model;

import android.text.TextUtils;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
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

    private void setStateForNumbersAfterOperator(){
        if (calculator.getState() == State.Operator) calculator.setState(State.Op2);
    }

    public void clickDelete(){
        calculator = new Calculator();
        liveData.setValue(calculator);
    }

    public void clickCancel(){
        String stringMain = calculator.getStringMain();
        String stringSecond = calculator.getStringSecond();

        if (calculator.getState() == State.Equal){
            clickDelete();
        }else if (calculator.getState() == State.Operator){
            calculator.setState(State.Op1);
            calculator.setStringSecond(stringSecond.substring(0, stringSecond.length() - 3));
            calculator.setStringMain(String.valueOf(calculator.getOperand1()));
        }else {
            if (!TextUtils.isEmpty(stringMain)){
                calculator.setStringMain(stringMain.substring(0, stringMain.length() - 1));
                calculator.setStringSecond(stringSecond.substring(0, stringSecond.length() - 1));
            }
        }
        liveData.setValue(calculator);
    }

    public void clickZero(){
        if (calculator.getState() == State.Equal) clickDelete();
        if (limitMain() && !calculator.getStringMain().equals("0")){
            calculator.setStringMain(calculator.getStringMain() + "0");
            calculator.setStringSecond(calculator.getStringSecond() + "0");
            setStateForNumbersAfterOperator();
            liveData.setValue(calculator);
        }
    }

    private void clickNumber(String number){
        if (calculator.getState() == State.Equal) clickDelete();
        if (limitMain()){
            if (calculator.getStringMain().equals("0")){
                calculator.setStringMain(number);
                calculator.setStringSecond(number);
            }else {
                calculator.setStringMain(calculator.getStringMain() + number);
                calculator.setStringSecond(calculator.getStringSecond() + number);
            }
            if (calculator.getState() == State.Operator) calculator.setState(State.Op2);
            liveData.setValue(calculator);
        }
    }


    public void clickOne(){
        clickNumber("1");

    }

    public void clickTwo(){
        clickNumber("2");
    }

    public void clickThree(){
        clickNumber("3");
    }

    public void clickFour(){
        clickNumber("4");
    }

    public void clickFive(){
        clickNumber("5");
    }

    public void clickSix(){
        clickNumber("6");
    }

    public void clickSeven(){
        clickNumber("7");
    }

    public void clickEight(){
        clickNumber("8");
    }

    public void clickNine(){
        clickNumber("9");
    }

    public void clickDot(){
        String stringMain = calculator.getStringMain();

        if (calculator.getState() == State.Op1 || calculator.getState() == State.Op2){
            if (TextUtils.isEmpty(stringMain)){
                calculator.setStringMain("0.");
                calculator.setStringSecond(calculator.getStringSecond() + " 0.");
            }else if (stringMain.equals("-")){
                calculator.setStringMain(calculator.getStringMain() + "0.");
                calculator.setStringSecond(calculator.getStringSecond() + "0.");
            }else if (!stringMain.contains(".")){
                calculator.setStringMain(calculator.getStringMain() + ".");
                calculator.setStringSecond(calculator.getStringSecond() + ".");
            }

        }else if (calculator.getState() == State.Operator){
            calculator.setStringMain("0.");
            calculator.setStringSecond(calculator.getStringSecond() + " 0.");

        }else if (calculator.getState() == State.Equal){
            calculator.setStringMain("0.");
            calculator.setStringSecond("0.");
            calculator.setState(State.Op1);
        }

        liveData.setValue(calculator);
    }

    public void clickMinus(){
        String stringMain = calculator.getStringMain();
        String stringSecond = calculator.getStringSecond();

        if (calculator.getState() == State.Equal){
            calculator.setStringMain("-");
            calculator.setStringSecond("-");
            calculator.setState(State.Op1);
        }else if (calculator.getState() == State.Op1 || calculator.getState() == State.Op2){
            if (TextUtils.isEmpty(stringMain)){
                calculator.setStringMain("-");
                calculator.setStringSecond(calculator.getStringSecond() + "-");
            }else if (stringMain.equals("-")){
                calculator.setStringMain("");
                calculator.setStringSecond(stringSecond.substring(0, stringSecond.length() - 1));
            }
        }else if (calculator.getState() == State.Operator){
            calculator.setStringMain("-");
            calculator.setStringSecond(calculator.getStringSecond() + "-");
            calculator.setState(State.Op2);
        }
        liveData.setValue(calculator);
    }

    public void clickSqrt(){
        String strMain = calculator.getStringMain();
        if (!TextUtils.isEmpty(strMain) && getBigDec(strMain).compareTo(BigDecimal.ZERO) >= 0){
            double sqrt = Math.sqrt(Double.valueOf(strMain));
            BigDecimal sqrtBigDec = BigDecimal.valueOf(sqrt).round(MathContext.DECIMAL32);
            if (calculator.getState() == State.Equal){
                calculator.setOperand1(sqrtBigDec);
                calculator.setStringMain(String.valueOf(sqrtBigDec));
                calculator.setStringSecond(String.valueOf(sqrtBigDec));
                calculator.setState(State.Op1);
            }else if (calculator.getState() == State.Op1){
                calculator.setOperand1(sqrtBigDec);
                calculator.setStringMain(String.valueOf(sqrtBigDec));
                String secondStr = calculator.getStringSecond();
                String subString = secondStr.substring(0, secondStr.length() - strMain.length());
                calculator.setStringSecond(subString + String.valueOf(sqrtBigDec));
            }else if (calculator.getState() == State.Op2){
                calculator.setOperand2(sqrtBigDec);
                calculator.setStringMain(String.valueOf(sqrtBigDec));
                String secondStr = calculator.getStringSecond();
                String subString = secondStr.substring(0, secondStr.length() - strMain.length());
                calculator.setStringSecond(subString + String.valueOf(sqrtBigDec));
            }
        }
        liveData.setValue(calculator);
    }

    private BigDecimal getBigDec(String string) {
        return new BigDecimal(string);
    }

    private void add(){
        BigDecimal bigDecimal = calculator.getOperand1().add(calculator.getOperand2());
        String resultString = bigDecimal.stripTrailingZeros().toPlainString();
        calculator.setResult(new BigDecimal(resultString));
    }

    private void sub(){
        BigDecimal bigDecimal = calculator.getOperand1().subtract(calculator.getOperand2());
        String resultString = bigDecimal.stripTrailingZeros().toPlainString();
        calculator.setResult(new BigDecimal(resultString));
    }

    private void mul(){
        BigDecimal bigDecimal = calculator.getOperand1().multiply(calculator.getOperand2());
        String resultString = bigDecimal.stripTrailingZeros().toPlainString();
        calculator.setResult(new BigDecimal(resultString));
    }

    private void div(){
        if (!String.valueOf(calculator.getOperand2()).equals("0")){
            BigDecimal bigDecimal = calculator.getOperand1()
                    .divide(calculator.getOperand2(), 9, RoundingMode.HALF_UP);
            String resultString = bigDecimal.stripTrailingZeros().toPlainString();
            calculator.setResult(new BigDecimal(resultString));
        }else {
            calculator.setResult(null);
        }
    }

    private void doOperator(Operator operator){
        switch (operator){
            case Add:
                add();
                break;
            case Sub:
                sub();
                break;
            case Div:
                div();
                break;
            case Mul:
                mul();
                break;
        }
    }

    private String getOpSign(Operator operator){
        if (operator == Add) return  " + ";
        if (operator == Sub) return  " - ";
        if (operator == Div) return  " : ";
        if (operator == Mul) return  " x ";
        return "";
    }

    private void clickOperator(Operator operator){
        String string = calculator.getStringMain();
        if (!TextUtils.isEmpty(string)){
            if (calculator.getState() == State.Op1){
                calculator.setOperand1(getBigDec(string));
                calculator.setStringSecond(calculator.getStringSecond() + getOpSign(operator));
                calculator.setStringMain("");
                calculator.setOperator(operator);
                calculator.setState(State.Operator);
            }else if (calculator.getState() == State.Operator){
                calculator.setOperator(operator);
                calculator.setStringSecond(calculator.getStringSecond() + getOpSign(operator));
            }else if (calculator.getState() == State.Op2){
                calculator.setOperand2(getBigDec(string));
                doOperator(calculator.getOperator());
                calculator.setStringSecond(calculator.getStringSecond() + getOpSign(operator));
                calculator.setStringMain("");
                calculator.setOperand1(calculator.getResult());
                calculator.setOperator(operator);
                calculator.setState(State.Operator);
            }else if (calculator.getState() == State.Equal){
                calculator.setOperand1(calculator.getResult());
                calculator.setStringSecond(String.valueOf(calculator.getResult())
                        + getOpSign(operator));
                calculator.setStringMain("");
                calculator.setOperator(operator);
                calculator.setState(State.Operator);
            }
            liveData.setValue(calculator);
        }
    }

    public void clickAdd(){
       clickOperator(Add);
    }

    public void clickSub(){
        clickOperator(Sub);
    }

    public void clickDiv(){
        clickOperator(Div);
    }

    public void clickMul(){
        clickOperator(Mul);
    }

    public void clickEqual(){
        String string = calculator.getStringMain();
        if (!TextUtils.isEmpty(string)){
            if (calculator.getState() == State.Op2){
                calculator.setOperand2(getBigDec(string));
                doOperator(calculator.getOperator());
                if (calculator.getResult() != null){
                    calculator.setStringMain(String.valueOf(calculator.getResult()));
                    calculator.setStringSecond(calculator.getStringSecond() + " = "
                            + String.valueOf(calculator.getResult()));
                    calculator.setState(State.Equal);
                    liveData.setValue(calculator);
                }
            }
        }
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

}
