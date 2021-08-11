package com.example.testtask_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {

    private Button menu, startGame;
    private TextView scoreTextView, highScoreTextView;
    int currentScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_activity);
        Bundle args = getIntent().getExtras();
        currentScore = Integer.parseInt(args.get("Score").toString());
        Log.i("LOG_TAG", currentScore + " = score");

        menu = findViewById(R.id.menuButton);
        menu.setOnClickListener(this);
        startGame = findViewById(R.id.startGameSecondTime);
        startGame.setOnClickListener(this);

        scoreTextView = findViewById(R.id.actualResult);
        scoreTextView.setText(currentScore + "");
        highScoreTextView = findViewById(R.id.bestScore);

        SharedPreferences preferences = getSharedPreferences("recordValue", MODE_PRIVATE);
        int score = preferences.getInt("recordValue",999);
        if(currentScore > score || score == 999){
            SharedPreferences.Editor ed = preferences.edit();
            ed.putInt("recordValue", currentScore);
            ed.apply();
            highScoreTextView.setText(currentScore + "");
        }else{
            highScoreTextView.setText(score + "");
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuButton:
                onBackPressed();
                startActivity(new Intent(this, MainActivity.class).putExtra("Record_Score", currentScore));
                break;
            case R.id.startGameSecondTime:
                finish();
                startActivity(new Intent(this, GameActivity.class));
                break;
        }
    }
}
