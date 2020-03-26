package com.example.my_project.test_retrofit;




import com.example.my_project.meteo.Meteo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiInterface {

    // API's endpoints
    @GET("/data/2.5/weather?units=metric")
    void getMeteo(@Query("q") String city, @Query("appid") String apiKey, Callback<Meteo> callback);

    @GET("/data/2.5/weather?units=metric")
    void getMeteogeo(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey, Callback<Meteo> callback);

// UserListResponse is POJO class to get the data from API, In above method we use List<UserListResponse> because the data in our API is starting from JSONArray and callback is used to get the response from api and it will set it in our POJO class

}