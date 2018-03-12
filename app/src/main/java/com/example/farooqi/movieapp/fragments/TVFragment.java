package com.example.farooqi.movieapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farooqi.movieapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {


    public TVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv, container, false);
        return v;
    }

}
