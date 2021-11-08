package com.twitter.challenge.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.challenge.R;
import com.twitter.challenge.TemperatureConverter;
import com.twitter.challenge.model.Weather;
import com.twitter.challenge.model.Weathers;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private Context mContext;

    private List<Weathers> mWeathers;

    public WeatherAdapter(Context aContext, List<Weathers> weathers){
        this.mContext = aContext;
        mWeathers =  weathers;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( mContext ).inflate( R.layout.weather_item, parent, false );
        return new WeatherViewHolder( view );
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        Weathers weathers = mWeathers.get( position );
        holder.tTemperature.setText(String.format("Current Weather %s", TemperatureConverter.celsiusToFahrenheit(weathers.getWeather().getTemp())));
        holder.tWindSpeed.setText(String.valueOf( weathers.getWind().getSpeed()));
        holder.iCloudIcon.setVisibility( weathers.getClouds().getCloudiness()>50 ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }


    public class WeatherViewHolder extends RecyclerView.ViewHolder{
        TextView tTemperature = null;

        TextView tWindSpeed = null;

        ImageView iCloudIcon = null;

        public WeatherViewHolder(@NonNull View itemView){
            super(itemView);
            tTemperature = (TextView)itemView.findViewById( R.id.temperature );
            tWindSpeed = (TextView)itemView.findViewById( R.id.wind_speed );
            iCloudIcon = (ImageView)itemView.findViewById( R.id.cloud_icon );
        }
    }
}
