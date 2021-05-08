package com.example.mobilemoviebase.db;

import com.example.mobilemoviebase.model.Movie;

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
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movieDetails WHERE imdbid = :imdbid")
    MovieDetails getMovieDetails(String imdbid);

    @Query("SELECT * FROM movies WHERE id = :id")
    MovieDetails getMovieById(long id);

    @Insert
    long insertMovie(Movie movie);

    @Insert
    long insertMovieDetails(MovieDetails movie);

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("DELETE FROM movies")
    void deleteAllMovies();

}
