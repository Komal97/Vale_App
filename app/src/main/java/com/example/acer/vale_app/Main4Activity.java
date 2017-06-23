package com.example.acer.vale_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by ACER on 21-Jun-17.
 */

public class Main4Activity extends AppCompatActivity  {
    private Button btnDest;
    private GoogleMap mMap;
    //private MapView mMapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new BlankFragment());

        FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction trans=mgr.beginTransaction();
        trans.replace(R.id.fragment,new BlankFragment());
        trans.commit();
        btnDest= (Button) findViewById(R.id.btndest);

        btnDest.setText("REACHED DESTINATION");
        btnDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i1=new Intent(Main4Activity.this,Pick_UpActivity.class);
                startActivity(i1);


            }
        });
    }

}
