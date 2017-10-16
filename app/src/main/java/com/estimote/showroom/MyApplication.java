package com.estimote.showroom;

import android.app.Application;
import android.app.NotificationManager;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.EstimoteSDK;


public class MyApplication extends Application {

    //private Region monitoringRegion = new Region("region", UUID.fromString("my-UUID"), 1, 1);
    private BeaconManager beaconManager;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        EstimoteSDK.initialize(getApplicationContext(), "daivan35@gmail.com", "ricjin35");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }
}
