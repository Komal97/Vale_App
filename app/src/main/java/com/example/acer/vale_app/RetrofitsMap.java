package com.example.acer.vale_app;


import com.example.acer.vale_app.DirectionDirectory.Direction;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitsMap {

    @GET("/maps/api/directions/json?key=AIzaSyB-kbX2bVXhCiV_jSDpQ_pDta_4BLT8vRE")

    Call<Direction> getDirection(@Query("units") String units,
                                  @Query("origin") String originLatlng,
                                  @Query("destination") String destinationLatlng);
}
