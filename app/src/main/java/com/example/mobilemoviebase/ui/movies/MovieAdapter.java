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
import com.example.mobilemoviebase.model.MovieResult;
import com.example.mobilemoviebase.model.Movie;
import com.example.mobilemoviebase.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<MovieResult> movieResult;
    private MovieResult movies;

    public MovieAdapter(Context context){
        movieResult = new ArrayList<>();
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
        Movie movie = movies.getSearch().get(position);
        holder.TextViewMovie.setText(movie.getTitle());
        holder.TextViewDirector.setText(movie.getImdbID());
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
                removeMovie(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieResult.size();
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
        }
    }

   public void setMovies(List<MovieResult> movies) {

   }

   public void addMovie(MovieResult movieResult){

   }

   public void removeMovie(int pos){

   }
}
