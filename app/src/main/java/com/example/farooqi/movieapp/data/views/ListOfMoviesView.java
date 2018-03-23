package com.example.farooqi.movieapp.data.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farooqi.movieapp.R;

/**
 * Created by SAMSUNG on 3/23/2018.
 */

public class ListOfMoviesView extends RecyclerView.ViewHolder {

    ImageView moviePoster;
    TextView movieTitle;
    TextView movieRelDate;
    TextView movieRate;

    public ListOfMoviesView(View itemView) {
        super(itemView);

        moviePoster = itemView.findViewById(R.id.img_category);
        movieTitle = itemView.findViewById(R.id.lbl_title_category);
        movieRelDate = itemView.findViewById(R.id.lbl_rel_date_category);
        movieRate = itemView.findViewById(R.id.lbl_rate_category);
    }

    public ImageView getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(ImageView moviePoster) {
        this.moviePoster = moviePoster;
    }

    public TextView getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(TextView movieTitle) {
        this.movieTitle = movieTitle;
    }

    public TextView getMovieRelDate() {
        return movieRelDate;
    }

    public void setMovieRelDate(TextView movieRelDate) {
        this.movieRelDate = movieRelDate;
    }

    public TextView getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(TextView movieRate) {
        this.movieRate = movieRate;
    }
}
