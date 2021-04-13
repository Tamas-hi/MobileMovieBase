package com.example.mobilemoviebase;

import android.app.Application;

import com.example.mobilemoviebase.ui.UIModule;

public class MobileMovieBaseApplication extends Application {

    public static MobileMovieBaseApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerMobileMovieBaseApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
