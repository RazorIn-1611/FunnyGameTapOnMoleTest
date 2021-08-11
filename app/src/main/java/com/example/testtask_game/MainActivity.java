package com.example.testtask_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startGame;
    private TextView recordTextView;
    static String RECORD_KEY = "recordValue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recordTextView = findViewById(R.id.RecordTextView);

        startGame = findViewById(R.id.startGameButton);
        startGame.setOnClickListener(this);
        //recordTextView.setText("--");


    }

    @Override
    protected void onResume() {
        SharedPreferences preferences = getSharedPreferences("recordValue", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = preferences.edit();
        int record = preferences.getInt("recordValue", 999);
        int lock = preferences.getInt("chronoLock", 0);
        Log.i("LOG_TAG", "onResume");
        if(record==999){
            recordTextView.setText("--");
            lock++;
            prefEditor.putInt("chronoLock", lock);
            prefEditor.apply();
            if(lock>3){
                finish();
            }
        }else{
            recordTextView.setText(record + "");
            Log.i("LOG_TAG", lock + " : lock");
            lock++;
            prefEditor.putInt("chronoLock", lock);
            prefEditor.apply();
            if(lock>3){
                finish();
            }
        }
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startGameButton:
                //TODO переход на активити игры
                startActivity(new Intent(this, GameActivity.class));
                //startActivity(new Intent(this, GameActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET));
                break;
        }
    }

}
