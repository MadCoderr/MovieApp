package com.example.farooqi.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.activities.DetailActivity;
import com.example.farooqi.movieapp.data.FakeData;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/9/2018.
 */

public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.TheaterHolder> {

    Context context;
    ArrayList<MovieModel> movieList;

    public TheaterAdapter(Context context, ArrayList<MovieModel> list) {
        this.context = context;
        this.movieList = new ArrayList<>();
        movieList.addAll(list);
    }

    @Override
    public TheaterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_list_theater, parent, false);
        return new TheaterHolder(v);
    }

    @Override
    public void onBindViewHolder(TheaterHolder holder, int position) {
        MovieModel movieModel = movieList.get(position);
        Picasso.with(context)
                .load(movieModel.getPosterPath())
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class TheaterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView itemRating;

        public TheaterHolder(View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.img_theater);
            itemRating = itemView.findViewById(R.id.lbl_rate_coming_soon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailActivity.class);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                context.startActivity(intent);
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
