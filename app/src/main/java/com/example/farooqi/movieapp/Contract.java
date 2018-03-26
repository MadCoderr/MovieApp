package com.example.farooqi.movieapp;

import com.example.farooqi.movieapp.data.pojo.CastModel;
import com.example.farooqi.movieapp.data.pojo.MovieDetailModel;
import com.example.farooqi.movieapp.data.pojo.MovieModel;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/12/2018.
 */

public interface Contract {

    interface onMovieSuccess {
        void onTaskListener(ArrayList<MovieModel> movieList);
        void onFailure(String message);
    }

    interface onMovieDetail {
        void onMovieDetailsSuccess(MovieDetailModel detail, ArrayList<CastModel> castList);
        void onFailure(String message);
    }

    interface onSeeAllClick{
        void onTaskSuccess(ArrayList<MovieModel> movieList);
        void onFailure(String message);
    }

}
