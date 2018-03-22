package com.example.farooqi.movieapp.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.farooqi.movieapp.R;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView recycler;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recycler = findViewById(R.id.rec_view_category);
        adapter = new CustomAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        Context context;

        public CustomAdapter(Context context) {
            this.context = context;
        }

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_list_category, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public CustomViewHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void onClick(View view) {

            }
        }
    }

}
