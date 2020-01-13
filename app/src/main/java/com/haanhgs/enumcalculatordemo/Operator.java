package com.haanhgs.enumcalculatordemo;

public enum Operator {
    Add("+"), Sub("-"), Mul("x"), Div(":");

    public final String sign;

    Operator(String sign) {
        this.sign = sign;
    }
}
