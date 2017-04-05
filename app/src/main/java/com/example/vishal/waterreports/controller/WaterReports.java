package com.example.vishal.waterreports.controller;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by vishal on 2/22/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class WaterReports extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
