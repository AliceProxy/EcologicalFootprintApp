package com.example.ecologicalfootprintapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecologicalfootprintapp.data.Score;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FragmentScoreTracking extends Fragment {
    private static final String TAG = "FragmentTracking";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        LineChart trackingChart; // line chart for tracking progress over time
        ArrayList<String> xAxis = new ArrayList<>(); // tracks the labels for the graph
        ArrayList<Entry> yAxis = new ArrayList<>(); // values for the graph
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        List<Score> scoreList = new ArrayList<Score>();
        View view = inflater.inflate(R.layout.fragment_tracking_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        trackingChart = view.findViewById(R.id.lineChart);

        if(((MainActivity)getActivity()).questionaire.getCompleted() == true)
        {
            try{
                scoreList = ((MainActivity)getActivity()).questionaire.getScores();

                if(scoreList.size() < 3)
                {
                    xAxis.add("Monday");
                    xAxis.add("Tuesday");
                    xAxis.add("Wednesday");
                    xAxis.add("Thursday");
                    xAxis.add("Friday");
                    xAxis.add("Saturday");
                    xAxis.add("Sunday");



                    // will need to be re-worked after score is calculated
                    yAxis.add(new Entry(10, 0));
                    yAxis.add(new Entry(9, 1));
                    yAxis.add(new Entry(8, 2));
                    yAxis.add(new Entry(6, 3));
                    yAxis.add(new Entry(8, 4));
                    yAxis.add(new Entry(7, 5));
                    yAxis.add(new Entry(8, 6));

                    trackingChart.setVisibleXRange(6, 10);

                    Log.e("No Scores", "no scores");

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Can't display tracking without any scores!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                else
                {
                    float max = 1, min = 0;
                    for(int i = 2; i < scoreList.size(); i++)
                    {
                        xAxis.add("Day "+(i-2));
                        yAxis.add(new Entry((float)scoreList.get(i).getScore(), i-2));

                        if((float)scoreList.get(i).getScore() > max)
                        {
                            max = (float)scoreList.get(i).getScore();
                        }
                        if((float)scoreList.get(i).getScore() < min)
                        {
                            min = (float)scoreList.get(i).getScore();
                        }
                    }
                    for(int i = 0; i < yAxis.size(); i++) {
                        Log.e("Yaxis: ", yAxis.get(i).toString());
                    }

                    for(int i = 0; i < xAxis.size(); i++) {
                        Log.e("Xaxis: ", xAxis.get(i));
                    }

                    trackingChart.setVisibleXRangeMaximum(scoreList.size() - 2);
                }
            }
            catch(Error e)
            {
                Log.e("Error in res", e.getMessage());
            }
        }
        else
        {
            xAxis.add("Monday");
            xAxis.add("Tuesday");
            xAxis.add("Wednesday");
            xAxis.add("Thursday");
            xAxis.add("Friday");
            xAxis.add("Saturday");
            xAxis.add("Sunday");


            // will need to be re-worked after score is calculated
            yAxis.add(new Entry(10, 0));
            yAxis.add(new Entry(9, 1));
            yAxis.add(new Entry(8, 2));
            yAxis.add(new Entry(6, 3));
            yAxis.add(new Entry(8, 4));
            yAxis.add(new Entry(7, 5));
            yAxis.add(new Entry(8, 6));

            trackingChart.setVisibleXRange(6, 10);

            Log.e("No Scores", "no scores");

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("Can't display tracking without any scores!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }




        String[] stringArray = new String[xAxis.size()];

        for(int i = 0; i < xAxis.size(); i++)
        {
            stringArray[i] = xAxis.get(i).toString();
        }

        LineDataSet lineDataSet = new LineDataSet(yAxis, "Values");
        lineDataSet.setDrawCircles(true);
        lineDataSet.setCircleRadius(3);
        lineDataSet.setLineWidth(15);
        lineDataSet.setValueTextColor(this.getResources().getColor(R.color.textLight));
        lineDataSet.setValueTextSize(24);
        lineDataSet.setColor(this.getResources().getColor(R.color.color3));

        lineDataSets.add(lineDataSet);

        trackingChart.setData(new LineData(stringArray, lineDataSets));

        trackingChart.setTouchEnabled(true);
        trackingChart.setDragEnabled(true);
        trackingChart.setGridBackgroundColor(this.getResources().getColor(R.color.color3));
        trackingChart.setDescriptionColor(this.getResources().getColor(R.color.textLight));
        trackingChart.setDescriptionTextSize(1);
        trackingChart.setScaleEnabled(true);
        trackingChart.setPinchZoom(true);

        XAxis chartXAxis = trackingChart.getXAxis();
        //chartXAxis.setLabelRotationAngle(15);
        chartXAxis.setPosition(XAxis.XAxisPosition.TOP);
        chartXAxis.setTextSize(18f);
        chartXAxis.setTextColor(this.getResources().getColor(R.color.textLight));
        chartXAxis.setDrawAxisLine(true);
        chartXAxis.setDrawGridLines(true);
        chartXAxis.setDrawLabels(true);
        chartXAxis.setGridLineWidth(5);
        chartXAxis.setAxisLineColor(this.getResources().getColor(R.color.color3));
        chartXAxis.setAxisLineWidth(5);


        YAxis chartYAxis = trackingChart.getAxisLeft();
        chartYAxis.setDrawZeroLine(true);
        chartYAxis.setTextSize(18f);
        chartYAxis.setTextColor(this.getResources().getColor(R.color.textLight));
        chartYAxis.setDrawAxisLine(true);
        chartYAxis.setDrawGridLines(true);
        chartYAxis.setDrawLabels(true);
        chartYAxis.setGridLineWidth(5);
        chartYAxis.setAxisLineColor(this.getResources().getColor(R.color.color3));
        chartYAxis.setAxisLineWidth(5);

        YAxis chartYAxis2 = trackingChart.getAxisRight();
        chartYAxis2.setDrawZeroLine(false);
        chartYAxis2.setTextColor(this.getResources().getColor(R.color.textLight));
        chartYAxis2.setDrawAxisLine(true);
        chartYAxis2.setDrawGridLines(true);
        chartYAxis2.setDrawLabels(false);
        chartYAxis2.setGridLineWidth(5);
        chartYAxis2.setAxisLineColor(this.getResources().getColor(R.color.color3));
        chartYAxis2.setAxisLineWidth(5);





        return view;
    }
}
