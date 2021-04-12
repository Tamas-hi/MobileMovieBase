package com.example.mobilemoviebase.ui.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements MovieScreen {

    private static MovieAdapter adapter;
    private List<Movie> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        final FloatingActionButton fab = findViewById(R.id.fab);

        // TODO handle fab click
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.MainRecyclerView);
        moviesList = new ArrayList<>();
        // TODO connect API and get data
        moviesList.add(new Movie("Test", "Example director", 1997, 125, "asdasd", "http"));
        adapter = new MovieAdapter(moviesList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showMovies(String movieSearchTerm) {
        // TODO search for movies
    }

    @Override
    public void showMoviesInList(List<Movie> movies) {
        // TODO show movies in list
    }

    @Override
    public void showNetworkError(String errorMsg) {
        // TODO handling network error
    }

    @Override
    protected void onStart() {
        super.onStart();
        MoviePresenter.getInstance().attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoviePresenter.getInstance().detachScreen();
    }
}