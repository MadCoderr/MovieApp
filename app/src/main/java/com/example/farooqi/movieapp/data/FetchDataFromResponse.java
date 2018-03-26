package com.example.farooqi.movieapp.data;

import android.util.Log;

import com.example.farooqi.movieapp.data.pojo.CastModel;
import com.example.farooqi.movieapp.data.pojo.MovieDetailModel;
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

                int movieId = object.isNull("id") ? 0 :object.getInt("id");
                int voteCount = object.isNull("vote_count") ? 0: object.getInt("vote_count");
                double voteAverage = object.isNull("vote_average") ? 0 : object.getDouble("vote_average");
                double popularity  = object.isNull("popularity") ? 0 : object.getDouble("popularity");
                String title = object.isNull("title") ? "" : object.getString("title");
                String posterPath = object.isNull("poster_path") ? "" : object.getString("poster_path");
                String overView = object.isNull("overview") ? "" :object.getString("overview");
                String releaseDate = object.isNull("release_date") ? "" :object.getString("release_date");

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


    public static MovieDetailModel getMovieDetailFromResponse(JSONObject object) throws JSONException {
        int movieId = object.isNull("id") ? 0 : object.getInt("id");
        int voteCount = object.isNull("vote_count") ? 0 : object.getInt("vote_count");
        int runTime = object.isNull("runtime") ? 0 : object.getInt("runtime");
        double voteAverage = object.isNull("vote_average") ? 0 : object.getDouble("vote_average");
        double popularity = object.isNull("popularity") ? 0 :object.getDouble("popularity");
        String title = object.isNull("original_title") ? "" : object.getString("original_title");
        String imdbId = object.isNull("imdb_id") ? "" :object.getString("imdb_id");
        String posterPath = object.isNull("poster_path") ? "" : object.getString("poster_path");
        String overView = object.isNull("overview") ? "" : object.getString("overview");
        String releaseDate = object.isNull("release_date") ? "" : object.getString("release_date");
        String homePage = object.isNull("homepage") ? "" : object.getString("homepage");
        String status = object.isNull("status") ?  "": object.getString("status");
        String tagLine = object.isNull("tagline") ? "" :object.getString("tagline");

        JSONArray genreArray = object.isNull("genres") ? null : object.getJSONArray("genres");
        String[] genres = genreArray == null ? null : getObjectsFromJsonArray(genreArray);

        JSONArray countryArray = object.isNull("production_countries") ?
                null : object.getJSONArray("production_countries");
        String[] countries = countryArray == null ? null : getObjectsFromJsonArray(countryArray);

        JSONArray companyArray = object.isNull("production_companies") ?
                null : object.getJSONArray("production_companies");
        String[] companies = companyArray == null ? null : getObjectsFromJsonArray(companyArray);


        JSONArray videoArray = object.getJSONObject("videos").getJSONArray("results");
        String videoKey = videoArray == null ? null : videoArray.getJSONObject(0).getString("key");
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
            int castId = object.isNull("cast_id") ? 0 : object.getInt("cast_id");
            int id = object.isNull("id") ? 0 :object.getInt("id");
            String character = object.isNull("character") ? "" :object.getString("character");
            String name = object.isNull("name") ? "" :object.getString("name");
            String creditId = object.isNull("credit_id") ? "" : object.getString("credit_id");
            String profilePath = object.isNull("profile_path") ? "" :object.getString("profile_path");

            castList.add(new CastModel(
               castId, id, character, name, creditId, profilePath
            ));
        }

        return castList;
    }

}
