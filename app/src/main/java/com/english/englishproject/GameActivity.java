package com.english.englishproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    Button btnGame1, btnGame2;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnGame1 = findViewById(R.id.btn_game1);
        btnGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), PlanetShooterGameActivity.class);
                startActivity(intent);
            }
        });

        btnGame2 = findViewById(R.id.btn_game2);
        btnGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg1) {
                Intent intent = new Intent(arg1.getContext(), WordMattersActivity.class);
                startActivity(intent);
            }
        });

        imgBack = findViewById(R.id.back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
