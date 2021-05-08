package com.example.mobilemoviebase.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;

@Database(entities = {Movie.class, MovieDetails.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static MovieDatabase instance;
    private static final String DB_name = "movie-list";

    public static MovieDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (MovieDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, MovieDatabase.class, DB_name).allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
