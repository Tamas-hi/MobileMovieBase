package com.example.mobilemoviebase.ui.details;

import android.telecom.Call;

import com.example.mobilemoviebase.ui.Presenter;

import javax.inject.Inject;

public class DetailsPresenter extends Presenter<DetailsScreen> {

    @Inject
    public DetailsPresenter(){

    }

    public void queryMovieDetail(int id){

    }

    @Override
    public void attachScreen(DetailsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }
}
