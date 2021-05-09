package com.example.mobilemoviebase.ui.details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mobilemoviebase.MobileMovieBaseApplication;
import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.MovieDetails;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    @Inject
    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        MobileMovieBaseApplication.injector.inject(this);

        String imdbId = getIntent().getStringExtra("Movie IMDB id");
        getMovieDetails(imdbId);
    }

    @Override
    public void onStart() {
        super.onStart();
        detailsPresenter.attachScreen(this);
    }

    @Override
    public void onStop() {
        detailsPresenter.detachScreen();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getMovieDetails(String ImdbId) {
        detailsPresenter.getMovieDetails(this, ImdbId);
    }

    @Override
    public void showMovieDetails(MovieDetails movieDetails) {

        ImageView ivPoster = findViewById(R.id.movie_unique_poster);
        Glide.with(this).load(movieDetails.getPoster()).into(ivPoster);

        TextView tvLength = findViewById(R.id.tv_length);
        tvLength.setText(movieDetails.getRuntime());

        TextView tvPlot = findViewById(R.id.tv_plot);
        tvPlot.setText(movieDetails.getPlot());
    }

    @Override
    public void showError(String error) {
        System.out.println(error);
    }
}
