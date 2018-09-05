package com.example.admin1.mapapplication.Webservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public  class CommonUtils {
    public boolean checkConnectivity(Context pContext) {
        ConnectivityManager lConnectivityManager = (ConnectivityManager)
                pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo lNetInfo = lConnectivityManager.getActiveNetworkInfo();
        return lNetInfo != null && lNetInfo.isConnected();
    }

}
