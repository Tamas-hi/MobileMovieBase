package com.example.mobilemoviebase.ui.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.Movie;

import java.util.List;

public class MovieActivity extends AppCompatActivity implements MovieScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
    }

    @Override
    public void showMovies(String movieSearchTerm) {
        // search for movies
    }

    @Override
    public void showMoviesInList(List<Movie> movies) {
        // show movies in list
    }

    @Override
    public void showNetworkError(String errorMsg) {
        // handling network error
    }

    @Override
    protected void onStart() {
        super.onStart();
        MoviePresenter.getInstance().attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoviePresenter.getInstance().detachScreen();
    }
}