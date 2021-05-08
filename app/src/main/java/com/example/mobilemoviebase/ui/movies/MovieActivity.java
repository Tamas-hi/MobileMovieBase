package com.example.mobilemoviebase.ui.movies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.mobilemoviebase.MobileMovieBaseApplication;
import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.MovieDetails;
import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.network.MovieApi;
import com.example.mobilemoviebase.ui.about.AboutActivity;
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
    private MovieAdapter adapter;
    private String movieSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        MobileMovieBaseApplication.injector.inject(this);

        final FloatingActionButton fab = findViewById(R.id.fab);
        final SearchView svMovie = findViewById(R.id.searchView);
        final ImageView ivMovie = findViewById(R.id.movie_lovers_img);

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               showPopUpWindow();
            }
        });

        svMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //loadMoviesInBackground(query);
                //return true;
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
            }
        });

        initRecyclerView();
    }

    private void showPopUpWindow(){
        View popUpView = LayoutInflater.from(this).inflate(R.layout.add_new_movie,null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this).setView(popUpView);


        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = mBuilder.create();
        alert.show();

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.MainRecyclerView);
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        moviePresenter.showMoviesSearchList(this, movieSearch);
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
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMovies(List<Movie> movie) {
        adapter.setMovies(movie);
    }

    /*public void loadMoviesInBackground(String query) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        MovieApi api = retrofit.create(MovieApi.class);
        Call<MovieResult> movies = api.getMoviesByTitle(query);

        movies.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                Log.d("RESULT", response.body().getSearch().get(0).getImdbID());
                System.out.println(response.body().getSearch());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });



    }*/

    /*public void addMovie(Movie movie){
        new Thread(){
            public final void run(){
               try {
                   runOnUiThread(new Runnable(){
                       @Override
                       public void run(){
                            adapter.addMovie(movie);
                       }
                   });
               }
               catch(Exception e){
                   e.printStackTrace();
               }
            }
        }.start();
    }*/
}