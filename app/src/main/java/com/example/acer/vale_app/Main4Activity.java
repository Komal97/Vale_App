package com.example.acer.vale_app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import android.text.TextWatcher;
import android.text.Editable;
/**
 * Created by ACER on 21-Jun-17.
 */

public class Main4Activity extends AppCompatActivity  {
    private Button btnDest,btnpick;
    private GoogleMap mMap;
    Dialog otpdialog;
    EditText etotp;
    //private MapView mMapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new BlankFragment());
        otpdialog=new Dialog(Main4Activity.this);

        otpdialog.setTitle("Otp Authentication");

        otpdialog.setContentView(R.layout.otp_dialog);
        btnpick= (Button) otpdialog.findViewById(R.id.btnpickup2);
        etotp=(EditText)otpdialog.findViewById(R.id.et1);

        btnpick.setEnabled(false);

        TextWatcher tw=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    btnpick.setEnabled(false);
                }
                else
                    btnpick.setEnabled(true);
            }

        };
        etotp.addTextChangedListener(tw);

        btnpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpdialog.cancel();
                Intent i1 = new Intent(Main4Activity.this, MapsActivity.class);
                startActivity(i1);

            }
        });
        FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction trans=mgr.beginTransaction();
        trans.replace(R.id.fragment,new BlankFragment());
        trans.commit();
        btnDest= (Button) findViewById(R.id.btndest);

        btnDest.setText("REACHED DESTINATION");
        btnDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // Intent i1=new Intent(Main4Activity.this,MapsActivity.class);
                //startActivity(i1);
                otpdialog.show();


            }
        });
    }

}
