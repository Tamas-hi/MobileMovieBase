package com.example.mobilemoviebase.mock;

import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;
import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.network.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockMovieApi implements MovieApi {
    @Override
    public Call<MovieResult> getMoviesByTitle(String title) {
        final MovieResult movieResult = new MovieResult();
        final List<Movie> movies = new ArrayList<Movie>();

        Movie m = new Movie();
        m.setTitle("Test");
        m.setImdbID("1234abcd");
        m.setYear("1997");
        m.setType("action");
        m.setPoster("www.google.com");

        Movie m2 = new Movie();
        m2.setTitle("Another movie");
        m2.setImdbID("abcd1234");
        m2.setYear("2000");
        m2.setType("thriller");
        m.setPoster("www.image.com");

        movies.add(m);
        movies.add(m2);
        movieResult.setSearch(movies);

        Call<MovieResult> call = new Call<MovieResult>(){

            @Override
            public Response<MovieResult> execute() throws IOException {
                return Response.success(movieResult);
            }

            @Override
            public void enqueue(Callback<MovieResult> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<MovieResult> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<MovieDetails> getMoviesByImdbId(String imdbId) {

        MovieDetails m = new MovieDetails();
        m.setTitle("Test Movie");
        m.setImdbID("1234abcd");
        m.setYear("1997");
        m.setType("action");
        m.setPoster("www.google.com");
        m.setPlot("Exciting movie");
        m.setRuntime("122 min");

        Call<MovieDetails> call = new Call<MovieDetails>(){

            @Override
            public Response<MovieDetails> execute() throws IOException {
                return Response.success(m);
            }

            @Override
            public void enqueue(Callback<MovieDetails> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<MovieDetails> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<ResponseBody> addMovie(Movie movie) {
        // Not supported by the API
        return null;
    }

    @Override
    public Call<ResponseBody> deleteMovie(int movieId) {
        // Not supported by the API
        return null;
    }
}
