package com.example.mobilemoviebase.ui.details;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.Movie;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public void showMovieDetails(Movie movie) {

    }
}
