package com.example.mobilemoviebase.interactor.movies.event;

import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;

import java.util.List;

public class GetMoviesEvent {

    private List<Movie> movies;

    private MovieDetails movieDetails;

    private String message;

    public GetMoviesEvent(){

    }

    public GetMoviesEvent(List<Movie> movies) {
        this.movies = movies;
    }

    public GetMoviesEvent(MovieDetails details){
        this.movieDetails = details;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
