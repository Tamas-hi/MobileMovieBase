package com.example.mobilemoviebase.ui.details;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemoviebase.MobileMovieBaseApplication;
import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.MovieWithDetails;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    @Inject
    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        MobileMovieBaseApplication.injector.inject(this);
    }

    @Override
    public void showMovieDetails(MovieWithDetails movie) {

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
        detailsPresenter.queryMovieDetail(1);
    }
}
