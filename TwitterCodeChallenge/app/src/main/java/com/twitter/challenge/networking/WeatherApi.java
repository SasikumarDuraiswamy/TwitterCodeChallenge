package com.twitter.challenge.networking;

import com.twitter.challenge.model.Weathers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {

    @GET("future_{page}.json")
    Call<Weathers> getWeather(@Path("page") int page);

    @GET("future_1.json")
    Call<Weathers> getWeather();
}
