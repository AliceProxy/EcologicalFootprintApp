package com.example.ecologicalfootprintapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Map;

public class FragmentScoreTracking extends Fragment {
    private static final String TAG = "FragmentTracking";

    public LineChart trackingChart; // line chart for tracking progress over time
    public ArrayList<String> xAxis = new ArrayList<>();
    public ArrayList<Entry> yAxis = new ArrayList<>(); // Entry from the chart repository
    public ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tracking_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        trackingChart = view.findViewById(R.id.lineChart);

        xAxis.add("Monday");
        xAxis.add("Tuesday");
        xAxis.add("Wednesday");
        xAxis.add("Thursday");
        xAxis.add("Friday");
        xAxis.add("Saturday");
        xAxis.add("Sunday");

        yAxis.add(new Entry(10, 0));
        yAxis.add(new Entry(9, 1));
        yAxis.add(new Entry(8, 2));
        yAxis.add(new Entry(6, 3));
        yAxis.add(new Entry(8, 4));
        yAxis.add(new Entry(7, 5));
        yAxis.add(new Entry(8, 6));

        String[] stringArray = new String[xAxis.size()];

        for(int i = 0; i < xAxis.size(); i++)
        {
            stringArray[i] = xAxis.get(i).toString();
        }

        LineDataSet lineDataSet = new LineDataSet(yAxis, "Values");
        lineDataSet.setDrawCircles(true);
        lineDataSet.setColor(this.getResources().getColor(R.color.color1));

        lineDataSets.add(lineDataSet);

        trackingChart.setData(new LineData(stringArray, lineDataSets));

        trackingChart.setVisibleXRangeMinimum(10f);
        trackingChart.setTouchEnabled(true);
        trackingChart.setDragEnabled(true);


        return view;
    }
}
