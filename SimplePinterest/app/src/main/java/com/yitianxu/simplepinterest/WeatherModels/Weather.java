package com.yitianxu.simplepinterest.WeatherModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyxu on 1/10/17.
 */

public class Weather {
    City city;
    List<WeatherListCell> list;

    public Weather() {
        list = new ArrayList<WeatherListCell>();
    }

    public List<WeatherListCell> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }
}
