package com.yitianxu.simplepinterest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yitianxu.simplepinterest.WeatherModels.Weather;
import com.yitianxu.simplepinterest.WeatherModels.WeatherListCell;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private RecyclerView mHourlyRecyclerView;
    private HourlyTemperatureAdapter mHourlyAdapter;

    private RecyclerView mDailyRecyclerView;
    private HourlyTemperatureAdapter mDailyAdapter;

    private TextView mCityName;
    private TextView mCurrentTime;
    private TextView mCurrentTemperature;
    private TextView mCurrentTemperatureMin;
    private TextView mCurrentTemperatureMax;
    private ImageView mCurrentTemperatureIcon;

    // Trailing slash is needed
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final int CITY_ID_SAN_FRANCISCO = 5391997;
    private static final String API_KEY = "7e91ac4e23b3d4944e722cf538da494a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHourlyRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mDailyRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_daily);
        mCityName = (TextView) findViewById(R.id.city_name);
        mCurrentTime = (TextView) findViewById(R.id.current_time);
        mCurrentTemperature = (TextView) findViewById(R.id.current_temp);
        mCurrentTemperatureMin = (TextView) findViewById(R.id.current_temp_low);
        mCurrentTemperatureMax = (TextView) findViewById(R.id.current_temp_high);
        mCurrentTemperatureIcon = (ImageView) findViewById(R.id.current_weather_icon);


        // Use this if content does not change size, optimize performance
        mHourlyRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mHourlyRecyclerView.setLayoutManager(layoutManager);

        mHourlyAdapter = new HourlyTemperatureAdapter(this, new ArrayList<WeatherListCell>());
        mHourlyRecyclerView.setAdapter(mHourlyAdapter);


        mDailyRecyclerView.setHasFixedSize(true);

//        LinearLayoutManager layoutManager2
//                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mDailyRecyclerView.setLayoutManager(layoutManager);
//        mDailyRecyclerView.setAdapter(mDailyAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherService openWeatherService =
                retrofit.create(OpenWeatherService.class);

        Call<Weather> call = openWeatherService.getForecast(CITY_ID_SAN_FRANCISCO, API_KEY);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                int statusCode = response.code();
                Weather weather = response.body();
                Log.i("MainActivity", "Call Success: " + statusCode);
                mHourlyAdapter.updateDataSet(weather.getList());
                mCityName.setText(weather.getCity().getName());
                mCurrentTemperature.setText("" + weather.getList().get(0).getMain().getTemp() + "\u2103");
                mCurrentTemperatureMin.setText("Low: " + weather.getList().get(0).getMain().getTemp_min() + "\u2103");
                mCurrentTemperatureMax.setText("High: " + weather.getList().get(0).getMain().getTemp_max() + "\u2103");
                mCurrentTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
                Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" +
                        weather.getList().get(0).getWeather().get(0).getIcon() + ".png").into(mCurrentTemperatureIcon);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                // Log error here since request failed
                Log.i("MainActivity", "Call Failed");
            }
        });
    }
}
