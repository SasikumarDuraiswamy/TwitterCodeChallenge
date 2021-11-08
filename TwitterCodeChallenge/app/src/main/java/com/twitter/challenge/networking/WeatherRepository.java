package com.twitter.challenge.networking;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.twitter.challenge.model.Weathers;
import com.twitter.challenge.utils.SharedPreferenceUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private static WeatherRepository mWeatherRepository;

    private WeatherApi mWeatherApi;

    public WeatherRepository(){
        mWeatherApi =  RetrofitService.CreateService( WeatherApi.class );
    }

    public static WeatherRepository getInstance(){
        if(null == mWeatherRepository){
            mWeatherRepository =  new WeatherRepository();
        }
        return mWeatherRepository;
    }

    public MutableLiveData<List<Weathers>> getWeathers(final int currentPage, final Context context){
        final MutableLiveData<List<Weathers>> weathers = new MutableLiveData<>();
        List<Weathers> weathersList = SharedPreferenceUtils.getInstance().getWeathers( context );
        if(null != weathersList && weathersList.size()>currentPage){
            weathers.setValue( weathersList.subList( 0, currentPage ) );
            SharedPreferenceUtils.getInstance().updateCurrentPage( currentPage, context );
        } else {
            mWeatherApi.getWeather(currentPage).enqueue( new Callback<Weathers>() {
                @Override
                public void onResponse(Call<Weathers> call, Response<Weathers> response) {
                    if(response.isSuccessful()) {
                        updateValue( currentPage, response.body(), context );
                        weathers.setValue(SharedPreferenceUtils.getInstance().getWeathers( context ));
                    }
                }

                @Override
                public void onFailure(Call<Weathers> call, Throwable t) {
                    weathers.setValue(SharedPreferenceUtils.getInstance().getWeathers( context ));
                }
            });
        }
        return weathers;
    }

    private void updateValue(int currentPage, Weathers weathers, Context context){
        SharedPreferenceUtils.getInstance().saveWeather(weathers, context);
        SharedPreferenceUtils.getInstance().updateCurrentPage( currentPage, context );
        SharedPreferenceUtils.getInstance().updateTotalPage( currentPage, context );
    }

}
