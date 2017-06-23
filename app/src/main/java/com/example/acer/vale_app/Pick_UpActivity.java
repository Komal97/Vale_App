package com.example.acer.vale_app;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Pick_UpActivity extends AppCompatActivity implements OnMapReadyCallback{

    private Button btnpickup;
   // private static final String TAG = "String";
    private GoogleMap mMap;
    private LocationManager mgr1;
    private BlankFragment ff1;
   // protected double latitude;
   // protected double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);

         ff1=new BlankFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, ff1);

        FragmentManager mgr = getSupportFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();
        trans.replace(R.id.fragment, ff1);
        trans.commit();

        btnpickup = (Button) findViewById(R.id.btndest);

        btnpickup.setText("PICKUP COMPLETE");

       /* mgr1= (LocationManager) getSystemService(LOCATION_SERVICE);

        //Step 2
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            mgr1.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1000, (LocationListener) this);
        else
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);*/

        btnpickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Pick_UpActivity.this, DeliveredActivity.class);
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
    /*
  @Override
  public void onLocationChanged(Location location) {
      // Log.d(TAG, "onLocationChanged: " + location.getLongitude() + "and Lattitude" + location.getLatitude());

      latitude=location.getLatitude();
      longitude=location.getLongitude();

      //sending data to fragment
      Bundle bundle = new Bundle();
      bundle.putFloat("Longitude", (float) latitude);
      bundle.putFloat("Latitude", (float) longitude);
      ff1.setArguments(bundle);

      Geocoder gc = new Geocoder(this);
      try {
          List<Address> list = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
          Log.d(TAG, "Address Line 1: " + list.get(0).getAddressLine(0));
          Log.d(TAG, "Address Line 2: " + list.get(0).getAddressLine(1));
          Log.d(TAG, "Address Line 3: " + list.get(0).getAddressLine(2));
          Log.d(TAG, "Country Name: " + list.get(0).getCountryName());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

      @Override
      public void onStatusChanged (String provider,int status, Bundle extras){

      }

      @Override
      public void onProviderEnabled (String provider){

      }

      @Override
      public void onProviderDisabled (String provider){

      }

      @Override
      protected void onPause () {
          super.onPause();
          if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
              return;
          }
          mgr1.removeUpdates(this);
      }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/

}
