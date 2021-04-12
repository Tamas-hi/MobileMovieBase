package com.example.mobilemoviebase.ui.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilemoviebase.R;
import com.example.mobilemoviebase.model.Movie;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> moviesList;

    public MovieAdapter(Context context, List<Movie> moviesList){
        this.context = context;
        this.moviesList = moviesList;
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
        Movie movie = moviesList.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieDirector.setText(movie.getDirector());
        holder.movieReleaseYear.setText(String.valueOf(movie.getYear()));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView movieTitle;
        public TextView movieDirector;
        public TextView movieReleaseYear;

        public ViewHolder(View itemView) {
            super(itemView);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieDirector = (TextView) itemView.findViewById(R.id.movieDirector);
            movieReleaseYear = (TextView) itemView.findViewById(R.id.movieYear);
        }
    }
}
