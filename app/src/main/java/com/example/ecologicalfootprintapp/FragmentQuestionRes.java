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
    private TextView  score;
    public float fs15;
    private static FragmentQuestionRes instance;

    @Override
    public void onStart()
    {
        super.onStart();
        fs15 = ((MainActivity)getActivity()).questionaire.getQ15();
        Log.e("OnStart 15:",""+fs15);
        instance = this;

    }

    public void updateQ15()
    {
        fs15 = ((MainActivity)getActivity()).questionaire.getQ15();
    }

    public static FragmentQuestionRes getInstance() {
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_qres_layout, container, false);
        Log.d(TAG, "onCreateView: started.");

        backButton = (Button) view.findViewById(R.id.prev5);
        homeButton = (Button) view.findViewById(R.id.homeButton);


        Log.e("Reading Answer 15 res",""+((MainActivity)getActivity()).questionaire.getQ15());

        /*This is the SQLlite initialization code which creates a database and tables to store the data
          that we will need to calculate the ecological footprint. The data/values we chose to store correspond
          to the ecological footprint of production & ecological footprint of consumption for different categories
          such as cropland, grazing, carbon, builtupland, forestproduct, fish. This data has been obtained via the
          GlobalFootprint Network's National Footprint Accounts 2018. This data is constant and is utilized in the
          calculation of an individual's footprint score by fetching it from the database.
         */
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


        //test = (TextView) view.findViewById(R.id.test);
       // test.setText("Test: "+pCropland+"Test: "+cCarbon+"Test: "+eqfBuiltUpLand);



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
        fs15 = ((MainActivity)getActivity()).questionaire.getQ15();

        score = (TextView) view.findViewById(R.id.scoreText);

        if(     fs1 == 0 ||
                fs2 == 0 ||
                fs3 == 0 ||
                fs4 == 0 ||
                fs5 == 0 ||
                fs6 == 0 ||
                fs7 == 0 ||
                fs8 == 0 ||
                fs9 == 0 ||
                fs10 == 0 ||
                fs11 == 0 ||
                fs12 == 0 ||
                fs13 == 0 ||
                fs14 == 0 ||
                fs15 == 0
        )
        {
            score.setText("Cant calculate score until all questions are answered");
            ((MainActivity)getActivity()).questionaire.setCompleted(false);

        }
        else {

            ((MainActivity)getActivity()).questionaire.setCompleted(true);



        /*This section of code calculates an individual's footprint score.
          The formula being used here is the (ecological footprint of consumption /
          ecological footprint of production * the user's value(input) * equivalence factor.
          The equivalence factor is a scaling factor that is applied for different countries. We
          obtained this value from the national footprint accounts for the United States.

         */
            float answer1 = ((cGrazing / pGrazing) * fs1) * eqfGrazing;
            float answer2 = ((cCropland / pCropland) * fs2) * eqfCropland;
            float answer3 = ((cBuiltUpLand / pBuiltUpLand) * fs3) * eqfBuiltUpLand;
            float answer4 = ((cForestProduct / pForestProduct) * fs4) * eqfForestProduct;
            float answer5 = ((cBuiltUpLand / pBuiltUpLand) * fs5) * eqfBuiltUpLand;
            float answer6 = ((cBuiltUpLand / pBuiltUpLand) * fs6) * eqfBuiltUpLand;
            float answer7 = ((cCarbon / pCarbon) * fs7) * eqfCarbon;
            float answer8 = ((cCarbon / pCarbon) * fs8) * eqfCarbon;
            float answer9 = ((cCarbon / pCarbon) * fs9) * eqfCarbon;
            float answer10 = ((cCarbon / pCarbon) * fs10) * eqfCarbon;
            float answer11 = ((cCarbon / pCarbon) * fs11) * eqfCarbon;
            float answer12 = ((cCarbon / pCarbon) * fs12) * eqfCarbon;
            float answer13 = ((cCarbon / pCarbon) * fs13) * eqfCarbon;
            float answer14 = ((cCarbon / pCarbon) * fs14) * eqfCarbon;
            float answer15 = ((cCarbon / pCarbon) * fs15) * eqfCarbon;

            double carbonImpact = answer7 + answer8 + answer9 + answer10 + answer11 + answer12 + answer13 + answer14 + answer15;
            double cropImpact = answer2;
            double livestockImpact = answer1;
            double landUseImpact = answer3 + answer5 + answer6;
            double forestImpact = answer4;


        /*float answer1 = (fs1/pGrazing)*eqfGrazing;
        float answer2 = (fs2/pCropland)*eqfCropland;
        float answer3 = (fs3/pBuiltUpLand)*eqfBuiltUpLand;
        float answer4 = (fs4/pForestProduct)*eqfForestProduct;
        float answer5 = (fs5/pBuiltUpLand)*eqfBuiltUpLand;
        float answer6 = (fs6/pBuiltUpLand)*eqfBuiltUpLand;
        float answer7 = (fs7/pCarbon)*eqfCarbon;
        float answer8 = (fs8/pCarbon)*eqfCarbon;
        float answer9 = (fs9/pCarbon)*eqfCarbon;
        float answer10 = (fs10/pCarbon)*eqfCarbon;
        float answer11 = (fs11/pCarbon)*eqfCarbon;
        float answer12 = (fs12/pCarbon)*eqfCarbon;
        float answer13 = (fs13/pCarbon)*eqfCarbon;
        float answer14 = (fs14/pCarbon)*eqfCarbon;
        float answer15 = (fs15/pCarbon)*eqfCarbon;*/


            float finalScore = answer1 + answer2 + answer3 + answer4 + answer5 +
                    answer6 + answer7 + answer8 + answer9 + answer10 +
                    answer11 + answer12 + answer13 + answer14 + answer15;

            Log.e("Answer 1", ""+answer1);
            Log.e("Answer 2", ""+answer2);
            Log.e("Answer 3", ""+answer3);
            Log.e("Answer 4", ""+answer4);
            Log.e("Answer 5", ""+answer5);
            Log.e("Answer 6", ""+answer6);
            Log.e("Answer 7", ""+answer7);
            Log.e("Answer 8", ""+answer8);
            Log.e("Answer 9", ""+answer9);
            Log.e("Answer 10", ""+answer10);
            Log.e("Answer 11", ""+answer11);
            Log.e("Answer 12", ""+answer12);
            Log.e("Answer 13", ""+answer13);
            Log.e("Answer 14", ""+answer14);
            Log.e("Answer 15", ""+answer15);

            score = (TextView) view.findViewById(R.id.scoreText);
            DecimalFormat df = new DecimalFormat("0.00");
            score.setText("Your Score Is: " + df.format(finalScore));
            ((MainActivity) getActivity()).questionaire.setScore(finalScore);
            ((MainActivity) getActivity()).questionaire.setCarbon((float) carbonImpact);
            ((MainActivity) getActivity()).questionaire.setCropLand((float) cropImpact);
            ((MainActivity) getActivity()).questionaire.setForestProducts((float) forestImpact);
            ((MainActivity) getActivity()).questionaire.setLandUse((float) landUseImpact);
            ((MainActivity) getActivity()).questionaire.setLivestock((float) livestockImpact);


        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(19);
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
