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

public class Splash extends AppCompatActivity {

    private ImageView iv;
    private static final int PERMISSION_REQUEST_CODE=10;
    public  static final int PERMISSIONS_MULTIPLE_REQUEST = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = (ImageView) findViewById(R.id.image);

        Animation animation = AnimationUtils.loadAnimation(Splash.this, R.anim.movedown);
        iv.startAnimation(animation);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {

            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)+checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)+checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)+checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)== PackageManager.PERMISSION_GRANTED);

            else {

                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_MULTIPLE_REQUEST);
//               new Timer().schedule(new TimerTask(){
//                    public void run() {
//                        startActivity(new Intent(Splash.this, LoginActivity.class));
//                    }
//                }, 3000);
         }
        }

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex){}

        if(!gps_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(Splash.this);
            dialog.setTitle("Improve location accurancy?");
            dialog.setMessage("This app wants to change your device setting:");
            dialog.setPositiveButton("DENY", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
            dialog.setNegativeButton("ALLOW", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(myIntent, 1);
                }
            });
            dialog.show();
        }
//        else{
//            Intent i1=new Intent(Splash.this,LoginActivity.class);
//            startActivity(i1);
//        }

//            if (getIntent().getBooleanExtra("EXIT", false))
//        {
//            finish();
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && requestCode==RESULT_OK){
            Intent i1=new Intent(Splash.this,LoginActivity.class);
            startActivity(i1);
        }
        else {

        }
    }











}










