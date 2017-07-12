package com.example.acer.vale_app;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeliveredActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button btndeliver;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_reach);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FrameLayoutFragment());

        FragmentManager mgr=getSupportFragmentManager();
        FragmentTransaction trans=mgr.beginTransaction();
        trans.replace(R.id.fragment,new FrameLayoutFragment());
        trans.commit();

        btndeliver= (Button) findViewById(R.id.btndest);

        btndeliver.setText("DELIVERED SUCCESSFULLY");
        btndeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(DeliveredActivity.this,TabbedActivity.class);
                startActivity(i1);


            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(28.63320831, 77.22294813);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
