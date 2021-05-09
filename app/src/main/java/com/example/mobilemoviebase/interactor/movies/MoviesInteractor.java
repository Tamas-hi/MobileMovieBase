package com.example.mobilemoviebase.interactor.movies;

import android.content.Context;

import com.example.mobilemoviebase.db.MovieDatabase;
import com.example.mobilemoviebase.interactor.movies.event.GetMoviesEvent;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;
import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.network.MovieApi;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesInteractor {

    private MovieApi movieApi;

    @Inject
    public MoviesInteractor(MovieApi movieApi){
        this.movieApi = movieApi;
    }

    public void getMoviesByTitle(Context context, String title){
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
            MovieApi movieApi = retrofit.create(MovieApi.class);

            Call<MovieResult> movies = movieApi.getMoviesByTitle(title);
            GetMoviesEvent event = new GetMoviesEvent();
            movies.enqueue(new Callback<MovieResult>() {
                @Override
                public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                    MovieResult movieResult = new MovieResult(response.body().getSearch(), response.body().getTotalResults(), response.body().isResponse());
                    if(title != null) {
                        for (Movie m : movieResult.getSearch()) {
                            String title = m.getTitle();
                            String year = m.getYear();
                            String imdbId = m.getImdbID();
                            String type = m.getType();
                            String posterUrl = m.getPoster();

                            Movie movie = new Movie();
                            movie.setTitle(title);
                            movie.setYear(year);
                            movie.setImdbID(imdbId);
                            movie.setType(type);
                            movie.setPoster(posterUrl);

                            MovieDatabase.getDatabase(context).movieDao().insertMovie(movie);
                        }
                    }
                    List<Movie> existingMovies = MovieDatabase.getDatabase(context).movieDao().getAllMovies();
                    event.setMovies(existingMovies);
                    EventBus.getDefault().post(event);


                }

                @Override
                public void onFailure(Call<MovieResult> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
    }

    public void getMovieDetailsById(Context context, String imdbId){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<MovieDetails> movies = movieApi.getMoviesByImdbId(imdbId);
        GetMoviesEvent event = new GetMoviesEvent();

        movies.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                MovieDetails movieDetails = new MovieDetails();
                movieDetails.setTitle(response.body().getTitle());
                movieDetails.setYear(response.body().getYear());
                movieDetails.setImdbID(response.body().getImdbID());
                movieDetails.setType(response.body().getType());
                movieDetails.setPoster(response.body().getPoster());
                movieDetails.setRuntime(response.body().getRuntime());
                movieDetails.setPlot(response.body().getPlot());

                MovieDatabase.getDatabase(context).movieDao().insertMovieDetails(movieDetails);
                MovieDetails existingDetails = MovieDatabase.getDatabase(context).movieDao().getMovieDetails(movieDetails.getImdbID());
                event.setMovieDetails(existingDetails);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    // Not supported by the API
    public void addMovie(Movie movie){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        movieApi = retrofit.create(MovieApi.class);
        movieApi.addMovie(movie);
    }

    // Not supported by the API
    public void deleteMovie(int id){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        movieApi = retrofit.create(MovieApi.class);
        movieApi.deleteMovie(id);
    }
}
