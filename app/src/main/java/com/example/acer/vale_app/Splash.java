package com.example.acer.vale_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private ImageView iv;
    private AlertDialog.Builder dialog;
    private boolean flag = false;
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = (ImageView) findViewById(R.id.image);

        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.movedown);
        iv.startAnimation(animation);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;


        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }


        if (!gps_enabled) {
                // notify user



                    dialog = new AlertDialog.Builder(Splash.this);
                    dialog.setTitle("Improve location accurancy?");
                    dialog.setMessage("This app wants to change your device setting:");
                    dialog.setNegativeButton("DENY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub
                            finish();
                        }
                    });
                    dialog.setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);

                            flag = true;

                            //startActivityForResult(myIntent, 1);


                        }
                    });

            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    dialog.show();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            }

            else {
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        startActivity(new Intent(Splash.this, LoginActivity.class));
                    }
                }, 3000);

            }
        }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag) {
            Intent i1 = new Intent(Splash.this, LoginActivity.class);
            i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i1);
            finish();
        }
    }
}