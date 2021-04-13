package com.example.mobilemoviebase.network;

import com.example.mobilemoviebase.model.MoviesResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("search")
    Call<MoviesResult> getMovies();
}
