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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ComingAdapter extends RecyclerView.Adapter<ComingAdapter.ComingViewHolder> {

    Context context;
    ArrayList<FakeData> fakeList;

    public ComingAdapter(Context context, ArrayList<FakeData> fakeData) {
        this.context = context;
        this.fakeList = new ArrayList<>();
        this.fakeList.addAll(fakeData);
    }

    @Override
    public ComingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_commig_soon, parent, false);
        return new ComingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComingViewHolder holder, int position) {
        FakeData data = fakeList.get(position);
        Picasso.with(context)
                .load(data.image)
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return fakeList.size();
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
