package com.example.weatherapp;

import com.example.weatherapp.models.OpenWeatherMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("weather?appid=bf9526b8d43e743790bf5aca1847ffb1&units=metric")
    Call<OpenWeatherMap> getWeatherWithLocation(@Query("lat")double lat,@Query("lon") double lon);

    @GET("weather?appid=bf9526b8d43e743790bf5aca1847ffb1&units=metric")
    Call<OpenWeatherMap> getWeatherWithCityName(@Query("q")String name);
}
