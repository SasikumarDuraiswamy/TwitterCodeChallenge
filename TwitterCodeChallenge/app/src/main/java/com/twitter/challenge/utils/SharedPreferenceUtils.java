package com.twitter.challenge.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitter.challenge.model.Weathers;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils mSharedPreferenceUtils;

    private static final String TWITTER = "TWITTER";

    private static final String WEATHER = "WEATHER";

    private static final String CURRENT_PAGE = "CURRENT_PAGE";

    private static final String TOTAL_PAGE = "TOTAL_PAGE";

    public static SharedPreferenceUtils getInstance(){
        if(null == mSharedPreferenceUtils){
            mSharedPreferenceUtils =  new SharedPreferenceUtils();
        }
        return mSharedPreferenceUtils;
    }

    public List<Weathers> getWeathers(Context context){
        SharedPreferences aSharedPreferences = context.getSharedPreferences( TWITTER, Context.MODE_PRIVATE);
        String weathers = aSharedPreferences.getString(WEATHER, null);
        List<Weathers> weather = null;
        if(!TextUtils.isEmpty( weathers )){
            weather = (new Gson()).fromJson( weathers, new TypeToken<List<Weathers>>(){}.getType() );
        }
        return weather;
    }

    public void saveWeather(Weathers aWeathers, Context context){
        List<Weathers> weathers = getWeathers( context );
        if(null == weathers){
            weathers = new ArrayList<>();
        }
        weathers.add( aWeathers );
        SharedPreferences aSharedPreferences = context.getSharedPreferences( TWITTER, Context.MODE_PRIVATE);
        SharedPreferences.Editor aEditor = aSharedPreferences.edit();
        String weather = (new Gson()).toJson( weathers );
        aEditor.putString( WEATHER, weather );
        aEditor.apply();
    }

    public void updateCurrentPage(int currentPage, Context context){
        updateIntValue(context, currentPage, CURRENT_PAGE);
    }

    public int getCurrentPage(Context context){
        return getIntValue( context, CURRENT_PAGE );
    }

    public void updateTotalPage(int totalPage, Context context){
        updateIntValue(context, totalPage, TOTAL_PAGE);
    }

    public int getTotalPage(Context context){
        return getIntValue( context, TOTAL_PAGE );
    }

    private int getIntValue(Context context, String key){
        SharedPreferences aSharedPreferences = context.getSharedPreferences( TWITTER, Context.MODE_PRIVATE);
        return aSharedPreferences.getInt( key, 0 );
    }

    private void updateIntValue(Context context, int value, String key){
        SharedPreferences aSharedPreferences = context.getSharedPreferences( TWITTER, Context.MODE_PRIVATE);
        SharedPreferences.Editor aEditor = aSharedPreferences.edit();
        aEditor.putInt( key, value );
        aEditor.apply();
    }
}
