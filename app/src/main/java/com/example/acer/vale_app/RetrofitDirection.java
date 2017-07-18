package com.example.acer.vale_app;

import android.graphics.Color;

import com.example.acer.vale_app.DirectionDirectory.Direction;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDirection {

    private final GoogleMap map;
    private Polyline line;
    private List<LatLng> list;

    public RetrofitDirection(GoogleMap map) {
        this.map = map;
    }

    String baseurl="https://maps.googleapis.com";

    Gson gson=new GsonBuilder().setLenient().create();

    Retrofit retrofit=new Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create(gson)).build();

    RetrofitsMap retrofitsMap=retrofit.create(RetrofitsMap.class);


    public List<LatLng> get_direction_fetch_direction(LatLng originLatlng,LatLng destinationLatlng)
    {
        String origin=originLatlng.latitude+","+originLatlng.longitude;
        String destination=destinationLatlng.latitude+","+destinationLatlng.longitude;

        Call<Direction> call=retrofitsMap.getDirection("metric",origin,destination);

        call.enqueue(new Callback<Direction>() {
            @Override
            public void onResponse(Call<Direction> call, Response<Direction> response) {

                for (int i=0;i<response.body().getRoutes().size();i++)
                {
                    String encodedstring = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
                    list=decodePoly(encodedstring);
                    if(line!=null)
                    {
                        line.remove();
                    }

                    LatLngBounds.Builder builder=LatLngBounds.builder();
                    for(LatLng latLng : list){
                        builder.include(latLng);
                    }

                    LatLngBounds bounds=builder.build();
                    CameraUpdate cu= CameraUpdateFactory.newLatLngBounds(bounds,200);

                    line=map.addPolyline(new PolylineOptions().addAll(list).geodesic(true).width(17).color(Color.parseColor("#ea8d32")));
                    map.moveCamera(cu);

                    CameraPosition pos=map.getCameraPosition();
                    CameraUpdate cun=CameraUpdateFactory.newCameraPosition(CameraPosition.builder().bearing(pos.bearing).tilt(45f).target(pos.target).zoom(pos.zoom).build());
                    map.animateCamera(cun);
                }
            }

            @Override
            public void onFailure(Call<Direction> call, Throwable t) {

            }
        });
        return list;
    }
    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }
}
