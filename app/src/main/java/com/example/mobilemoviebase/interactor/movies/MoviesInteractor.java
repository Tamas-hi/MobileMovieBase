package com.example.mobilemoviebase.interactor.movies;

import com.example.mobilemoviebase.MobileMovieBaseApplication;
import com.example.mobilemoviebase.network.MovieApi;

import javax.inject.Inject;

public class MoviesInteractor {

    MovieApi movieApi;

    @Inject
    public MoviesInteractor(MovieApi movieApi){
        this.movieApi = movieApi;
        MobileMovieBaseApplication.injector.inject(this);
    }

    public void getMovies(String movieQuery){
        // TODO getMovies call
    }
}
