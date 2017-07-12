package com.example.acer.vale_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = (ImageView) findViewById(R.id.image);

        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.movedown);
        iv.startAnimation(animation);

        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(Splash.this, LoginActivity.class));
            }
        }, 3000);

        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }

    }

}










