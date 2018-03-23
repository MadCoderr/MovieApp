package com.example.farooqi.movieapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.farooqi.movieapp.Contract;
import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.MovieModel;
import com.example.farooqi.movieapp.data.utils.remote.NetworkUtils;
import com.example.farooqi.movieapp.data.views.ListOfMoviesView;
import com.example.farooqi.movieapp.data.views.ProgressBarView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView recycler;
    CustomAdapter adapter;

    ArrayList<MovieModel> modelList;

    String url;
    int pageCount = 1;
    boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        modelList = new ArrayList<>();

        Intent intent = getIntent();
        url = intent.getStringExtra("movie_url");
        url += pageCount;

        recycler = findViewById(R.id.rec_view_category);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        NetworkUtils.getAWholeListOfMovies(url, new Contract.onSeeAllClick() {
            @Override
            public void onTaskSuccess(ArrayList<MovieModel> movieList) {
                modelList.addAll(movieList);
                adapter = new CustomAdapter(CategoryActivity.this);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(String message) {
                Log.i("category", message);
            }
        });

        final LinearLayoutManager linear = (LinearLayoutManager) recycler.getLayoutManager();
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                int visibleItemCount = linear.getChildCount();
                int totalItemCount = linear.getItemCount();
                int pastVisibleItems = linear.findFirstVisibleItemPosition();
                if (!isLoading && pastVisibleItems + visibleItemCount >= totalItemCount) {
                    loadMore();
                    isLoading = true;
                }
            }
        });

    }


    private void loadMore() {
        Log.i("category", "loadMore called");
        modelList.add(null);
        recycler.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemInserted(modelList.size() - 1);
                pageCount++;
                url = url.substring(0, url.length() - 1) + pageCount;
                Log.i("category", "url: " + url);
                NetworkUtils.getAWholeListOfMovies(url, new Contract.onSeeAllClick() {
                    @Override
                    public void onTaskSuccess(ArrayList<MovieModel> movieList) {
                        Log.i("category", "to second list");
                        modelList.remove(modelList.size() - 1);
                        adapter.notifyItemRemoved(modelList.size());

                        modelList.addAll(movieList);
                        adapter.notifyDataSetChanged();
                        adapter.setLoading();
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.i("category", message);
                        Toast.makeText(CategoryActivity.this, "No More To Load",
                                Toast.LENGTH_SHORT).show();
                        modelList.remove(modelList.size() - 1);
                        adapter.notifyItemRemoved(modelList.size());
                        adapter.setLoading();
                    }
                });

            }
        });

    }

    private class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        Context context;

        private static final int VIEW_TYPE_ITEM = 0;
        private static final int VIEW_TYPE_LOADING = 1;


        public CustomAdapter(Context context) {
            this.context = context;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            LayoutInflater inflater = LayoutInflater.from(context);

            switch (viewType) {
                case VIEW_TYPE_ITEM:
                    View v1 = inflater.inflate(R.layout.item_list_category, parent, false);
                    viewHolder = new ListOfMoviesView(v1);
                    break;

                case VIEW_TYPE_LOADING:
                    View v2 = inflater.inflate(R.layout.layout_loading_item, parent, false);
                    viewHolder = new ProgressBarView(v2);
                    break;
            }

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case VIEW_TYPE_ITEM:
                    ListOfMoviesView v1 = (ListOfMoviesView) holder;
                    final MovieModel model = modelList.get(position);
                    if (model != null) {
                        v1.getMovieTitle().setText(model.getTitle());
                        v1.getMovieRelDate().setText(model.getReleaseDate());
                        v1.getMovieRate().setText(String.valueOf(model.getVoteAverage()));
                        Picasso
                                .with(context)
                                .load(model.getPosterPath())
                                .placeholder(R.drawable.movie_placeholder)
                                .error(R.drawable.no_image_found)
                                .into(v1.getMoviePoster());

                        v1.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra(Preferences.MOVIE_ID_KEY, model.getMovieId());
                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                                    context.startActivity(intent);
                                } else {
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                            }
                        });
                    }
                    break;

                case VIEW_TYPE_LOADING:
                    ProgressBarView v2 = (ProgressBarView) holder;
                    v2.getProBar().setIndeterminate(true);
                    v2.getProBar().getIndeterminateDrawable()
                            .setColorFilter(context.getResources().getColor(R.color.colorSelected),
                                    PorterDuff.Mode.MULTIPLY);
                    break;
            }
        }

        @Override
        public int getItemViewType(int position) {
            return modelList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        @Override
        public int getItemCount() {
            return modelList == null ? 0 : modelList.size();
        }

        public void setLoading() {
            isLoading = false;
        }
    }

}
