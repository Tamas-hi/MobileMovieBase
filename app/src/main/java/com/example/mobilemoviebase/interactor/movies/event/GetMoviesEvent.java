package com.example.mobilemoviebase.interactor.movies.event;

import com.example.mobilemoviebase.model.Movie;

import java.util.List;

public class GetMoviesEvent {
    private int code;
    private List<Movie> movies;
    private Throwable throwable;

    public GetMoviesEvent(){

    }

    public GetMoviesEvent(int code, List<Movie> movies, Throwable throwable) {
        this.code = code;
        this.movies = movies;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
