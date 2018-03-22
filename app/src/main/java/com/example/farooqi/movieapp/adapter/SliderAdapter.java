package com.example.farooqi.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.activities.DetailActivity;
import com.example.farooqi.movieapp.data.FakeData;
import com.example.farooqi.movieapp.data.Preferences;
import com.example.farooqi.movieapp.data.pojo.MovieModel;

import java.util.ArrayList;

public class SliderAdapter implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    Context context;
    ArrayList<FakeData> fakeList;
    ArrayList<MovieModel> movieModelList;
    public SliderAdapter(Context context, ArrayList<MovieModel> list) {
        this.context = context;
        this.movieModelList = new ArrayList<>();
        this.movieModelList.addAll(list);
    }

    public void setSliderLayout(SliderLayout slider) {
        for (MovieModel data : movieModelList) {
            TextSliderView sliderView = new TextSliderView(context);

            if (data.getVoteAverage() >= 7.0) {
                sliderView
                        .image(data.getPosterPath())
                        .empty(R.drawable.movie_placeholder)
                        .error(R.drawable.no_image_found)
                        .description(data.getTitle())
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);


                Bundle bundle = new Bundle();
                bundle.putInt(Preferences.MOVIE_ID_KEY, data.getMovieId());
                sliderView.bundle(bundle);
                slider.addSlider(sliderView);
            }
        }


        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);
        slider.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Preferences.MOVIE_ID_KEY, slider.getBundle().getInt(Preferences.MOVIE_ID_KEY));
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
