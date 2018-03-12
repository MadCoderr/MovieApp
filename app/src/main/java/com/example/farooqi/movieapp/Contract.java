package com.example.farooqi.movieapp;

import com.example.farooqi.movieapp.data.pojo.MovieModel;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/12/2018.
 */

public interface Contract {

    void onTheatreMoviesObtain(ArrayList<MovieModel> theatreMovies);
    void onFailure(String message);
}
