package com.example.mobilemoviebase.ui.movies;

import com.example.mobilemoviebase.ui.Presenter;

public class MoviePresenter extends Presenter<MovieScreen> {

    private static MoviePresenter instance = null;

    private MoviePresenter() {
    }

    public static MoviePresenter getInstance() {
        if (instance == null) {
            instance = new MoviePresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(MovieScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showMoviesSearchList(String movieSearchTerm) {
        screen.showMovies(movieSearchTerm);
    }
}
