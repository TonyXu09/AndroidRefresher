package com.yitianxu.simplepinterest.WeatherModels;

/**
 * Created by tonyxu on 1/10/17.
 */

// WeatherTemperature temp data for weather
public class WeatherTemperature {
    float temp;
    float temp_min;
    float temp_max;
    int humidity;

    public float getTemp() {
        return Math.round(temp - 273.15f);
    }

    public float getTemp_min() {
        return Math.round(temp_min - 273.15f);
    }

    public float getTemp_max() {
        return Math.round(temp_max - 273.15f);
    }

    public int getHumidity() {
        return humidity;
    }
}
