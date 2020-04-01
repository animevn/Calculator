package com.haanhgs.calculator.viewmodel;

import com.haanhgs.calculator.model.Calculator;
import com.haanhgs.calculator.model.Repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private Repo repo = new Repo();

    public LiveData<Calculator> getData(){
        return repo.getLiveData();
    }

    public void clickOne(){
        repo.clickOne();
    }

    public void clickTwo(){
        repo.clickTwo();
    }

    public void clickThree(){
        repo.clickThree();
    }

    public void clickFour(){
        repo.clickFour();
    }

    public void clickFive(){
        repo.clickFive();
    }

    public void clickSix(){
        repo.clickSix();
    }

    public void clickSeven(){
        repo.clickSeven();
    }

    public void clickEight(){
        repo.clickEight();
    }

    public void clickNine(){
        repo.clickNine();
    }

    public void clickZero(){
        repo.clickZero();
    }

}
