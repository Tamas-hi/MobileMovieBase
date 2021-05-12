package com.example.mobilemoviebase.ui;

import android.content.Context;

import com.example.mobilemoviebase.di.Network;
import com.example.mobilemoviebase.ui.about.AboutPresenter;
import com.example.mobilemoviebase.ui.details.DetailsPresenter;
import com.example.mobilemoviebase.ui.movies.MoviePresenter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {

    private Context context;

    public UIModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public MoviePresenter movieListPresenter(){
        return new MoviePresenter();
    }

    @Provides
    @Singleton
    public DetailsPresenter movieDetailsPresenter(){
        return new DetailsPresenter();
    }

    @Provides
    @Singleton
    public AboutPresenter aboutPresenter(){
        return new AboutPresenter();
    }


    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return Executors.newFixedThreadPool(1);
    }

}
