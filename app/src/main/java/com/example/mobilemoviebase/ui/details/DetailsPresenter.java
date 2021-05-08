package com.example.mobilemoviebase.ui.details;

import android.content.Context;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.telecom.Call;

import com.example.mobilemoviebase.db.MovieDatabase;
import com.example.mobilemoviebase.interactor.movies.MoviesInteractor;
import com.example.mobilemoviebase.interactor.movies.event.GetMoviesEvent;
import com.example.mobilemoviebase.model.MovieDetails;
import com.example.mobilemoviebase.network.MovieApi;
import com.example.mobilemoviebase.ui.Presenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsPresenter extends Presenter<DetailsScreen> {

    @Inject
    public DetailsPresenter(){

    }

    @Override
    public void attachScreen(DetailsScreen screen) {
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

    }

    public MovieDetails getMovieDetails(Context context, String ImdbId) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                loadMovieDetailsInBackground(context, ImdbId);
                return true;
            }
        }.execute();


        MovieDetails movieDetails = MovieDatabase.getDatabase(context).movieDao().getMovieDetails(ImdbId);
        return movieDetails;
    }

    public void loadMovieDetailsInBackground(Context context, String imdbId) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        MovieApi api = retrofit.create(MovieApi.class);
        MoviesInteractor moviesInteractor = new MoviesInteractor(api);
        moviesInteractor.getMovieDetailsById(context, imdbId);
    }
}
