package com.example.financeoverview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class GeneralOverview extends AppCompatActivity {

    private ViewPager mSlideViewPager;

    private SliderAdapter sliderAdapter;


    // Line Graph Variables
    private LineChart mChart;
    private ArrayList<Entry> yValues;
    private LineDataSet set1;
    private LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_overview);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);




    }
}