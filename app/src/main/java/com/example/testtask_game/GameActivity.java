package com.example.testtask_game;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView timer, result;
    private ImageButton lunka1, lunka2,lunka3,lunka4,lunka5,lunka6,lunka7,lunka8,lunka9;
    private boolean isClicked = false;
    private int score = 0, exLunkaActive = 0, activeLunka = 0;
    private ArrayList<LunkaObject> lunkaList;
    private CountDownTimer ticker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);


        SharedPreferences preferences = getSharedPreferences("recordValue", MODE_PRIVATE);
        int surprize = preferences.getInt("chronoLock", 555);
        if(surprize==555){
            finish();
        }
        lunkaList = new ArrayList<>();
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka9)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka8)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka7)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka6)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka5)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka4)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka3)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka2)));
        lunkaList.add(new LunkaObject((ImageButton)findViewById(R.id.lunka1)));

        timer = findViewById(R.id.TimeValue);
        result = findViewById(R.id.scoreValue);

        lunkaList.get(0).getLunka().setOnClickListener(this);
        lunkaList.get(1).getLunka().setOnClickListener(this);
        lunkaList.get(2).getLunka().setOnClickListener(this);
        lunkaList.get(3).getLunka().setOnClickListener(this);
        lunkaList.get(4).getLunka().setOnClickListener(this);
        lunkaList.get(5).getLunka().setOnClickListener(this);
        lunkaList.get(6).getLunka().setOnClickListener(this);
        lunkaList.get(7).getLunka().setOnClickListener(this);
        lunkaList.get(8).getLunka().setOnClickListener(this);


        //в 1 секунду (задаем значения в миллисекундах):
        ticker = new CountDownTimer(31000, 500) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 + "");
                result.setText(score + "");
                isClicked = false;
                activeLunka = randomizeLunkaSpawnKrot();
                if(activeLunka==exLunkaActive) activeLunka = randomizeLunkaSpawnKrot();
                if(activeLunka==exLunkaActive) activeLunka = randomizeLunkaSpawnKrot();
                lunkaList.get(activeLunka).getLunka().setImageResource(R.drawable.lunka_active);
                lunkaList.get(exLunkaActive).getLunka().setImageResource(R.drawable.lunka);
                exLunkaActive = activeLunka;

                //Log.i("LOG_TAG", activeLunka + "- active linka");

            }
            public void onFinish() {
                endGame();
            }
        };
        ticker.start();
    }

    @Override
    public void onBackPressed(){
        finish();
        ticker.cancel();
        super.onBackPressed();
    }

    private void endGame() {
        this.finish();
        startActivity(new Intent(getApplicationContext(), GameOverActivity.class).putExtra("Score", score));
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==lunkaList.get(activeLunka).getLunka().getId()){
            if(!isClicked) score++;
            isClicked = true;
           // Log.i("LOG_TAG", "+++++++");
        }
    }
    /*
    Функция определяет рандомную лунку, где появится крот
    Лучше названия и быть не может
     */
    private int randomizeLunkaSpawnKrot(){
        Random randomNumber = new Random();
        int randomNumberOfLunka = randomNumber.nextInt(9);

        return randomNumberOfLunka;
    }
}
