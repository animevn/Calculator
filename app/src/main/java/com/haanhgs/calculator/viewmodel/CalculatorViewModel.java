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

    public void clickDot(){
        repo.clickDot();
    }

    public void clickMinus(){
        repo.clickMinus();
    }

    public void clickZero(){
        repo.clickZero();
    }

    public void clickAdd(){
        repo.clickAdd();
    }

    public void clickSub(){
        repo.clickSub();
    }

    public void clickDiv(){
        repo.clickDiv();
    }

    public void clickMul(){
        repo.clickMul();
    }

    public void clickDelete(){
        repo.clickDelete();
    }

    public void clickCancel(){
        repo.clickCancel();
    }

    public void clickEqual(){
        repo.clickEqual();
    }

}
