package com.example.mobilemoviebase.ui.movies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemoviebase.MobileMovieBaseApplication;
import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.ui.about.AboutActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import javax.inject.Inject;

public class MovieActivity extends AppCompatActivity implements MovieScreen {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    MoviePresenter moviePresenter;
    private MovieAdapter adapter;
    private String movieSearch;

    public static final String KEY_MOVIE = "KEY_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        MobileMovieBaseApplication.injector.inject(this);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        final FloatingActionButton fab = findViewById(R.id.fab);
        final SearchView svMovie = findViewById(R.id.searchView);
        final ImageView ivMovie = findViewById(R.id.movie_lovers_img);

        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               showPopUpWindow();
            }
        });

        svMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieSearch = query;
                getMovie(movieSearch);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ivMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, AboutActivity.class);
                startActivity(intent);

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "about");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "aboutActivity loaded");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle);
            }
        });

        initRecyclerView();
    }

    private void showPopUpWindow(){
        View popUpView = LayoutInflater.from(this).inflate(R.layout.add_new_movie,null);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this).setView(popUpView);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "fab");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "add Movie");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Movie movie = new Movie();
                EditText title = popUpView.findViewById(R.id.MovieNameEditText);
                EditText imdbId = popUpView.findViewById(R.id.MovieImdbId);
                EditText year = popUpView.findViewById(R.id.MovieYear);
                EditText type = popUpView.findViewById(R.id.MovieType);
                EditText poster = popUpView.findViewById(R.id.MoviePoster);

                movie.setTitle(title.getText().toString());
                movie.setImdbID(imdbId.getText().toString());
                movie.setYear(year.getText().toString());
                movie.setType(type.getText().toString());
                movie.setPoster(poster.getText().toString());

                adapter.addMovie(movie);
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertBuilder.create();
        alert.show();

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.MainRecyclerView);
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        moviePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        moviePresenter.detachScreen();
        super.onStop();
    }

    public void getMovie(String movieSearch){
        moviePresenter.showMoviesSearchList(this, movieSearch);
        adapter.notifyDataSetChanged();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "fetched_movie");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, movieSearch);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE, bundle);
    }

    @Override
    protected void onResume(){
        super.onResume();
        moviePresenter.showMoviesSearchList(this, movieSearch);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMovies(List<Movie> movie) {
        adapter.setMovies(movie);
    }

    @Override
    public void showError(String message) {
        System.out.println(message);
    }

}
