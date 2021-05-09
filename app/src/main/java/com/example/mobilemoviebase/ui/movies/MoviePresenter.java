package com.example.mobilemoviebase.ui.movies;

import android.content.Context;

import com.example.mobilemoviebase.interactor.movies.MoviesInteractor;
import com.example.mobilemoviebase.interactor.movies.event.GetMoviesEvent;
import com.example.mobilemoviebase.network.MovieApi;
import com.example.mobilemoviebase.ui.Presenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePresenter extends Presenter<MovieScreen> {

    @Inject
    public MoviePresenter(){

    }

    @Override
    public void attachScreen(MovieScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetMoviesEvent event) {
        if (event.getMovies() != null) {
            screen.showMovies(event.getMovies());
        } else{
            screen.showError(event.getMessage());
        }
    }

    public void showMoviesSearchList(Context context, String movieSearch){
        loadMoviesInBackground(context, movieSearch);
    }

    public void loadMoviesInBackground(Context context, String movieSearch){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        MovieApi api = retrofit.create(MovieApi.class);
        MoviesInteractor moviesInteractor = new MoviesInteractor(api);
        moviesInteractor.getMoviesByTitle(context, movieSearch);

    }
}
