package com.example.farooqi.movieapp.data.utils.remote;

import android.util.Log;

import com.example.farooqi.movieapp.Contract;
import com.example.farooqi.movieapp.data.FetchDataFromResponse;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.CastModel;
import com.example.farooqi.movieapp.data.pojo.MovieDetailModel;
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


    public static void getListOfMovies(String url, final Contract.onMovieSuccess listener) {
        final ArrayList<MovieModel> list = new ArrayList<>();
        reqClient.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("results");
                    list.addAll(FetchDataFromResponse.getMovieDataFromResponse(array));
                    listener.onTaskListener(list);
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

    public static void getMovieDetails(String url, final Contract.onMovieDetail listener) {
        reqClient.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("network_utils", "movie Detail: " + response.toString());
                try {
                    MovieDetailModel model = FetchDataFromResponse.getMovieDetailFromResponse(response);
                    JSONArray array = response.getJSONObject("credits").getJSONArray("cast");
                    ArrayList<CastModel> list = FetchDataFromResponse.getCastDetailFromResponse(array);
                    listener.onMovieDetailsSuccess(model, list);
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


    public static void getAWholeListOfMovies(String url, final Contract.onSeeAllClick listener) {
        final ArrayList<MovieModel> movieList = new ArrayList<>();
        reqClient.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("results");
                    movieList.addAll(FetchDataFromResponse.getMovieDataFromResponse(array));
                    listener.onTaskSuccess(movieList);
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

}
