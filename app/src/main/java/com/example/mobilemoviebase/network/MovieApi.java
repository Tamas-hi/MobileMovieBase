package com.example.mobilemoviebase.network;

import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @Headers({"Accept: application/json",
                "x-rapidapi-key: acd21cc6femsh5d6f180530ebd38p1b1101jsnf5eb2e9a6be5",
                "x-rapidapi-host: movie-database-imdb-alternative.p.rapidapi.com"})
    @GET("/")
    Call<MovieResult> getMoviesByTitle (@Query("s") String title);

    @Headers({"Accept: application/json",
            "x-rapidapi-key: acd21cc6femsh5d6f180530ebd38p1b1101jsnf5eb2e9a6be5",
            "x-rapidapi-host: movie-database-imdb-alternative.p.rapidapi.com"})
    @GET("/")
    Call<MovieDetails> getMoviesByImdbId (@Query("i") String imdbId);

    @Headers({"Accept: application/json",
            "x-rapidapi-key: acd21cc6femsh5d6f180530ebd38p1b1101jsnf5eb2e9a6be5",
            "x-rapidapi-host: movie-database-imdb-alternative.p.rapidapi.com"})
    @POST("/addMovie")
    Call<ResponseBody> addMovie (@Body Movie movieProperties);

    @Headers({"Accept: application/json",
            "x-rapidapi-key: acd21cc6femsh5d6f180530ebd38p1b1101jsnf5eb2e9a6be5",
            "x-rapidapi-host: movie-database-imdb-alternative.p.rapidapi.com"})
    @DELETE("/deleteMovie/{id}")
    Call<ResponseBody> deleteMovie(@Path("id") int movieId);
}
