package com.example.mobilemoviebase.test;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.mobilemoviebase.db.MovieDao;
import com.example.mobilemoviebase.db.MovieDatabase;
import com.example.mobilemoviebase.mock.MockMovieApi;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.model.MovieDetails;
import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.network.MovieApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MovieTest {

    @Mock
    MovieApi movieApi;
    MovieDao movieDao;
    MovieDatabase db;

    @Before
    public void setUp(){
        movieApi = new MockMovieApi();
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase.class).allowMainThreadQueries().build();
        movieDao = db.movieDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    // Database tests

    @Test
    public void addAndGetMovieByTitle() throws Exception {
        Movie m = new Movie();
        m.setTitle("Example Title");
        movieDao.insertMovie(m);
        Movie byTitle = movieDao.getMovieByTitle("Example Title");
        assertThat(byTitle, equalTo(m));
    }

    @Test
    public void addAndGetMovieById() throws Exception {
        Movie m = new Movie();
        m.setId(22);
        m.setTitle("Example Title");
        movieDao.insertMovie(m);
        Movie byId = movieDao.getMovieById(22);
        assertThat(byId, equalTo(m));
    }

    @Test
    public void addAndGetMovieDetails() throws Exception {
        MovieDetails m = new MovieDetails();
        m.setImdbID("1234");
        m.setRuntime("122 min");
        m.setTitle("Movie with details");
        m.setPlot("very exciting movie");
        movieDao.insertMovieDetails(m);
        MovieDetails byImdbId = movieDao.getMovieDetails("1234");
        assertThat(byImdbId, equalTo(m));
    }

    @Test
    public void getAllMovies() throws Exception{
        Movie m1 = new Movie();
        m1.setTitle("First movie");
        m1.setImdbID("Test Imdb");
        m1.setPoster("www.google.com");
        m1.setType("action");
        m1.setYear("1997");

        Movie m2 = new Movie();
        m2.setTitle("Second movie");
        m2.setImdbID("Test2 imdb");
        m2.setPoster("www.image.com");
        m2.setType("thriller");
        m2.setYear("2002");

        movieDao.insertMovie(m1);
        movieDao.insertMovie(m2);

        List<Movie> movies = movieDao.getAllMovies();
        assertThat(movies.size(), equalTo(2));
        assertThat(movies.get(0).getYear(), equalTo("1997"));
        assertThat(movies.get(1).getType(), equalTo("thriller"));
    }

    @Test
    public void updateMovieTitle() throws Exception{
        Movie m1 = new Movie();
        m1.setId(1);
        m1.setTitle("First movie");
        m1.setImdbID("Test Imdb");
        m1.setPoster("www.google.com");
        m1.setType("action");
        m1.setYear("1997");

        movieDao.insertMovie(m1);
        Movie gotMovie = movieDao.getMovieById(1);
        assertThat(gotMovie.getTitle(), equalTo("First movie"));

        movieDao.updateMovieTitle(m1.getId(), "New title");
        Movie updatedMovie = movieDao.getMovieById(m1.getId());
        assertThat(updatedMovie.getTitle(), equalTo("New title"));
    }

    @Test
    public void updateMovieDetails() throws Exception{
        MovieDetails m1 = new MovieDetails();
        m1.setId(1);
        m1.setTitle("First movie");
        m1.setImdbID("Test Imdb");
        m1.setPoster("www.google.com");
        m1.setType("action");
        m1.setYear("1997");
        m1.setPlot("very exciting movie");

        movieDao.insertMovieDetails(m1);
        MovieDetails gotMovie = movieDao.getMovieDetails("Test Imdb");
        assertThat(gotMovie.getPlot(), equalTo("very exciting movie"));

        movieDao.updateMovieDetailsPlot(m1.getId(), "New plot here");
        MovieDetails updatedMovie = movieDao.getMovieDetails(m1.getImdbID());
        assertThat(updatedMovie.getPlot(), equalTo("New plot here"));
    }

    @Test
    public void deleteMovie() throws Exception{
        Movie m1 = new Movie();
        m1.setId(1);
        m1.setTitle("First movie");
        m1.setImdbID("Test Imdb");
        m1.setPoster("www.google.com");
        m1.setType("action");
        m1.setYear("1997");

        Movie m2 = new Movie();
        m2.setId(2);
        m2.setTitle("Second movie");
        m2.setImdbID("Test2 imdb");
        m2.setPoster("www.image.com");
        m2.setType("thriller");
        m2.setYear("2002");

        movieDao.insertMovie(m1);
        Movie gotMovie = movieDao.getMovieById(1);
        assertThat(gotMovie.getTitle(), equalTo("First movie"));

        movieDao.insertMovie(m2);
        Movie gotMovieTwo = movieDao.getMovieById(2);
        assertThat(gotMovieTwo.getTitle(), equalTo("Second movie"));

        movieDao.deleteMovie(m1);
        List<Movie> result = movieDao.getAllMovies();
        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void deleteMovieDetails() throws Exception{
        MovieDetails m1 = new MovieDetails();
        m1.setId(1);
        m1.setTitle("First movie");
        m1.setImdbID("Test Imdb");
        m1.setPoster("www.google.com");
        m1.setType("action");
        m1.setYear("1997");
        m1.setPlot("very exciting movie");

        movieDao.insertMovieDetails(m1);
        MovieDetails gotMovie = movieDao.getMovieDetails("Test Imdb");
        assertThat(gotMovie.getTitle(), equalTo("First movie"));

        movieDao.deleteMovieDetails(m1);
        List<MovieDetails> result = movieDao.getAllMovieDetails();
        assertTrue(result.isEmpty());
    }

    @Test
    public void deleteAllMovies() throws Exception{
        Movie m1 = new Movie();
        m1.setTitle("First movie");
        m1.setImdbID("Test Imdb");
        m1.setPoster("www.google.com");
        m1.setType("action");
        m1.setYear("1997");

        Movie m2 = new Movie();
        m2.setTitle("Second movie");
        m2.setImdbID("Test2 imdb");
        m2.setPoster("www.image.com");
        m2.setType("thriller");
        m2.setYear("2002");

        movieDao.insertMovie(m1);
        movieDao.insertMovie(m2);

        movieDao.deleteAllMovies();
        List<Movie> result = movieDao.getAllMovies();
        assertTrue(result.isEmpty());
    }

    @Test
    public void deleteAllMovieDetails() throws Exception{
        MovieDetails m1 = new MovieDetails();
        m1.setTitle("First movie");
        m1.setImdbID("Test Imdb");
        m1.setPoster("www.google.com");
        m1.setType("action");
        m1.setYear("1997");
        m1.setPlot("exciting m1 movie");

        MovieDetails m2 = new MovieDetails();
        m2.setTitle("Second movie");
        m2.setImdbID("Test2 imdb");
        m2.setPoster("www.image.com");
        m2.setType("thriller");
        m2.setYear("2002");
        m2.setPlot("exciting m2 movie");

        movieDao.insertMovieDetails(m1);
        movieDao.insertMovieDetails(m2);

        movieDao.deleteAllMovieDetails();
        List<MovieDetails> result = movieDao.getAllMovieDetails();
        assertTrue(result.isEmpty());
    }

    // Network tests

    @Test
    public void getMoviesByTitleNetwork() throws Exception{
        Call<MovieResult> call = movieApi.getMoviesByTitle("Test");
        Response<MovieResult> response = call.execute();

        assertTrue(response.isSuccessful());

        // Movies with "Test" in title DO exist
        assertFalse(response.body().getSearch().isEmpty());

        assertThat(response.body().getSearch().get(0).getTitle(), equalTo("Test"));
        assertThat(response.body().getSearch().get(0).getType(), equalTo("action"));
        assertThat(response.body().getSearch().get(0).getYear(), equalTo("1997"));
    }

    @Test
    public void getMoviesByImdbIdNetwork() throws Exception{
        Call<MovieDetails> call = movieApi.getMoviesByImdbId("1234abcd");
        Response<MovieDetails> response = call.execute();

        assertTrue(response.isSuccessful());

        // Movies with "1234abcd" in imdbId DO exist
        assertNotNull(response.body());

        assertThat(response.body().getTitle(), equalTo("Test Movie"));
        assertThat(response.body().getType(), equalTo("action"));
        assertThat(response.body().getYear(), equalTo("1997"));
    }





}
