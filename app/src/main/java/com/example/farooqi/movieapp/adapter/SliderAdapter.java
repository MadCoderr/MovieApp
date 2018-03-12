package com.example.farooqi.movieapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.farooqi.movieapp.R;
import com.example.farooqi.movieapp.data.FakeData;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/9/2018.
 */

public class SliderAdapter implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    Context context;
    ArrayList<FakeData> fakeList;
    public SliderAdapter(Context context) {
        this.context = context;
        this.fakeList = new ArrayList<>();
    }

    public void setSliderLayout(SliderLayout slider) {
        fakeList = FakeData.getFakeData();

        for (FakeData data : fakeList) {
            TextSliderView sliderView = new TextSliderView(context);
             sliderView
                    .image(data.image)
                    .description(data.title)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            Bundle bundle = new Bundle();
            bundle.putString("color_id", data.image);
            sliderView.bundle(bundle);

            slider.addSlider(sliderView);
        }


        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);
        slider.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Log.i("slider", "color id: " + slider.getBundle().getString("color_id"));
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
