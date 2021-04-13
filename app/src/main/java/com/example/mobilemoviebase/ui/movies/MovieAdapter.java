package com.example.mobilemoviebase.ui.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

    public MovieAdapter(List<Movie> moviesList){
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
        holder.TextViewMovie.setText(movie.getTitle());
        holder.TextViewDirector.setText(movie.getDirector());
        holder.TextViewReleaseYear.setText(String.valueOf(movie.getYear()));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewMovie;
        public TextView TextViewDirector;
        public TextView TextViewReleaseYear;
        public Button btnDetails;
        public ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            TextViewMovie = (TextView) itemView.findViewById(R.id.movieTitle);
            TextViewDirector = (TextView) itemView.findViewById(R.id.movieDirector);
            TextViewReleaseYear = (TextView) itemView.findViewById(R.id.movieYear);
            btnDetails = (Button) itemView.findViewById(R.id.btnDetails);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);

            // TODO handle movieDetails click

            btnDetails.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });

            // TODO handle movieDelete click

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
