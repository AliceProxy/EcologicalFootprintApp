package com.example.ecologicalfootprintapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentQuestionRes extends Fragment {
    private static final String TAG = "FragmentRes";

    private Button backButton, homeButton;
    private TextView res1, res2, res3, res4, res5;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_qres_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        backButton = (Button) view.findViewById(R.id.prev5);
        homeButton = (Button) view.findViewById(R.id.homeButton);
        res1 = (TextView) view.findViewById(R.id.response1) ;
        res2 = (TextView) view.findViewById(R.id.response2) ;
        res3 = (TextView) view.findViewById(R.id.response3) ;
        res4 = (TextView) view.findViewById(R.id.response4) ;
        res5 = (TextView) view.findViewById(R.id.response5) ;

        res1.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ1());
        res2.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ2());
        res3.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ3());
        res4.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ4());
        res5.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ5());


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(10);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        return view;
    }
}
