package com.example.ecologicalfootprintapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openDatabase;

public class FragmentQuestionRes extends Fragment {
    private static final String TAG = "FragmentRes";

    private Button backButton, homeButton;
    private TextView res1, res2, res3, res4, res5, res6, res7, res8, res9, res10, res11, res12, res13, res14, res15, score, test;



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
        res6 = (TextView) view.findViewById(R.id.response6) ;
        res7 = (TextView) view.findViewById(R.id.response7) ;
        res8 = (TextView) view.findViewById(R.id.response8) ;
        res9 = (TextView) view.findViewById(R.id.response9) ;
        res10 = (TextView) view.findViewById(R.id.response10) ;
        res11 = (TextView) view.findViewById(R.id.response11) ;
        res12 = (TextView) view.findViewById(R.id.response12) ;
        res13 = (TextView) view.findViewById(R.id.response13) ;
        res14 = (TextView) view.findViewById(R.id.response14) ;
        res15 = (TextView) view.findViewById(R.id.response15) ;

        res1.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ1());
        res2.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ2());
        res3.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ3());
        res4.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ4());
        res5.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ5());
        res6.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ6());
        res7.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ7());
        res8.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ8());
        res9.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ9());
        res10.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ10());
        res11.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ11());
        res12.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ12());
        res13.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ13());
        res14.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ14());
        res15.setText("Selected Option: "+((MainActivity)getActivity()).questionaire.getQ15());

        ((MainActivity)getActivity()).questionaire.setCompleted(true);


        SQLiteDatabase myDatabase = getActivity().openOrCreateDatabase("FootPrintDB",android.content.Context.MODE_PRIVATE ,null);

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS FootPrintData3(pCropland FLOAT, pGrazing FLOAT, pForestProduct FLOAT, pCarbon FLOAT, pFish FLOAT, pBuiltUpLand FLOAT, " +
                "cCropland FLOAT, cGrazing FLOAT, cForestProduct FLOAT, cCarbon FLOAT, cFish FLOAT, cBuiltUpLand FLOAT, " +
                "eqfCropland FLOAT, eqfGrazing FLOAT, eqfForestProduct FLOAT, eqfCarbon FLOAT, eqfFish FLOAT, eqfBuiltUpLand FLOAT);");
        myDatabase.execSQL("INSERT INTO FootPrintData3 VALUES(1.3, 0.3, 0.8, 5.7, 0.1, 0.1, 1.0, 0.3, 0.8, 6.0, 0.1, 0.1, 2.39, 1.24, 0.51, 0.41, 2.39, 1.33);");

        Cursor resultSet = myDatabase.rawQuery("Select * from FootPrintData3",null);
        resultSet.moveToFirst();
        float pCropland = resultSet.getFloat(0);
        float pGrazing = resultSet.getFloat(1);
        float pForestProduct = resultSet.getFloat(2);
        float pCarbon = resultSet.getFloat(3);
        float pFish = resultSet.getFloat(4);
        float pBuiltUpLand = resultSet.getFloat(5);

        float cCropland = resultSet.getFloat(6);
        float cGrazing = resultSet.getFloat(7);
        float cForestProduct = resultSet.getFloat(8);
        float cCarbon = resultSet.getFloat(9);
        float cFish = resultSet.getFloat(10);
        float cBuiltUpLand = resultSet.getFloat(11);

        float eqfCropland = resultSet.getFloat(12);
        float eqfGrazing = resultSet.getFloat(13);
        float eqfForestProduct = resultSet.getFloat(14);
        float eqfCarbon = resultSet.getFloat(15);
        float eqfFish = resultSet.getFloat(16);
        float eqfBuiltUpLand = resultSet.getFloat(17);


        test = (TextView) view.findViewById(R.id.test);
        test.setText("Test: "+pCropland+"Test: "+cCarbon+"Test: "+eqfBuiltUpLand);



        float fs1 = ((MainActivity)getActivity()).questionaire.getQ1();
        float fs2 = ((MainActivity)getActivity()).questionaire.getQ2();
        float fs3 = ((MainActivity)getActivity()).questionaire.getQ3();
        float fs4 = ((MainActivity)getActivity()).questionaire.getQ4();
        float fs5 = ((MainActivity)getActivity()).questionaire.getQ5();
        float fs6 = ((MainActivity)getActivity()).questionaire.getQ6();
        float fs7 = ((MainActivity)getActivity()).questionaire.getQ7();
        float fs8 = ((MainActivity)getActivity()).questionaire.getQ8();
        float fs9 = ((MainActivity)getActivity()).questionaire.getQ9();
        float fs10 = ((MainActivity)getActivity()).questionaire.getQ10();
        float fs11 = ((MainActivity)getActivity()).questionaire.getQ11();
        float fs12 = ((MainActivity)getActivity()).questionaire.getQ12();
        float fs13 = ((MainActivity)getActivity()).questionaire.getQ13();
        float fs14 = ((MainActivity)getActivity()).questionaire.getQ14();
        float fs15 = ((MainActivity)getActivity()).questionaire.getQ15();


        float finalScore = (fs1 + fs2 + fs3 + fs4 + fs5 + fs6 + fs7 + fs8
                + fs9 + fs10 + fs11 + fs12 + fs13 + fs14 + fs15) / 121;
        score = (TextView) view.findViewById(R.id.score);
        DecimalFormat df = new DecimalFormat("0.00");
        score.setText("Your Score: "+df.format(finalScore));


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(20);
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
