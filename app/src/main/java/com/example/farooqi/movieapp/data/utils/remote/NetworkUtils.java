package com.example.farooqi.movieapp.data.utils.remote;

import android.util.Log;

import com.example.farooqi.movieapp.Contract;
import com.example.farooqi.movieapp.data.FetchDataFromResponse;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SAMSUNG on 3/12/2018.
 */

public class NetworkUtils {

    private static AsyncHttpClient reqClient = new AsyncHttpClient();
    static {
        reqClient.setThreadPool(Executors.newSingleThreadExecutor());
    }


    public static void getTheaterMovies(final Contract.onTheaterMovie listener) {
        String url = Preferences.IN_THEATRE_MOVIE_URL_ONE;
        final ArrayList<MovieModel> theaterList = new ArrayList<>();
        reqClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("results");
                    theaterList.addAll(FetchDataFromResponse.getMovieDataFromResponse(array));
                    listener.onTheaterMoviesObtain(theaterList);
                    Log.i("network", theaterList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onFailure(throwable.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                listener.onFailure(throwable.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFailure(throwable.getMessage());
            }
        });
    }


    public static void getUpComingMovies(final Contract.onUpComingMovie listener) {
        String url = Preferences.IN_COMMING_MOVIE_URL_ONE;
        final ArrayList<MovieModel> upComingList = new ArrayList<>();
        reqClient.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("results");
                    upComingList.addAll(FetchDataFromResponse.getMovieDataFromResponse(array));
                    listener.onUpComingMoviesObtain(upComingList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                listener.onFailure(throwable.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                listener.onFailure(throwable.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFailure(throwable.getMessage());
            }
        });

    }

    public static void getMovieDetails() {

    }

}
