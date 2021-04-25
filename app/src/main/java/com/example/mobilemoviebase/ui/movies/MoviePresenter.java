package com.example.mobilemoviebase.ui.movies;

import android.content.Context;

import com.example.mobilemoviebase.di.Network;
import com.example.mobilemoviebase.interactor.movies.MoviesInteractor;
import com.example.mobilemoviebase.ui.Presenter;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MoviePresenter extends Presenter<MovieScreen> {

    @Inject
    public MoviePresenter(){

    }

    @Override
    public void attachScreen(MovieScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showMoviesSearchList(Context context){

    }
}
