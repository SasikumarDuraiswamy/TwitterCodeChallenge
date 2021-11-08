package com.twitter.challenge.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.twitter.challenge.model.Weathers;

import java.util.List;

public class WeatherViewModel extends BaseViewModel<List<Weathers>> {

    public void init(int currentPage, Context context){
        super.init();
        mMutableLiveData = mWeatherRepository.getWeathers( currentPage, context );
    }
}
