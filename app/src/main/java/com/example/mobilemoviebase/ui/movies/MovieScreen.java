package com.example.mobilemoviebase.ui.movies;

import com.example.mobilemoviebase.model.MovieResult;

import java.util.List;

public interface MovieScreen {

    void showMovies(List<MovieResult> movies);

    void loadMoviesInBackground();
}
