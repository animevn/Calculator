package com.haanhgs.calculator.view;

import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.haanhgs.calculator.R;
import com.haanhgs.calculator.viewmodel.CalculatorViewModel;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvDisplay)
    TextView tvDisplay;
    @BindView(R.id.tvSecond)
    TextView tvSecond;
    @BindView(R.id.bnAdd)
    Button bnAdd;
    @BindView(R.id.bnThree)
    Button bnThree;
    @BindView(R.id.bnFour)
    Button bnFour;
    @BindView(R.id.bnSeven)
    Button bnSeven;
    @BindView(R.id.bnSign)
    Button bnSign;
    @BindView(R.id.bnZero)
    Button bnZero;
    @BindView(R.id.bnDot)
    Button bnDot;
    @BindView(R.id.bnNine)
    Button bnNine;
    @BindView(R.id.bnEight)
    Button bnEight;
    @BindView(R.id.bnResult)
    Button bnResult;
    @BindView(R.id.bnFive)
    Button bnFive;
    @BindView(R.id.bnSix)
    Button bnSix;
    @BindView(R.id.bnDel)
    Button bnDel;
    @BindView(R.id.bnCE)
    Button bnCE;
    @BindView(R.id.bnOne)
    Button bnOne;
    @BindView(R.id.bnTwo)
    Button bnTwo;
    @BindView(R.id.bnSub)
    Button bnSub;
    @BindView(R.id.bnDiv)
    Button bnDiv;
    @BindView(R.id.bnMul)
    Button bnMul;
    @BindView(R.id.bnSquare)
    Button bnSquare;

    private CalculatorViewModel viewModel;

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        viewModel.getData().observe(this, calculator -> {
            tvDisplay.setText(calculator.getStringMain());
            tvSecond.setText(calculator.getStringSecond());
        });
    }

    private void hideActionBar() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionBar();
        initViewModel();
    }

    @OnClick({R.id.bnAdd, R.id.bnThree, R.id.bnFour, R.id.bnSeven, R.id.bnSign, R.id.bnZero,
            R.id.bnDot, R.id.bnNine, R.id.bnEight, R.id.bnResult, R.id.bnFive,
            R.id.bnSix, R.id.bnDel, R.id.bnCE, R.id.bnOne, R.id.bnTwo, R.id.bnSub, R.id.bnDiv,
            R.id.bnMul, R.id.bnSquare})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnZero:
                viewModel.clickZero();
                break;
            case R.id.bnOne:
                viewModel.clickOne();
                break;
            case R.id.bnTwo:
                viewModel.clickTwo();
                break;
            case R.id.bnThree:
                viewModel.clickThree();
                break;
            case R.id.bnFour:
                viewModel.clickFour();
                break;
            case R.id.bnFive:
                viewModel.clickFive();
                break;
            case R.id.bnSix:
                viewModel.clickSix();
                break;
            case R.id.bnSeven:
                viewModel.clickSeven();
                break;
            case R.id.bnEight:
                viewModel.clickEight();
                break;
            case R.id.bnNine:
                viewModel.clickNine();
                break;
            case R.id.bnSign:
                viewModel.clickMinus();
                break;
            case R.id.bnDot:
                viewModel.clickDot();
                break;
            case R.id.bnAdd:
                viewModel.clickAdd();
                break;
            case R.id.bnSub:
                viewModel.clickSub();
                break;
            case R.id.bnDiv:
                viewModel.clickDiv();
                break;
            case R.id.bnMul:
                viewModel.clickMul();
                break;
            case R.id.bnSquare:
                viewModel.clickSqrt();
                break;
            case R.id.bnResult:
                viewModel.clickEqual();
                break;
            case R.id.bnDel:
                viewModel.clickDelete();
                break;
            case R.id.bnCE:
                viewModel.clickCancel();
                break;

        }
    }
}
