package com.example.vishal.waterreports;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by vishal on 2/22/17.
 */

public class WaterReports extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
