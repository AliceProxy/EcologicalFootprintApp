package com.example.ecologicalfootprintapp;

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
import android.widget.TextView;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class FragmentScoreBreakdown extends Fragment
{
    private static final String TAG = "FragmentBreakdown";

    int pieColors[] = new int[5]; // stores all of the colors to be selected for the graph
    ArrayList <Float> graphVals = new ArrayList<Float>();
    ArrayList <String> graphLabels = new ArrayList<String>();

    TextView score;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_breakdown_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        score = (view).findViewById(R.id.middleScore);


        // manually adding colors to choose from later
        pieColors[0] = this.getResources().getColor(R.color.color1); // carbon emissions
        pieColors[1] = this.getResources().getColor(R.color.colorPrimaryDark); // forest products
        pieColors[2] = this.getResources().getColor(R.color.colorStrong); // crop land
        pieColors[3] = this.getResources().getColor(R.color.colorAccent); // animal products
        pieColors[4] = this.getResources().getColor(R.color.color2); // land use


        if(((MainActivity)getActivity()).questionaire.getCompleted() == false)
        {
            emptyPie();
            addPieValue("No Score", 30.0f);
            drawpie(view);
        }
        else
        {
            emptyPie();
            addPieValue("CarbonEmissions", ((MainActivity)getActivity()).questionaire.getCarbonInfluence());
            addPieValue("AnimalProducts", ((MainActivity)getActivity()).questionaire.getLivestockInfluence());
            addPieValue("LandUse", ((MainActivity)getActivity()).questionaire.getLandUseInfluence());
            addPieValue("CropLand", ((MainActivity)getActivity()).questionaire.getCropLandInfluence());
            addPieValue("ForestProducts", ((MainActivity)getActivity()).questionaire.getForestProductInfluence());

            for(int i = 0; i < graphVals.size(); i++)
            {
                Log.e("PIE Val","Value "+i+": "+graphVals.get(i));
            }

            for(int i = 0; i < graphLabels.size(); i++)
            {
                Log.e("PIE Label","Value "+i+": "+graphLabels.get(i));
            }

            DecimalFormat df = new DecimalFormat("0.00");
            score.setText(""+df.format(((MainActivity)getActivity()).questionaire.getScore()));
            drawpie(view);
        }

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
            config.addData(new SimplePieInfo(graphVals.get(i), pieColors[i], graphLabels.get(i))).drawText(true);
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

    public void emptyPie(){
        graphVals = new ArrayList<Float>();
        graphLabels = new ArrayList<String>();
    }

}
