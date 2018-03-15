package com.example.farooqi.movieapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.example.farooqi.movieapp.Contract;
import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.adapter.ComingAdapter;
import com.example.farooqi.movieapp.adapter.SliderAdapter;
import com.example.farooqi.movieapp.adapter.TheaterAdapter;
import com.example.farooqi.movieapp.data.FakeData;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.example.farooqi.movieapp.data.utils.remote.NetworkUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    SliderLayout sliderLayout;
    SliderAdapter sliderAdapter;

    RecyclerView theaterRecycler;
    RecyclerView comingRecycler;


    TheaterAdapter tAdapter;
    ComingAdapter cAdapter;

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
        sliderAdapter = new SliderAdapter(context);
        sliderAdapter.setSliderLayout(sliderLayout);

        theaterRecycler = v.findViewById(R.id.rec_view_theater_home);
        comingRecycler = v.findViewById(R.id.rec_view_com_soon_home);

        setLayoutManager(theaterRecycler);
        setLayoutManager(comingRecycler);

        NetworkUtils.getTheaterMovies(new Contract.onTheaterMovie() {
            @Override
            public void onTheaterMoviesObtain(ArrayList<MovieModel> theatreMovies) {
                Log.d("home_frag","fromTheater" + theatreMovies.toString());
                tAdapter = new TheaterAdapter(context, theatreMovies);
                theaterRecycler.setAdapter(tAdapter);
            }

            @Override
            public void onFailure(String message) {
               Log.d("home_frag","fromTheater" + message);
            }
        });


        NetworkUtils.getUpComingMovies(new Contract.onUpComingMovie() {
            @Override
            public void onUpComingMoviesObtain(ArrayList<MovieModel> upComingMovies) {
                Log.d("home_frag","fromUpComing" + upComingMovies.toString());
                cAdapter = new ComingAdapter(context, upComingMovies);
                comingRecycler.setAdapter(cAdapter);
            }

            @Override
            public void onFailure(String message) {
                Log.i("home_frag", "fromUpComing: " + message);
            }
        });

        return v;
    }

    private void setLayoutManager(RecyclerView recycler) {
        LinearLayoutManager linear = new LinearLayoutManager(context);
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(linear);
    }
}
