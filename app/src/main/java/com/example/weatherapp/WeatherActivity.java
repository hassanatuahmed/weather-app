package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.databinding.ActivityWeatherBinding;
import com.example.weatherapp.models.OpenWeatherMap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {
     TextView cityWeather,temperatureWeather,condition,humidityWeather,maxTemperatureWeather
             ,minTemperatureWeather,pressureWeather,windWeather;
     ImageView imageViewWeather;
     Button search;
     EditText editText;
    ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cityWeather = binding.txtCity;
        temperatureWeather = binding.txtCityWeather;
        condition = binding.txtWeatherConditionWeather;
        humidityWeather = binding.txtHumidity;
        maxTemperatureWeather = binding.txtMaxTemp;
        minTemperatureWeather = binding.minTemp;
        pressureWeather = binding.txtPressure;
        windWeather = binding.txtWind;

        imageViewWeather = binding.imgViewWeather;
        search = binding.btnSearch;
        editText = binding.editTextCity;

        search.setOnClickListener(view -> {
            String cityName = editText.getText().toString();
            getWeatherData(cityName);
            editText.setText("");


        });




    }

    public void getWeatherData(String name){
        WeatherAPI  weatherAPI = RetrofitWeather.getClient().create(WeatherAPI.class);
        Call<OpenWeatherMap> call = weatherAPI.getWeatherWithCityName(name);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {

                if(response.isSuccessful()){
                    cityWeather.setText(response.body().getName()+" , "+response.body().getSys().getCountry());
                    temperatureWeather.setText(response.body().getMain().getTemp() + "°C");
                    condition.setText(response.body().getWeather().get(0).getDescription());
                    humidityWeather.setText(" : "+response.body().getMain().getHumidity() + "%");
                    maxTemperatureWeather.setText(" : "+response.body().getMain().getTemp_max()+" °C");
                    minTemperatureWeather.setText(" : "+response.body().getMain().getTemp_min()+" °C");
                    pressureWeather.setText(" : "+response.body().getMain().getPressure());
                    windWeather.setText(" : "+response.body().getMain().getPressure());

                    String iconCode = response.body().getWeather().get(0).getIcon();
                    Picasso.get().load("https://openweathermap.org/img/wn/"+iconCode+"@2x.png")
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imageViewWeather);

                }else {
                    Toast.makeText(WeatherActivity.this,"Could not fetch data",Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

                Toast.makeText(WeatherActivity.this,"error",Toast.LENGTH_SHORT).show();

            }
        });
    }

}