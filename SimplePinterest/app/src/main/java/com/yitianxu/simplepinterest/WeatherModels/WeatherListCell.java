package com.yitianxu.simplepinterest.WeatherModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyxu on 1/10/17.
 */

public class WeatherListCell {
    WeatherTemperature main;
    List<WeatherDetails> weather;
    long dt;

    public WeatherListCell() {
        weather = new ArrayList<WeatherDetails>();
    }

    public WeatherTemperature getMain() {
        return main;
    }

    public List<WeatherDetails> getWeather() {
        return weather;
    }

    public long getDt() {
        return dt;
    }
}
