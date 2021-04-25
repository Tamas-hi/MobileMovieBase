package com.example.mobilemoviebase.db;

import android.graphics.Movie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilemoviebase.model.MovieDetails;

import java.util.List;


@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    List<MovieDetails> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    MovieDetails getMovieById(int id);

    @Insert
    long insertMovie(MovieDetails movieDetails);

    @Update
    void updateMovie(MovieDetails movieDetails);

    @Delete
    void deleteMovie(MovieDetails movieDetails);

    @Query("DELETE FROM movies")
    void deleteAllMovies();

}
