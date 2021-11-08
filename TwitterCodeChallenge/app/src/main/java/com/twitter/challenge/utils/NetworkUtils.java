package com.twitter.challenge.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class NetworkUtils {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager aConnectivityManager =
                (ConnectivityManager)context.getSystemService( Context.CONNECTIVITY_SERVICE );
        Network aNetwork = aConnectivityManager.getActiveNetwork();
        NetworkCapabilities aNetworkCapabilities = aConnectivityManager.getNetworkCapabilities( aNetwork);
        return aNetworkCapabilities != null && aNetworkCapabilities.hasCapability( NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }
}
