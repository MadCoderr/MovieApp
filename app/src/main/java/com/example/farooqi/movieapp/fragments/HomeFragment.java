package com.example.farooqi.movieapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.example.farooqi.movieapp.Contract;
import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.activities.CategoryActivity;
import com.example.farooqi.movieapp.adapter.ComingAdapter;
import com.example.farooqi.movieapp.adapter.PopularMoviesAdapter;
import com.example.farooqi.movieapp.adapter.SliderAdapter;
import com.example.farooqi.movieapp.adapter.TheaterAdapter;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.example.farooqi.movieapp.data.utils.remote.NetworkUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    SliderLayout sliderLayout;
    SliderAdapter sliderAdapter;

    TextView theaterSeeAll;
    TextView comingSeeAll;
    TextView popularSeeAll;

    RecyclerView theaterRecycler;
    RecyclerView comingRecycler;
    RecyclerView popularRecycler;

    TheaterAdapter tAdapter;
    ComingAdapter cAdapter;
    PopularMoviesAdapter pAdapter;

    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity().getApplicationContext();

        sliderLayout = v.findViewById(R.id.slider_layout_home_frag);

        theaterRecycler = v.findViewById(R.id.rec_view_theater_home);
        comingRecycler = v.findViewById(R.id.rec_view_com_soon_home);
        popularRecycler = v.findViewById(R.id.rec_view_popular_home);

        theaterSeeAll = v.findViewById(R.id.lbl_see_all_theater_home);
        comingSeeAll = v.findViewById(R.id.lbl_see_all_com_soon_home);
        popularSeeAll = v.findViewById(R.id.lbl_see_all_popular_home);

        theaterSeeAll.setOnClickListener(this);
        comingSeeAll.setOnClickListener(this);
        popularSeeAll.setOnClickListener(this);

        setLayoutManager(theaterRecycler);
        setLayoutManager(comingRecycler);
        setLayoutManager(popularRecycler);

        // for popular movies
        NetworkUtils.getListOfMovies(Preferences.POPULAR_MOVIE_URL_ONE,
                new Contract.onMovieSuccess() {
                    @Override
                    public void onTaskListener(ArrayList<MovieModel> movieList) {
                        sliderAdapter = new SliderAdapter(context, movieList);
                        sliderAdapter.setSliderLayout(sliderLayout);

                        pAdapter = new PopularMoviesAdapter(context, movieList);
                        popularRecycler.setAdapter(pAdapter);
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.i("home_frag", "fromPopular: " + message);
                        showToast(message);
                    }
                });

       // for upcoming movies
        NetworkUtils.getListOfMovies(Preferences.IN_COMMING_MOVIE_URL_ONE,
                new Contract.onMovieSuccess() {
                    @Override
                    public void onTaskListener(ArrayList<MovieModel> movieList) {
                        cAdapter = new ComingAdapter(context, movieList);
                        comingRecycler.setAdapter(cAdapter);
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.i("home_frag", "fromUpComing: " + message);
                        showToast(message);
                    }
                });

        // for in theater movies
       NetworkUtils.getListOfMovies(Preferences.IN_THEATRE_MOVIE_URL_ONE,
               new Contract.onMovieSuccess() {
                   @Override
                   public void onTaskListener(ArrayList<MovieModel> movieList) {
                       Log.d("home_frag","fromTheater" + movieList.toString());
                       tAdapter = new TheaterAdapter(context, movieList);
                       theaterRecycler.setAdapter(tAdapter);
                   }

                   @Override
                   public void onFailure(String message) {
                       Log.d("home_frag","fromTheater" + message);
                       showToast(message);
                   }
               });

        return v;
    }

    private void setLayoutManager(RecyclerView recycler) {
        LinearLayoutManager linear = new LinearLayoutManager(context);
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(linear);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, CategoryActivity.class);
        switch (view.getId()) {
            case R.id.lbl_see_all_theater_home:
                intent.putExtra("movie_url", Preferences.IN_THEATRE_MOVIE_URL);
                break;

            case R.id.lbl_see_all_com_soon_home:
                intent.putExtra("movie_url", Preferences.IN_COMMING_MOVIE_URL);
                break;

            case R.id.lbl_see_all_popular_home:
                intent.putExtra("movie_url", Preferences.POPULAR_MOVIE_URL);
                break;
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
