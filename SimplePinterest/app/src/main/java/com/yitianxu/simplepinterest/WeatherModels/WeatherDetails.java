package com.yitianxu.simplepinterest.WeatherModels;

/**
 * Created by tonyxu on 1/10/17.
 */

public class WeatherDetails {
    int id;
    String main; //Snow, Rain, Extreme, etc
    String description;
    String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
