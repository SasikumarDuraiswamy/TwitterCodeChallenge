package com.twitter.challenge;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.challenge.adapters.WeatherAdapter;
import com.twitter.challenge.model.Weathers;
import com.twitter.challenge.utils.NetworkUtils;
import com.twitter.challenge.utils.SharedPreferenceUtils;
import com.twitter.challenge.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Weathers> mWeathers = new ArrayList<>();

    RecyclerView mRecyclerView;

    WeatherAdapter mWeatherAdapter;

    WeatherViewModel mWeatherViewModel;

    Button bPrvious = null;

    Button bNext = null;

    TextView tStdDev = null;

    static final int FINAL_COUNT = 5;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById( R.id.weather_list );
        bPrvious = (Button)findViewById( R.id.previous );
        bPrvious.setOnClickListener( this );

        bNext = (Button)findViewById( R.id.next );
        bNext.setOnClickListener( this );
        tStdDev = (TextView)findViewById( R.id.stddev );
        mWeatherViewModel = ViewModelProviders.of(this).get( WeatherViewModel.class );
        loadData( bNext , true);
        mWeatherViewModel.getWeatherRepository().observe( this, weatherResponse -> {
            mWeathers.clear();
            mWeathers.addAll(weatherResponse );
            updateStdDev(mWeathers);
            mWeatherAdapter.notifyDataSetChanged();
        } );
        setUpData();

    }

    private void setUpData(){
        if(null == mWeatherAdapter) {
            mWeatherAdapter = new WeatherAdapter( this , mWeathers);
            mRecyclerView.setLayoutManager( new LinearLayoutManager( this ) );
            mRecyclerView.setNestedScrollingEnabled( true );
            mRecyclerView.setAdapter( mWeatherAdapter );
        } else {
            mWeatherAdapter.notifyDataSetChanged();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        loadData(v, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadData(View v, boolean isFirst){
        int  currentPage = SharedPreferenceUtils.getInstance().getCurrentPage( this );
        if(v.getId() == R.id.next){
            if(NetworkUtils.isNetworkConnected( this )){
                if(isFirst || currentPage < FINAL_COUNT)
                mWeatherViewModel.init(isFirst? currentPage: currentPage+1, this);
            } else {
                Toast.makeText( MainActivity.this, "Please check your intenet", Toast.LENGTH_LONG ).show();
            }
        } else if(v.getId() == R.id.previous && currentPage > 0){
            mWeatherViewModel.init(currentPage-1, this);
        }
    }

    private void updateStdDev(List<Weathers> weathersList){
        double sum = 0.0, standardDeviation = 0.0;
        int length = weathersList.size();

        for(Weathers weathers : weathersList) {
            sum += weathers.getWeather().getTemp();
        }

        double mean = sum/length;

        for(Weathers weathers : weathersList) {
            standardDeviation += Math.pow(weathers.getWeather().getTemp() - mean, 2);
        }

        tStdDev.setText(String.valueOf(Math.sqrt(standardDeviation/length)));
    }


}
