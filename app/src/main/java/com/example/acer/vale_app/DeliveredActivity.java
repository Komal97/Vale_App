package com.example.acer.vale_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DeliveredActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button pick;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_reach);

        pick= (Button) findViewById(R.id.btnpickup);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(DeliveredActivity.this,TabbedActivity.class);
                startActivity(i1);

            }
        });

       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
               .findFragmentById(R.id.map);
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
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    LatLng latLng = new LatLng(latitude, longitude);
                    LatLng latLng2 = new LatLng(28.6961009, 77.1527008);

                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    String result = null;
                    try {

                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        if (addressList != null && addressList.size() > 0) {
                            Address address = addressList.get(0);
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
                            RetrofitDirection direction=new RetrofitDirection(mMap);
                            direction.get_direction_fetch_direction(latLng,latLng2);


                            Marker marker = null;
                            if(marker!=null)
                            {
                                marker.remove();
                            }

                        mMap.addMarker(new MarkerOptions().position(latLng).title(result).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));

                        mMap.addMarker(new MarkerOptions().position(latLng2).title("Netaji Subhash Place metro station"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 16.0f));

                    }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                    @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });

        }
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    LatLng latLng = new LatLng(latitude, longitude);
                    LatLng latLng2 = new LatLng(28.6961009, 77.1527008);

                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    String result = null;
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        if (addressList != null && addressList.size() > 0) {
                            Address address = addressList.get(0);
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {

                                if (!address.getAddressLine(i).equals("null"))
                                    sb.append(address.getAddressLine(i)).append(",");
                            }
                            if(address.getLocality() != null && !address.getLocality().equals("null"))
                                sb.append(address.getLocality()).append(",");
                            if(address.getPostalCode() != null && !address.getPostalCode().equals("null"))
                                sb.append(address.getPostalCode()).append(",");
                            if(address.getCountryName() != null && !address.getCountryName().equals("null"))
                                sb.append(address.getCountryName());
                                result = sb.toString();

                            mMap.clear();
                            RetrofitDirection direction=new RetrofitDirection(mMap);
                            direction.get_direction_fetch_direction(latLng,latLng2);


                            Marker marker = null;
                           if(marker!=null)
                           {
                               marker.remove();
                           }
                            mMap.addMarker(new MarkerOptions().position(latLng).title(result).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));

                            mMap.addMarker(new MarkerOptions().position(latLng2).title("Netaji Subhash Place metro station"));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 16.0f));

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                    @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }}
