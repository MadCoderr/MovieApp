package com.example.farooqi.movieapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farooqi.movieapp.Contract;
import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.CastModel;
import com.example.farooqi.movieapp.data.pojo.MovieDetailModel;
import com.example.farooqi.movieapp.data.utils.remote.NetworkUtils;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends YouTubeBaseActivity {

    ImageView moviePoster;
    TextView movieTitle, movieYear, movieTime, movieRate, movieOverView,
            movieTagLine, movieGenres, movieRelease, movieCountry;


    YouTubePlayerView playerView;
    RelativeLayout relative;
    LinearLayout linearLayout;

    RecyclerView castRecycler;
    CastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        moviePoster = findViewById(R.id.img_detail);
        movieTitle = findViewById(R.id.lbl_title_detail);
        movieYear = findViewById(R.id.lbl_year_detail);
        movieTime = findViewById(R.id.lbl_time_detail);
        movieRate = findViewById(R.id.lbl_rate_detail);
        movieOverView = findViewById(R.id.lbl_desc_detail);
        movieTagLine = findViewById(R.id.lbl_desc_tag_line);
        movieGenres = findViewById(R.id.lbl_desc_genre);
        movieRelease = findViewById(R.id.lbl_desc_rel_date);
        movieCountry = findViewById(R.id.lbl_desc_origin);
        castRecycler = findViewById(R.id.rec_view_cast_detail);
        playerView = findViewById(R.id.player_view);
        relative = findViewById(R.id.rel_layout_detail);
        linearLayout = findViewById(R.id.linear_layout_detail);

        final LinearLayoutManager linear = new LinearLayoutManager(this);
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        castRecycler.setLayoutManager(linear);

        Intent intent = getIntent();
        if (intent != null) {
            int movieId = intent.getIntExtra(Preferences.MOVIE_ID_KEY, 0);
            String MOVIE_DETAIL_URL = Preferences.MOVIE_BASE_URL + movieId + Preferences.MOVIE_Footer_URL;
            NetworkUtils.getMovieDetails(MOVIE_DETAIL_URL, new Contract.onMovieDetail() {
                @Override
                public void onMovieDetailsSuccess(MovieDetailModel detail, ArrayList<CastModel> castList) {
                    Log.i("detail_activity", "onMovieDetail called");
                    linearLayout.setVisibility(View.VISIBLE);
                    setDetailViews(detail);
                    adapter = new CastAdapter(castList);
                    castRecycler.setAdapter(adapter);
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Log.d("detail", "intent is null");
        }

    }

    private void setDetailViews(final MovieDetailModel model) {
        Picasso
                .with(DetailActivity.this)
                .load(model.getPosterPath())
                .into(moviePoster);

        moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relative.setVisibility(View.GONE);
                playerView.setVisibility(View.VISIBLE);

                playerView.initialize(Preferences.API_KEY,
                        new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo(model.videoKey);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult result) {

                    }
                });
            }
        });

        movieTitle.setText(model.title);
        movieYear.setText(model.releaseDate);
        movieTime.setText(String.valueOf(model.runTime));
        movieRate.setText(String.valueOf(model.voteAverage) + "/10");
        movieOverView.setText(model.overView);
        movieTagLine.setText(model.tagLine);
        movieRelease.setText(model.releaseDate);

        if (model.genreName != null) {
            for (int i = 0; i < model.genreName.length; i++) {
                movieGenres.append(model.genreName[i] + " ");
            }
        }

        if (model.countries != null) {
            for (int i = 0; i < model.countries.length; i++) {
                movieCountry.append(model.countries[i] + " ");
            }
        }
    }


    private class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

        ArrayList<CastModel> castList;

        public CastAdapter(ArrayList<CastModel> castList) {
            this.castList = new ArrayList<>();
            this.castList = castList;
        }

        @Override
        public CastAdapter.CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getLayoutInflater().inflate(R.layout.item_list_cast, parent, false);
            return new CastViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CastAdapter.CastViewHolder holder, int position) {
            CastModel model = castList.get(position);
            Picasso
                    .with(DetailActivity.this)
                    .load(model.getProfilePath())
                    .placeholder(R.drawable.person_placeholder)
                    .error(R.drawable.no_image_found)
                    .into(holder.castImage);

            holder.castName.setText(model.name);
            holder.castRoleName.setText(model.character);

        }

        @Override
        public int getItemCount() {
            return castList.size();
        }

        public class CastViewHolder  extends RecyclerView.ViewHolder {

            ImageView castImage;
            TextView castName;
            TextView castRoleName;

            public CastViewHolder(View itemView) {
                super(itemView);

                castImage = itemView.findViewById(R.id.img_cast);
                castName = itemView.findViewById(R.id.lbl_cast_name);
                castRoleName = itemView.findViewById(R.id.lbl_cast_name_role);

            }
        }
    }

}
