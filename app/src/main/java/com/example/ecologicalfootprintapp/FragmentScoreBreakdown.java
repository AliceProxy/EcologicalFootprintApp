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

public class FragmentScoreBreakdown extends Fragment {
    private static final String TAG = "FragmentBreakdown";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_breakdown_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        drawpie(view);

        return view;
    }

    public void drawpie(View view)
    {
        AnimatedPieView mAnimatedPieView = view.findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// angle offset
                .addData(new SimplePieInfo(30, this.getResources().getColor(R.color.color1), "title 1"))
                .addData(new SimplePieInfo(18.0f, this.getResources().getColor(R.color.color3), "title 2")).drawText(true)
                .duration(2000);// duration of the animation

        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }
}
