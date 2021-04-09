package com.example.studentsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.studentsurvey.databinding.ActivityWelcomeBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding activityWelcomeBinding;
    boolean isSkip=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWelcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(activityWelcomeBinding.getRoot());

        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);


        activityWelcomeBinding.skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSkip=true;
                skipPage(intent);


            }
        });
        activityWelcomeBinding.aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,AboutActivity.class));
            }
        });



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(!isSkip){
//                    skipPage(intent);
//                    Log.d(getString(R.string.DEBUGING_TAG),"welcome runnable");
//                }
//
//
//            }
//        }, 6000);
    }
    private void skipPage(Intent intent){
        startActivity(intent);
        finish();
    }
}