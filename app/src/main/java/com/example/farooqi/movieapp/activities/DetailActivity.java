package com.example.farooqi.movieapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.data.Preferences;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        if (intent != null) {
            int movieId = intent.getIntExtra(Preferences.MOVIE_ID_KEY, 0);
            String MOVIE_DETAIL_URL = Preferences.MOVIE_BASE_URL + movieId + Preferences.MOVIE_Footer_URL;

        } else {
            Log.d("detail", "intent is null");
        }

    }
}
