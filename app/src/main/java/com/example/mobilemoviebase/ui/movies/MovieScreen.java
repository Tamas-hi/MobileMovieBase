package com.example.mobilemoviebase.ui.movies;

import com.example.mobilemoviebase.model.Movie;

import java.util.List;

public interface MovieScreen {

    void showMovies(String movieSearchTerm);

    void showMoviesInList(List<Movie> movies);

    void showNetworkError(String errorMsg);
}
