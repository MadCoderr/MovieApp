package com.example.farooqi.movieapp.data;

import android.util.Log;

import com.example.farooqi.movieapp.data.pojo.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/12/2018.
 */

public class FetchDataFromResponse {

    public static ArrayList<MovieModel> getMovieDataFromResponse(JSONArray array) {
        ArrayList<MovieModel> movieList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = array.getJSONObject(i);

                int movieId = object.getInt("id");
                int voteCount = object.getInt("vote_count");
                double voteAverage = object.getDouble("vote_average");
                double popularity  = object.getDouble("popularity");
                String title = object.getString("title");
                String posterPath = object.getString("poster_path");
                String overView = object.getString("overview");
                String releaseDate = object.getString("release_date");

                JSONArray arr = object.getJSONArray("genre_ids");
                int[] genreId = new int[arr.length()];
                for (int j = 0; j < arr.length(); j++) {
                    genreId[j] = arr.getInt(j);
                }

                movieList.add(new MovieModel(movieId, voteCount, voteAverage,
                        popularity, genreId, title, posterPath, overView, releaseDate));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return movieList;
    }
}
