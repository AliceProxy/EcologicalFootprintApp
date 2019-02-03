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
import android.widget.Toast;

public class FragmentHome extends Fragment {
    private static final String TAG = "FragmentHome";

    private Button startQuest;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        startQuest = (Button) view.findViewById(R.id.startSurvey);
        startQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(6);
                Toast.makeText(getActivity(),"Clicked startButton", Toast.LENGTH_SHORT).show();
            }
        });
        // start activity from fragment
        // Intent intent = new Intent(getActivity(), secondActivity.class);
        // startActivity(intent);
        return view;
    }
}
