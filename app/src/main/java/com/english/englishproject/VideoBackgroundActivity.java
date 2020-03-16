package com.english.englishproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class VideoBackgroundActivity extends AppCompatActivity {

    private VideoView video;
    MediaPlayer mediaPlayer;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_background);

//        video = findViewById(R.id.videoView);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.english);
//
//        video.setVideoURI(uri);
//        video.start();
//
//        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mediaPlayer = mp;
//                mediaPlayer.setLooping(true);
//                if (current != 0){
//                    mediaPlayer.seekTo(current);
//                    mediaPlayer.start();
//                }
//            }
//        });
    }

//    protected void onPause(){
//        super.onPause();
//        current = mediaPlayer.getCurrentPosition();
//        video.pause();
//    }
//
//    protected void onResume(){
//        super.onResume();
//        video.start();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mediaPlayer.release();
//        mediaPlayer = null;
//    }
}
