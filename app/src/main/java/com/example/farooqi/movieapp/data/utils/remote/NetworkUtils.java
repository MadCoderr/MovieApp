package com.example.farooqi.movieapp.data.utils.remote;

import android.util.Log;

import com.example.farooqi.movieapp.Contract;
import com.example.farooqi.movieapp.data.FetchDataFromResponse;
import com.example.farooqi.movieapp.data.Prefexis;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executor;
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


    public static void getTheathreMovies(final Contract contractListener) {
        String url = Prefexis.IN_THEATRE_MOVIE_URL_ONE;
        final ArrayList<MovieModel> theathreList = new ArrayList<>();
        reqClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("results");
                    theathreList.addAll(FetchDataFromResponse.getMovieDataFromResponse(array));
                    contractListener.onTheatreMoviesObtain(theathreList);
                    Log.i("network", theathreList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                contractListener.onFailure(throwable.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                contractListener.onFailure(throwable.getMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                contractListener.onFailure(throwable.getMessage());
            }
        });
    }
}
