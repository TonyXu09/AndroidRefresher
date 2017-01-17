package com.yitianxu.simplepinterest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yitianxu.simplepinterest.WeatherModels.WeatherListCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyxu on 1/3/17.
 */

public class HourlyTemperatureAdapter extends RecyclerView.Adapter<HourlyTemperatureAdapter.HourlyWeatherViewHolder> {
    List<WeatherListCell> mData;
    private Context mContext;

    public static class HourlyWeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnWeatherCellClickedListener mListener;
        CardView mCardView;
        TextView mTemp;
        TextView mTimeString;
        ImageView mWeatherIcon;

        public HourlyWeatherViewHolder(View itemView, OnWeatherCellClickedListener listener) {
            super(itemView);
            mListener = listener;
            mCardView = (CardView) itemView.findViewById(R.id.cv);
            mTemp = (TextView) itemView.findViewById(R.id.temp);
            mTimeString = (TextView) itemView.findViewById(R.id.time);
            mWeatherIcon = (ImageView) itemView.findViewById(R.id.imageview_weather_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onCellClicked(v, getAdapterPosition());
        }

        public static interface OnWeatherCellClickedListener {
            public void onCellClicked(View v, int position);
        }
    }

    public HourlyTemperatureAdapter(Context context, ArrayList<WeatherListCell> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public HourlyWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        HourlyWeatherViewHolder vh = new HourlyWeatherViewHolder(v, new HourlyWeatherViewHolder.OnWeatherCellClickedListener() {
            @Override
            public void onCellClicked(View v, int position) {
                Log.i("HourlyAdapter", "OnCellClicked " + position);
                mData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, 1);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(HourlyWeatherViewHolder holder, int position) {
        WeatherListCell cell = mData.get(position);
        java.util.Date time = new java.util.Date((long) cell.getDt() * 1000);
        holder.mTimeString.setText(time.getHours() + ":00");
        holder.mTemp.setText("" + cell.getMain().getTemp() + "\u2103");
        String icon = cell.getWeather().get(0).getIcon();
        Picasso.with(mContext).load("http://openweathermap.org/img/w/" + icon + ".png").into(holder.mWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateDataSet(List<WeatherListCell> dataSet) {
        mData = dataSet;
        notifyDataSetChanged();
    }

}
