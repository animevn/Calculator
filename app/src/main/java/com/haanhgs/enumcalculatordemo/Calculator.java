package com.haanhgs.enumcalculatordemo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    public enum Operator{
        Add, Sub, Div, Mul;
    }

    public BigDecimal add(BigDecimal operand1, BigDecimal operand2){
        return operand1.add(operand2);
    }

    public  BigDecimal sub(BigDecimal operand1, BigDecimal operand2){
        return operand1.subtract(operand2);
    }

    public BigDecimal mul(BigDecimal operand1, BigDecimal operand2){
        return operand1.multiply(operand2);
    }

    public BigDecimal div(BigDecimal operand1, BigDecimal operand2){
        if (operand2.equals(new BigDecimal("0"))){
            throw new IllegalArgumentException("Can not divide by zero");
        }
        return operand1.divide(operand2, 9, RoundingMode.HALF_UP);
    }








}
