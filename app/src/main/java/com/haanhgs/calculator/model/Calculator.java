package com.haanhgs.calculator.model;

import java.math.BigDecimal;

public class Calculator {

    private String stringMain = "";
    private String stringSecond = "";
    private BigDecimal operand1 = null;
    private Operator operator = null;
    private BigDecimal operand2 = null;
    private BigDecimal result = null;
    private State state = State.Op1;
    private boolean calculation = false;

    public String getStringMain() {
        return stringMain;
    }

    public void setStringMain(String stringMain) {
        this.stringMain = stringMain;
    }

    public String getStringSecond() {
        return stringSecond;
    }

    public void setStringSecond(String stringSecond) {
        this.stringSecond = stringSecond;
    }

    public BigDecimal getOperand1() {
        return operand1;
    }

    public void setOperand1(BigDecimal operand1) {
        this.operand1 = operand1;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public BigDecimal getOperand2() {
        return operand2;
    }

    public void setOperand2(BigDecimal operand2) {
        this.operand2 = operand2;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public boolean isCalculation() {
        return calculation;
    }

    public void setCalculation(boolean calculation) {
        this.calculation = calculation;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
