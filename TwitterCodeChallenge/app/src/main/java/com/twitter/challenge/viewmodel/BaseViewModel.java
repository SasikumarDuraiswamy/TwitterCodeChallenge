package com.twitter.challenge.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.twitter.challenge.networking.WeatherRepository;

public abstract class BaseViewModel<T> extends ViewModel {
    protected MutableLiveData<T> mMutableLiveData = null;

    protected WeatherRepository mWeatherRepository;

    protected void init(){
        mWeatherRepository = WeatherRepository.getInstance();
    }

    public LiveData<T> getWeatherRepository(){
        return mMutableLiveData;
    }
}
