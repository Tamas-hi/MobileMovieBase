package com.example.mobilemoviebase.ui.movies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.db.MovieDatabase;
import com.example.mobilemoviebase.model.MovieDetails;
import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;
    //private MovieResult movies = new ArrayList<>();

    public MovieAdapter(Context context){
        movieList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.TextViewMovie.setText(movie.getTitle());
        holder.TextViewType.setText(movie.getType());
        holder.TextViewReleaseYear.setText(String.valueOf(movie.getYear()));

        holder.btnDetails.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Movie IMDB id", movie.getImdbID());
                context.startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                deleteMovie(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        if(movieList.size() > 10){
            return 10;
        } else {
            return movieList.size();
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewMovie;
        public TextView TextViewType;
        public TextView TextViewReleaseYear;
        public Button btnDetails;
        public ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            TextViewMovie = (TextView) itemView.findViewById(R.id.movieTitle);
            TextViewType = (TextView) itemView.findViewById(R.id.movieType);
            TextViewReleaseYear = (TextView) itemView.findViewById(R.id.movieYear);
            btnDetails = (Button) itemView.findViewById(R.id.btnDetails);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);
        }
    }

   public void setMovies(List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
   }

   public void addMovie(Movie movie){
        movieList.add(movie);
        notifyItemInserted(movieList.size() -1);
        MovieDatabase.getDatabase(context).movieDao().insertMovie(movie);
   }

   public void deleteMovie(int pos){
        List<Movie> movies = MovieDatabase.getDatabase(context).movieDao().getAllMovies();

        Movie movieToDelete = movieList.get(pos);
        movieList.remove(movieToDelete);
        notifyItemRemoved(pos);

        MovieDatabase.getDatabase(context).movieDao().deleteMovie(movieToDelete);

   }
}
