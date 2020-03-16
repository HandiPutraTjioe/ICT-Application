package com.english.englishproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class PlanetShooterGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_shooter_game);
    }

    public void startGame(View v){
        Log.i("ImageButton","clicked");
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }
}
