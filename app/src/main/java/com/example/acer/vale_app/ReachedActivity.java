package com.example.acer.vale_app;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by ACER on 21-Jun-17.
 */

public class ReachedActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Button btnDest, btnpick;
    private GoogleMap mMap;
    Dialog otpdialog;
    EditText etotp;
    private LatLng currentLoc;
    LocationManager locationManager;

    //private MapView mMapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_reach);

        //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FrameLayoutFragment());

        otpdialog = new Dialog(ReachedActivity.this);

        otpdialog.setTitle("Otp Authentication");

        otpdialog.setContentView(R.layout.otp_dialog);

        btnpick = (Button) otpdialog.findViewById(R.id.btnpickup2);
        etotp = (EditText) otpdialog.findViewById(R.id.et1);

        btnpick.setEnabled(false);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    btnpick.setEnabled(false);
                } else
                    btnpick.setEnabled(true);
            }

        };
        etotp.addTextChangedListener(tw);

        btnpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpdialog.cancel();
                Intent i1 = new Intent(ReachedActivity.this, DeliveredActivity.class);
                startActivity(i1);

            }
        });

        btnDest = (Button) findViewById(R.id.btnpickup);

        btnDest.setText("Reached Destination");
        btnDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i1=new Intent(ReachedActivity.this,DeliveredActivity.class);
                //startActivity(i1);
                otpdialog.show();


            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    double latitute=location.getLatitude();
                    double longitude=location.getLongitude();

                    LatLng latLng=new LatLng(latitute,longitude);
                    LatLng latLng2 = new LatLng(28.6961009, 77.1527008);

                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    String result=null;

                    try {
                        List<Address> addresslist = geocoder.getFromLocation(latitute,longitude,1);
                        if(addresslist!=null && addresslist.size()>0){
                            Address address = addresslist.get(0);
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {

                                if (!address.getAddressLine(i).equals("null"))
                                    sb.append(address.getAddressLine(i)).append(",");
                            }
                            if (address.getLocality() != null && !address.getLocality().equals("null"))
                                sb.append(address.getLocality()).append(",");
                            if (address.getPostalCode() != null && !address.getPostalCode().equals("null"))
                                sb.append(address.getPostalCode()).append(",");
                            if (address.getCountryName() != null && !address.getCountryName().equals("null"))
                                sb.append(address.getCountryName());

                            result = sb.toString();

                            mMap.clear();
                            RetrofitDirection direction=new RetrofitDirection(mMap,ReachedActivity.this);
                            direction.get_direction_fetch_direction(latLng,latLng2);

                            Marker marker = null;
                            if(marker!=null)
                            {
                                marker.remove();
                            }


                            mMap.addMarker(new MarkerOptions().position(latLng).title(result).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.0f));

                            mMap.addMarker(new MarkerOptions().position(latLng2).title("Netaji Subhash Place metro station"));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 16.0f));

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                   }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    double latitute=location.getLatitude();
                    double longitude=location.getLongitude();

                    LatLng latLng=new LatLng(latitute,longitude);
                    LatLng latLng2 = new LatLng(28.6961009, 77.1527008);

                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    String result=null;

                    List<Address> addresslist= null;
                    try {
                        addresslist = geocoder.getFromLocation(latitute,longitude,1);
                        if(addresslist!=null && addresslist.size()>0){
                            Address address = addresslist.get(0);
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {

                                if (!address.getAddressLine(i).equals("null"))
                                    sb.append(address.getAddressLine(i)).append(",");
                            }
                            if (address.getLocality() != null && !address.getLocality().equals("null"))
                                sb.append(address.getLocality()).append(",");
                            if (address.getPostalCode() != null && !address.getPostalCode().equals("null"))
                                sb.append(address.getPostalCode()).append(",");
                            if (address.getCountryName() != null && !address.getCountryName().equals("null"))
                                sb.append(address.getCountryName());

                            result = sb.toString();
                            mMap.clear();
                            RetrofitDirection direction=new RetrofitDirection(mMap,ReachedActivity.this);
                            direction.get_direction_fetch_direction(latLng,latLng2);

                            Marker marker = null;
                            if(marker!=null)
                            {
                                marker.remove();
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mMap.addMarker(new MarkerOptions().position(latLng).title(result).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

                    mMap.addMarker(new MarkerOptions().position(latLng2).title("Netaji Subhash Place metro station"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 16.0f));

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        mMap.clear();





    }

    private  boolean checkReady(){
        if(mMap==null){
            return  false;
        }
        return true;
    }
}
