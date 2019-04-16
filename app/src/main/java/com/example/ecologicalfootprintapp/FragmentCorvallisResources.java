package com.example.ecologicalfootprintapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentCorvallisResources extends Fragment {
    private static final String TAG = "FragmentCorvallis";

    private Button WaterButton, RecyclingButton, EnergyButton, AgricultureButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_corvallis_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        WaterButton = (Button) view.findViewById(R.id.WaterButton);
        EnergyButton = (Button) view.findViewById(R.id.EnergyButton);
        RecyclingButton = (Button) view.findViewById(R.id.RecyclingButton);
        AgricultureButton = (Button) view.findViewById(R.id.AgricultureButton);

        WaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(23);

            }
        });

        EnergyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(24);

            }
        });

        RecyclingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(26);

            }
        });

        AgricultureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(25);

            }
        });

        return view;
    }
}
