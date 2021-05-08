package com.example.mobilemoviebase.ui.details;

import com.example.mobilemoviebase.model.MovieDetails;

public interface DetailsScreen {
    void showMovieDetails(MovieDetails movieDetails);
    void showError(String error);
}
