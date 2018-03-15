package com.example.farooqi.movieapp;

import com.example.farooqi.movieapp.data.pojo.MovieModel;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/12/2018.
 */

public interface Contract {

    interface onTheaterMovie {
        void onTheaterMoviesObtain(ArrayList<MovieModel> theatreMovies);
        void onFailure(String message);
    }

    interface onUpComingMovie {
        void onUpComingMoviesObtain(ArrayList<MovieModel> upComingMovies);
        void onFailure(String message);
    }

    interface onMovieDetail {
        void onMovieDetailsSuccess();
        void onFailure(String message);
    }

}
