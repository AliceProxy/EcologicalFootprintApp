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
import android.widget.TextView;
import android.widget.Toast;

public class FragmentHome extends Fragment
{
    private static final String TAG = "FragmentHome";

    private Button startQuest;
    private TextView completedText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        Log.d(TAG, "onCreateView: started.");


        // setting up onclick listener
        startQuest = (Button) view.findViewById(R.id.startSurvey);
        startQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(6);
                Toast.makeText(getActivity(),"Clicked startButton", Toast.LENGTH_SHORT).show();
            }
        });

        // get the value from the questionaire class to see if the questionaire has been completed or not
        // changes the text to reflect to let the user know
        completedText = view.findViewById(R.id.completedText);


        if(((MainActivity)getActivity()).questionaire.getCompleted()) // using this method to access the questionaire object on the main activity
        {
            completedText.setText("You have completed the questionaire");
            startQuest.setText("Re-Take Survey");
        }
        else
        {
           completedText.setText("You have not completed the questionaire yet, please press the button below to begin");
            startQuest.setText("Take The Survey");
        }

        return view;
    }
}
