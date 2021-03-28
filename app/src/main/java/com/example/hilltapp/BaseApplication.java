package com.example.hilltapp;

import android.app.Application;
import android.content.Context;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {
 private  static    BaseApplication instance;

public static BaseApplication getInstance(){
    return  instance;
}
    @Override
    public void onCreate() {
        super.onCreate();
        instance= this;
    }
}


