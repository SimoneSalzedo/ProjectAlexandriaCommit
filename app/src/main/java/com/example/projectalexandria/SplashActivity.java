package com.example.projectalexandria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import LibraryServices.FileUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        };

        Runnable fadingEffect = new Runnable() {
            @Override
            public void run() {
                fade();
            }
        };

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(fadingEffect);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this , HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    public void fade(){
    ImageView imageView = findViewById(R.id.farologo);
    Animation animationfade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
    imageView.startAnimation(animationfade);
    //Committ
    }



}