package com.yitianxu.simplepinterest;

import com.yitianxu.simplepinterest.WeatherModels.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tonyxu on 1/10/17.
 */

public interface OpenWeatherService {
    //api.openweathermap.org/data/2.5/forecast?id=524901
    @GET("forecast")
    Call<Weather> getForecast(@Query("id") int id, @Query("appid") String appId);

    //api.openweathermap.org/data/2.5/forecast/daily?id=524901
    @GET("forecast/daily")
    Call<Weather> getDailyForecast(@Query("id") int id, @Query("appid") String appId);
}
