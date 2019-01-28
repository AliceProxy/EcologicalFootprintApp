package com.example.ecologicalfootprintapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAbout extends Fragment {
    private static final String TAG = "FragmentAbout";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_about_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        return view;

        //
        //move fragments from a fragment:
        // ((MainActivity)getActivity())  <-- gives access to any method inside main activity (ie. setViewPager)
        //
    }
}
