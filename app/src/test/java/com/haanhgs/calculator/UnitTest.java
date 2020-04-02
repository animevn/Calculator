package com.haanhgs.calculator;

import com.haanhgs.calculator.model.Repo;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UnitTest {

    private Repo repo;

    @Before
    public void setUp() {
        repo = new Repo();
    }

    @Test
    public void testAddInterger(){
        BigDecimal operand1 = repo.getBigDec("15");
        BigDecimal operand2 = repo.getBigDec("25");
        repo.getCalculator().setOperand1(operand1);
        repo.getCalculator().setOperand2(operand2);
        repo.add();
        assertThat(String.valueOf(repo.getCalculator().getOperand1()),  is(equalTo("15")));
        assertThat(String.valueOf(repo.getCalculator().getOperand2()),  is(equalTo("25")));
        assertThat(String.valueOf(repo.getCalculator().getResult()), is(equalTo("40")));
    }

    @Test
    public void testAddDecimal(){
        BigDecimal operand1 = repo.getBigDec("15.23456");
        BigDecimal operand2 = repo.getBigDec("25.36789345");
        repo.getCalculator().setOperand1(operand1);
        repo.getCalculator().setOperand2(operand2);
        repo.add();
        assertThat(String.valueOf(repo.getCalculator().getOperand1()), is(equalTo("15.23456")));
        assertThat(String.valueOf(repo.getCalculator().getOperand2()), is(equalTo("25.36789345")));
        assertThat(String.valueOf(repo.getCalculator().getResult()), is(equalTo("40.60245345")));
    }

    @Test
    public void testSqrtEasy(){
        assertThat(String.valueOf(repo.sqrt("25")), is(equalTo("5")));
    }

    @Test
    public void testSqrtHard1(){
        assertThat(String.valueOf(repo.sqrt("5")), is(equalTo("2.236067977")));
    }

    @Test
    public void testSqrtHard2(){
        assertThat(String.valueOf(repo.sqrt("55")), is(equalTo("7.416198487")));
    }






















}