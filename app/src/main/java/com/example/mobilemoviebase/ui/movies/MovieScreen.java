package com.example.mobilemoviebase.ui.movies;

import com.example.mobilemoviebase.model.Movie;

import java.util.List;

public interface MovieScreen {
    void showMovies(List<Movie> movies);
    void showError(String message);
}
