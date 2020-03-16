package com.english.englishproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView txtScore, txtPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int score = getIntent().getExtras().getInt("score");
        SharedPreferences pref = getSharedPreferences("MyPref", 0);

        int scoreSP = pref.getInt("scoreSP", 0);
        SharedPreferences.Editor editor = pref.edit();

        if (score > scoreSP) {
            scoreSP = score;
            editor.putInt("scoreSP", scoreSP);
            editor.commit();
        }

        txtScore = findViewById(R.id.tvScore);
        txtPersonal = findViewById(R.id.tvPersonalBest);
        txtScore.setText("" + score);
        txtPersonal.setText("" + scoreSP);
    }

    public void restart(View view){
        Intent intent = new Intent(GameOverActivity.this, PlanetShooterGameActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
