package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.models.OpenWeatherMap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView city,temperature,weatherCondition,humidity,maxTemperature,minTemperature,pressure,wind;
    ImageView imageView;
    FloatingActionButton fab;

    ActivityMainBinding binding;
    LocationManager locationManager;
    LocationListener locationListener;



    double lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        city = binding.txtCity;
        temperature = binding.txtTemp;
        weatherCondition = binding.txtWeather;
        humidity = binding.txtHumidity;
        maxTemperature = binding.txtMaxTemp;
        minTemperature = binding.txtMinTemp;
        pressure = binding.txtPressure;
        wind = binding.txtWind;
        fab = binding.fab;


        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
            startActivity(intent);

        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                Log.d("lat", String.valueOf(lat));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                LocationListener.super.onStatusChanged(provider, status, extras);
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                LocationListener.super.onProviderEnabled(provider);
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                LocationListener.super.onProviderDisabled(provider);
            }
        };




        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest
                    .permission.ACCESS_FINE_LOCATION},1);

        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    5000,50,locationListener);

        }




    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1 && permissions.length > 0 && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    5000,50,locationListener);


        }


    }
    public void getWeatherData(double lat,double lon){
        WeatherAPI  weatherAPI = RetrofitWeather.getClient().create(WeatherAPI.class);
        Call<OpenWeatherMap> call = weatherAPI.getWeatherWithLocation(lat,lon);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                city.setText(response.body().getName()+" , "+response.body().getSys().getCountry());
                temperature.setText(response.body().getMain().getTemp() + "°C");
                weatherCondition.setText(response.body().getWeather().get(0).getDescription());
                humidity.setText(" : "+response.body().getMain().getHumidity() + "%");
                maxTemperature.setText(" : "+response.body().getMain().getTemp_max()+" °C");
                minTemperature.setText(" : "+response.body().getMain().getTemp_min()+" °C");
                pressure.setText(" : "+response.body().getMain().getPressure());
                wind.setText(" : "+response.body().getMain().getPressure());

                String iconCode = response.body().getWeather().get(0).getIcon();
                Picasso.get().load("https://openweather.org/img/wn/"+iconCode+"10d@2x.png")
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView);


            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();

            }
        });
    }

}