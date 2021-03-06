package com.example.farooqi.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.activities.DetailActivity;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/9/2018.
 */

public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.TheaterHolder> {

    Context context;
    ArrayList<MovieModel> movieList;

    public TheaterAdapter(Context context, ArrayList<MovieModel> list) {
        this.context = context;
        this.movieList = new ArrayList<>();
        movieList.addAll(list);
    }

    @Override
    public TheaterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_list_movies, parent, false);
        return new TheaterHolder(v);
    }

    @Override
    public void onBindViewHolder(TheaterHolder holder, int position) {
        MovieModel model = movieList.get(position);
        holder.movieRating.setText(String.valueOf(model.getVoteAverage()));
        Picasso
                .with(context)
                .load(model.getPosterPath())
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.no_image_found)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class TheaterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView moviePoster;
        TextView movieRating;

        public TheaterHolder(View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.img_movie_poster);
            movieRating = itemView.findViewById(R.id.lbl_rate_movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MovieModel movie = movieList.get(getAdapterPosition());
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(Preferences.MOVIE_ID_KEY, movie.getMovieId());
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                context.startActivity(intent);
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
