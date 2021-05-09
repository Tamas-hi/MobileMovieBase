package com.example.mobilemoviebase.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;

import java.util.List;


@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movieDetails")
    List<MovieDetails> getAllMovieDetails();

    @Query("SELECT * FROM movieDetails WHERE imdbid = :imdbid")
    MovieDetails getMovieDetails(String imdbid);

    @Query("SELECT * FROM movies WHERE id = :id")
    Movie getMovieById(long id);

    @Query("SELECT * FROM movies WHERE title = :title")
    Movie getMovieByTitle(String title);

    @Insert
    long insertMovie(Movie movie);

    @Insert
    long insertMovieDetails(MovieDetails movieDetails);

    @Query("UPDATE movies SET title =:title WHERE id=:id")
    void updateMovieTitle(long id, String title);

    @Query("UPDATE movieDetails SET plot =:plot WHERE id=:id")
    void updateMovieDetailsPlot(long id, String plot);

    @Delete
    void deleteMovie(Movie movie);

    @Delete
    void deleteMovieDetails(MovieDetails movieDetails);

    @Query("DELETE FROM movies")
    void deleteAllMovies();

    @Query("DELETE FROM movieDetails")
    void deleteAllMovieDetails();

}
