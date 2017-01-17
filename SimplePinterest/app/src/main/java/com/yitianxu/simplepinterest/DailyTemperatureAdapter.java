package com.yitianxu.simplepinterest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yitianxu.simplepinterest.WeatherModels.Weather;

import java.util.List;

/**
 * Created by tonyxu on 1/11/17.
 */

public class DailyTemperatureAdapter extends RecyclerView.Adapter<DailyTemperatureAdapter.DailyTemperatureViewHolder> {
    public List<Weather> mData;


    public static class DailyTemperatureViewHolder extends RecyclerView.ViewHolder {

        public DailyTemperatureViewHolder(View itemView) {
            super(itemView);


        }
    }

    public DailyTemperatureAdapter(List<Weather> data){
        mData = data;
    }

    @Override
    public DailyTemperatureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DailyTemperatureViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
