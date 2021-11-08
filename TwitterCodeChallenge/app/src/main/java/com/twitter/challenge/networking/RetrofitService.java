package com.twitter.challenge.networking;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit mRetrofit= new Retrofit.Builder()
            .baseUrl( "https://twitter-code-challenge.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S CreateService(Class<S> serviceClass){
        return mRetrofit.create(serviceClass);
    }
}
