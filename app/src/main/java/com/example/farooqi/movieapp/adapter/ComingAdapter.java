package com.example.farooqi.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.activities.DetailActivity;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ComingAdapter extends RecyclerView.Adapter<ComingAdapter.ComingViewHolder> {

    Context context;
    ArrayList<MovieModel> movieList;

    public ComingAdapter(Context context, ArrayList<MovieModel> movieList) {
        this.context = context;
        this.movieList = new ArrayList<>();
        this.movieList.addAll(movieList);
    }

    @Override
    public ComingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_commig_soon, parent, false);
        return new ComingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComingViewHolder holder, int position) {
        MovieModel data = movieList.get(position);
        Picasso.with(context)
                .load(data.getPosterPath())
                .into(holder.itemImage);

        Log.d("coming_adapter", data.getTitle());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ComingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView itemImage;
        TextView itemRating;

        public ComingViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.img_coming_soon);
            itemRating = itemView.findViewById(R.id.lbl_rate_coming_soon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MovieModel movie = movieList.get(getAdapterPosition());
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(Preferences.MOVIE_ID_KEY, movie.getMovieId());
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                context.startActivity(intent);
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
