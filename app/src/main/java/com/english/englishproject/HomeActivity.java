package com.english.englishproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.english.englishproject.activities.MyBooksActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser;

    Button btnCourse, btnProfile, btnBooks, btnAboutUs, btnGames;
    TextView txtMyName;
    ImageView imgLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        currentUser = firebaseAuth.getCurrentUser();

        txtMyName = findViewById(R.id.txt_myname);
        txtMyName.setText(user.getEmail());

        btnCourse = findViewById(R.id.btn_course);
        btnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), CourseActivity.class);
                startActivity(intent);
            }
        });

        btnProfile = findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg1) {
                Intent intent = new Intent(arg1.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnGames = findViewById(R.id.btn_games);
        btnGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btnBooks = findViewById(R.id.btn_books);
        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), MyBooksActivity.class);
                startActivity(intent);
            }
        });

        btnAboutUs = findViewById(R.id.btn_about);
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(arg0.getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        imgLogout = findViewById(R.id.logout);
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
                moveTaskToBack(true);
            }
        });

        updateNavHeader();
    }

    public void updateNavHeader(){
        ImageView navUserphoto = findViewById(R.id.profileimg);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserphoto);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit??")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        moveTaskToBack(true);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}
