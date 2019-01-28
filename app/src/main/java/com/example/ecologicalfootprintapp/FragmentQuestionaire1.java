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

public class FragmentQuestionaire1 extends Fragment {
    private static final String TAG = "FragmentHome";

    private Button backButton, nextButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        Log.d(TAG, "onCreateView: started.");
        backButton = (Button) view.findViewById(R.id.back1); //use this syntax for functions in the main layout
        nextButton = (Button) view.findViewById(R.id.next1);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ((MainActivity)getActivity()).setViewPager(0); // go to home screen
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ((MainActivity)getActivity()).setViewPager(7); // go to Q1
            }
        });

        return view;
    }
}
