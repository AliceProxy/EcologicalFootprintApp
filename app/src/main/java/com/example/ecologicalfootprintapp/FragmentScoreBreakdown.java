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

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.ArrayList;
import java.util.Random;

public class FragmentScoreBreakdown extends Fragment
{
    private static final String TAG = "FragmentBreakdown";

    int pieColors[] = new int[8]; // stores all of the colors to be selected for the graph
    ArrayList <Float> graphVals = new ArrayList<Float>();
    ArrayList <String> graphLabels = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_breakdown_layout, container, false);
        Log.d(TAG, "onCreateView: started.");


        // manually adding colors to choose from later
        pieColors[0] = this.getResources().getColor(R.color.color3);
        pieColors[1] = this.getResources().getColor(R.color.color1);
        pieColors[2] = this.getResources().getColor(R.color.colorPrimaryDark);
        pieColors[3] = this.getResources().getColor(R.color.colorStrong);
        pieColors[4] = this.getResources().getColor(R.color.textLight);
        pieColors[5] = this.getResources().getColor(R.color.colorAccent);
        pieColors[6] = this.getResources().getColor(R.color.color2);
        pieColors[7] = this.getResources().getColor(R.color.color3);

        addPieValue("Electricity", 30);
        addPieValue("Meat", 18.0f);
        addPieValue("Water", 20.0f);
        drawpie(view);

        return view;
    }

    // draws the pie chart
    public void drawpie(View view)
    {
        AnimatedPieView mAnimatedPieView = view.findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// angle offset
                .autoSize(true)
                .strokeWidth(180)
                .textSize(65)
                .duration(2000);// duration of the animation

        // adds all of the data stored in the arrays to the graph and displays it
        for(int i = 0; i < graphLabels.size(); i++)
        {
            // choose a random color
            Random r = new Random();
            int i1 = r.nextInt(8);

                config.addData(new SimplePieInfo(graphVals.get(i), pieColors[i1], graphLabels.get(i))).drawText(true);
        }

        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }

    public void addPieValue(String label, float value)
    {
        if(graphLabels.contains(label))
            return;
        graphVals.add(value);
        graphLabels.add(label);
    }
}
