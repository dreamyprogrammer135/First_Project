package com.dreamyprogrammer.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int COUNT_STEPS = 10;
    private int number1, namber2, answerrr;
    private int steps = COUNT_STEPS;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonStart;
    private Button buttonDel;
    private TextView tvexample;
    private TextView etAnswer;
    private String exampleStr;
    private TextView tvCountLavel;
    private int exampleInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setOnClickListener();
        setView();
    }

    private void setView() {
        tvCountLavel.setText(COUNT_STEPS + " " + getResources().getString(R.string._10_steps));
    }

    private void setOnClickListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonDel.setOnClickListener(this);
        buttonStart.setOnClickListener(this);
    }

    private void findView() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button10);
        buttonStart = findViewById(R.id.buttonStart);
        buttonDel = findViewById(R.id.buttonDel);
        tvexample = findViewById(R.id.tvexample);
        etAnswer = findViewById(R.id.etAnswer);
        tvCountLavel = findViewById(R.id.tvCountLavel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                inputData(v,button1);
                return;
            case R.id.button2:
                inputData(v,button2);
                return;
            case R.id.button3:
                inputData(v,button3);
                return;
            case R.id.button4:
                inputData(v,button4);
                return;
            case R.id.button5:
                inputData(v,button5);
                return;
            case R.id.button6:
                inputData(v,button6);
                return;
            case R.id.button7:
                inputData(v,button7);
                return;
            case R.id.button8:
                inputData(v,button8);
                return;
            case R.id.button9:
                inputData(v,button9);
                return;
            case R.id.button10:
                inputData(v,button0);
                return;
            case R.id.buttonStart:
                 startGames(v);
                return;
            case R.id.buttonDel:
                etAnswer.setText("");
                return;
            default:
                return;
        }
    }

    //Пока оставлю тут ну вообще это в отдельный класс надо поместить
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private void startGames(View v) {
            if (steps == 10) {
                getNewExample();
                buttonStart.setText(R.string.answer);
                tvCountLavel.setText(steps + " " + getResources().getString(R.string._10_steps));
                steps -= 1;
            }
        if ((! etAnswer.getText().toString().equals(""))) {
            if (steps < 10 & steps > 0) {
                checkResponse();
                getNewExample();
                tvCountLavel.setText(steps + " " + getResources().getString(R.string._10_steps));
                etAnswer.setText("");
                steps -= 1;
            } else {
                Snackbar.make(v, R.string.gameOver, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                buttonStart.setText(R.string.start);
                tvCountLavel.setText(COUNT_STEPS + " " + getResources().getString(R.string._10_steps));
                etAnswer.setText("");
                tvexample.setText("");
                steps = COUNT_STEPS;

            }
        } else if (steps !=10){
            Snackbar.make(v, R.string.еnter_number, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }

    private void checkResponse() {
        answerrr = Integer.parseInt(etAnswer.getText().toString());
        if((number1+namber2) == answerrr){
            Toast.makeText(this, R.string.correct_answer,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.wrong,
                    Toast.LENGTH_SHORT).show();
            steps +=1;
        }
    }

    private void getNewExample(){
        //Будут меняться от уровня сложности
        final int min = 0; // Минимальное число для диапазона
        final int max = 10; // Максимальное число для диапазона
        //Получаем число которое надо будет рачитать.
        final int rnd1 = rnd(min, max);
        final int rnd2 = rnd(min, 10-rnd1);
        tvexample.setText(rnd1 + " + " + rnd2 + " = ");
        number1 = rnd1;
        namber2 = rnd2;
    }

    private void inputData(View v, Button button){
        if (etAnswer.getText().toString().length() < 3 ) {
            exampleStr = etAnswer.getText().toString();
            etAnswer.setText(exampleStr + button.getText().toString());
            exampleInt = Integer.parseInt(exampleStr + button.getText().toString());
        } else {
            Snackbar.make(v, R.string.countAnswer, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}