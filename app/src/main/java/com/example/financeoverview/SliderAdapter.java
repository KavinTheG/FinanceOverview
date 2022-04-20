package com.example.financeoverview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.revenue,
            R.drawable.expenses_icon
    };

    public String[] slide_headings = {
            "INCOME",
            "EXPENSE"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.overview_slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeading = view.findViewById(R.id.heading);
        FloatingActionButton sendToNewPageBtn = view.findViewById(R.id.floatingActionButton);

        LineChart mChart = view.findViewById(R.id.line_chart);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 10));
        yValues.add(new Entry(1, 50));
        yValues.add(new Entry(2, 30));
        yValues.add(new Entry(3, 40));
        yValues.add(new Entry(4, 70));
        yValues.add(new Entry(5, 80));
        yValues.add(new Entry(6, 20));

        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData lineData  = new LineData(dataSets);

        mChart.setData(lineData);

        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("0");
        xLabel.add("1");
        xLabel.add("2");
        xLabel.add("3");
        xLabel.add("4");
        xLabel.add("5");
        xLabel.add("6");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);

        mChart.getAxisLeft().setTextColor(Color.WHITE);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xLabel.get((int) value);
            }
        });



        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);

        sendToNewPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent incomePage = new Intent(view.getContext().getApplicationContext(), IncomePage.class);
                Intent expensePage = new Intent(view.getContext().getApplicationContext(), ExpensesPage.class);

                if (position == 0) {
                    view.getContext().startActivity(incomePage);
                } else if (position == 1) {
                    view.getContext().startActivity(expensePage);
                }

            }
        });

        container.addView(view);

        return  view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
