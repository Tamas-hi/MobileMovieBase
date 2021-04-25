package com.example.mobilemoviebase.interactor.movies;

import android.util.Log;

import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;
import com.example.mobilemoviebase.network.MovieApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesInteractor {

    private MovieApi movieApi;

    @Inject
    public MoviesInteractor(MovieApi movieApi){
        this.movieApi = movieApi;
    }

    public void getMoviesByTitle(){
        Call<MovieResult> movies = movieApi.getMoviesByTitle("Test");

        movies.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                Log.d("RESULT", response.body().getSearch().get(0).getImdbID());
                System.out.println(response.body().getTotalResults());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void getMoviesById(){
        Call<MovieDetails> movies = movieApi.getMoviesByImdbId("tt4154796");

        movies.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                Log.d("RESULT", response.body().getPlot());
                System.out.println(response.body().getPlot());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


    public void addMovie(Movie movie){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        movieApi = retrofit.create(MovieApi.class);
        movieApi.addMovie(movie);
    }

    public void deleteMovie(int id){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        movieApi = retrofit.create(MovieApi.class);
        movieApi.deleteMovie(id);
    }
}
