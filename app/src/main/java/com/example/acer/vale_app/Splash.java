package com.example.acer.vale_app;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
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
    private boolean flag=false;
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = (ImageView) findViewById(R.id.image);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.movedown);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) + checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) + checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) + checkSelfPermission(Manifest.permission.INTERNET) + checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (!gps_enabled) {
                    // notify user
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Splash.this);
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
                            flag=true;
                            //startActivityForResult(myIntent, 1);


                        }
                    });
                    dialog.show();
                } else {
                    iv.startAnimation(animation);
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            startActivity(new Intent(Splash.this, LoginActivity.class));
                        }
                    }, 3000);

                }

            } else {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_MULTIPLE_REQUEST);
                if (!gps_enabled) {
                    // notify user
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Splash.this);
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
                            flag=true;
                            //startActivityForResult(myIntent, 1);


                        }
                    });
                    dialog.show();
                } else {
                    iv.startAnimation(animation);
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            startActivity(new Intent(Splash.this, LoginActivity.class));
                        }
                    }, 3000);

                }

            }
        }

    }



    @Override
    protected void onResume() {
        super.onResume();
        if(flag){
            Intent i1 = new Intent(Splash.this, LoginActivity.class);
            startActivity(i1);
            finish();
        }
    }
}






















