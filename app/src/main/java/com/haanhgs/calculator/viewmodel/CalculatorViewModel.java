package com.haanhgs.calculator.viewmodel;

import android.app.Application;

import com.haanhgs.calculator.model.Calculator;
import com.haanhgs.calculator.model.Repo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CalculatorViewModel extends AndroidViewModel {

    private Repo repo = new Repo();

    public CalculatorViewModel(@NonNull Application application) {
        super(application);
        repo.setLiveData();
    }

    public LiveData<Calculator> getData(){
        return repo.getLiveData();
    }

    public void clickOne(){
        repo.clickNumber("1");
    }

    public void clickTwo(){
        repo.clickNumber("2");
    }

    public void clickThree(){
        repo.clickNumber("3");
    }

    public void clickFour(){
        repo.clickNumber("4");
    }

    public void clickFive(){
        repo.clickNumber("5");
    }

    public void clickSix(){
        repo.clickNumber("6");
    }

    public void clickSeven(){
        repo.clickNumber("7");
    }

    public void clickEight(){
        repo.clickNumber("8");
    }

    public void clickNine(){
        repo.clickNumber("9");
    }

    public void clickZero(){
        repo.clickZero();
    }

    public void clickDot(){
        repo.clickDot();
    }

    public void clickMinus(){
        repo.clickMinus();
    }

    public void clickSqrt(){
        repo.clickSqrt();
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
