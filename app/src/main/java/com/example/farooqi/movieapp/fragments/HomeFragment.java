package com.example.farooqi.movieapp.fragments;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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


        cAdapter = new ComingAdapter(context, FakeData.getFakeData());
        comingRecycler.setAdapter(cAdapter);

        NetworkUtils.getTheathreMovies(new Contract() {
            @Override
            public void onTheatreMoviesObtain(ArrayList<MovieModel> theatreMovies) {
                Log.d("home_frag", theatreMovies.toString());
                tAdapter = new TheaterAdapter(context, theatreMovies);
                theaterRecycler.setAdapter(tAdapter);
            }

            @Override
            public void onFailure(String message) {
               Log.d("home_frag", message);
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
