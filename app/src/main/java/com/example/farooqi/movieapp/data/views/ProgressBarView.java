package com.example.farooqi.movieapp.data.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.farooqi.movieapp.R;

/**
 * Created by SAMSUNG on 3/23/2018.
 */

public class ProgressBarView extends RecyclerView.ViewHolder {

    ProgressBar proBar;

    public ProgressBarView(View itemView) {
        super(itemView);
        proBar = itemView.findViewById(R.id.pro_bar_view);
    }

    public ProgressBar getProBar() {
        return proBar;
    }

    public void setProBar(ProgressBar proBar) {
        this.proBar = proBar;
    }
}
