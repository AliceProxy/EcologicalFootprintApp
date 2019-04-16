package com.example.ecologicalfootprintapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class FragmentHome extends Fragment
{
    private static final String TAG = "FragmentHome";

    private Button startQuest, resetAnswers;
    private TextView completedText, titleText;

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

        // setting up onclick listener
        resetAnswers = (Button) view.findViewById(R.id.resetAnswers);
        resetAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ((MainActivity)getActivity()).questionaire.resetAll();
                Toast.makeText(getActivity(),"Answers have been reset", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);

            }
        });

        // get the value from the questionaire class to see if the questionaire has been completed or not
        // changes the text to reflect to let the user know
        completedText = view.findViewById(R.id.completedText);
        titleText = view.findViewById(R.id.titleText);


        if(((MainActivity)getActivity()).questionaire.getCompleted()) // using this method to access the questionaire object on the main activity
        {
            completedText.setText("You have completed the questionaire");
            DecimalFormat df = new DecimalFormat("0.00");
            titleText.setText("Your Score Is: "+df.format(((MainActivity)getActivity()).questionaire.getScore()));
            resetAnswers.setEnabled(true);
            resetAnswers.setVisibility(View.VISIBLE);
            startQuest.setText("Re-Take Survey");
        }
        else
        {
           completedText.setText("You have not completed the questionaire yet, please press the button below to begin");
            titleText.setText("Calculate Your Score: ");
            resetAnswers.setEnabled(false);
            resetAnswers.setVisibility(View.GONE);
            startQuest.setText("Take The Survey");
        }

        return view;
    }
}
