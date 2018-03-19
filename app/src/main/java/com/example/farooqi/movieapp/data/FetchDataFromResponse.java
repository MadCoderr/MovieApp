package com.example.farooqi.movieapp.data;

import android.util.Log;

import com.example.farooqi.movieapp.data.pojo.CastModel;
import com.example.farooqi.movieapp.data.pojo.MovieDetailModel;
import com.example.farooqi.movieapp.data.pojo.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
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


    public static MovieDetailModel getMovieDetailFromResone(JSONObject object) throws JSONException {
        int movieId = object.getInt("id");
        int voteCount = object.getInt("vote_count");
        int runTime = object.getInt("runtime");
        double voteAverage = object.getDouble("vote_average");
        double popularity = object.getDouble("popularity");
        String title = object.getString("original_title");
        String imdbId = object.getString("imdb_id");
        String posterPath = object.getString("poster_path");
        String overView = object.getString("overview");
        String releaseDate = object.getString("release_date");
        String homePage = object.getString("homepage");
        String status = object.getString("status");
        String tagLine = object.getString("tagline");

        JSONArray genreArray = object.getJSONArray("genres");
        String[] genres = getObjectsFromJsonArray(genreArray);

        JSONArray countryArray = object.getJSONArray("production_countries");
        String[] countries = getObjectsFromJsonArray(countryArray);

        JSONArray companyArray = object.getJSONArray("production_companies");
        String[] companies = getObjectsFromJsonArray(companyArray);


        JSONArray videoArray = object.getJSONObject("videos").getJSONArray("results");
        String videoKey = videoArray.getJSONObject(0).getString("key");
        Log.i("fetch_data", "video key: " + videoKey);

        return new MovieDetailModel(
                movieId, voteCount, runTime, voteAverage, popularity, title, imdbId, posterPath,
                overView, releaseDate, homePage, status, tagLine, videoKey, countries, genres,
                companies
        );

    }

    private static String[] getObjectsFromJsonArray(JSONArray array) throws JSONException {
        String[] strArray = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            strArray[i] = object.getString("name");
            Log.i("fetch_data", "objects: " + strArray[i]);
        }
        return strArray;
    }

    public static ArrayList<CastModel> getCastDetailFromResponse(JSONArray array) throws JSONException {
        ArrayList<CastModel> castList = new ArrayList<>();
        for (int i = 0 ; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            int castId = object.getInt("cast_id");
            int id = object.getInt("id");
            String character = object.getString("character");
            String name = object.getString("name");
            String creditId = object.getString("credit_id");
            String profilePath = object.getString("profile_path");

            castList.add(new CastModel(
               castId, id, character, name, creditId, profilePath
            ));
        }

        return castList;
    }

}
