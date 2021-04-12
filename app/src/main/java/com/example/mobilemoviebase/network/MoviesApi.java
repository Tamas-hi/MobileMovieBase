package com.example.mobilemoviebase.network;

import com.example.mobilemoviebase.model.MoviesResult;

import retrofit2.Call;
import retrofit2.http.GET;

// TODO all CRUD operation
public interface MoviesApi {
    @GET("search")
    Call<MoviesResult> getMovies();
}
