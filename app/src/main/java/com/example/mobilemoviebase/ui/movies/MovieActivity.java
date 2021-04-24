package com.example.mobilemoviebase.ui.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.example.mobilemoviebase.MobileMovieBaseApplication;
import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.network.MovieApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieActivity extends AppCompatActivity implements MovieScreen {

    @Inject
    MoviePresenter moviePresenter;
    private static MovieAdapter adapter;
    private List<MovieResult> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        MobileMovieBaseApplication.injector.inject(this);

        final FloatingActionButton fab = findViewById(R.id.fab);
        final SearchView svMovie = findViewById(R.id.searchView);

        // TODO handle fab click
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // add new MovieFragment
            }
        });

        svMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadMoviesInBackground();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.MainRecyclerView);
        // TODO connect API and get data
        //moviesList.add(new Movie("testId", "Test", "Example director", 1997, 125, "asdasd", "http"));
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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

    @Override
    protected void onResume(){
        super.onResume();
        moviePresenter.showMoviesSearchList(this);
    }

    @Override
    public void showMovies(List<MovieResult> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void loadMoviesInBackground() {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        MovieApi api = retrofit.create(MovieApi.class);
        Call<MovieResult> movies = api.getMoviesByTitle("Test");

        movies.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                Log.d("RESULT", response.body().getSearch().get(0).getImdbID());
                System.out.println(response.body().getTotalResults());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });



    }
}