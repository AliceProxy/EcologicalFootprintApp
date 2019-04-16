package com.example.ecologicalfootprintapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;
import com.example.ecologicalfootprintapp.JsonManager;
import com.example.ecologicalfootprintapp.data.Score;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Questionaire
{
    public boolean completed, LinkedAPI;
    public Date lastCompleted;
    // Integers to represent the option number selected for the question, -1 if unselected
    public int question1, question2, question3, question4, question5;

    private AssetManager manager;
    private JsonManager jManager;

    private Context cont;

    // pass in the context on construction to use methods from getAssets();
    public Questionaire(Context activityContext)
    {
        // get and store the assets
        manager = activityContext.getAssets();

        cont = activityContext;

        //initialize the Json Manager
        jManager = new JsonManager(activityContext);

        //jManager.validateFile("Questionaire.json", cont);
        //jManager.addScore(2.45);
        //jManager.addScore(6.45);

        /*
        List<Score> scores = jManager.getScore();

        for(int i = 0; i < scores.size(); i++)
        {
            Log.e("scores i", ""+scores.get(i).getScore());
        }

        jManager.addScore(6.2);
        jManager.addScore(4.13);

        scores = jManager.getScore();

        for(int i = 0; i < scores.size(); i++)
        {
            Log.e("scores i 2", ""+scores.get(i).getScore());
        }

        //jManager.addScore(2.0);
        */
    }


    public List<Score> getScores()
    {
        return jManager.getScore();
    }

    public void addScore(double score)
    {
        jManager.addScore(score);
    }




    // returns the completed status boolean
    public boolean getCompleted() { return jManager.getJson("Questionaire.json","Completed").equals("true"); }

    public float getLandUseInfluence() { return Float.parseFloat(jManager.getJson("Questionaire.json","LandUseInfluence")); }
    public float getCarbonInfluence() { return Float.parseFloat(jManager.getJson("Questionaire.json","CarbonInfluence")); }
    public float getForestProductInfluence() { return Float.parseFloat(jManager.getJson("Questionaire.json","ForestProductInfluence")); }
    public float getLivestockInfluence() { return Float.parseFloat(jManager.getJson("Questionaire.json","LivestockInfluence")); }
    public float getCropLandInfluence() { return Float.parseFloat(jManager.getJson("Questionaire.json","CropLandInfluence")); }
    public float getScore() { return Float.parseFloat(jManager.getJson("Questionaire.json","TotalScore")); }

    public void setScore(float val) { jManager.setJson("Questionaire.json", "TotalScore", ""+val); }
    public void setCropLand(float val) { jManager.setJson("Questionaire.json", "CropLandInfluence", ""+val); }
    public void setLivestock(float val) { jManager.setJson("Questionaire.json", "LivestockInfluence", ""+val); }
    public void setCarbon(float val) { jManager.setJson("Questionaire.json", "CarbonInfluence", ""+val); }
    public void setForestProducts(float val) { jManager.setJson("Questionaire.json", "ForestProductInfluence", ""+val); }
    public void setLandUse(float val) { jManager.setJson("Questionaire.json", "LandUseInfluence", ""+val); }

    public boolean getAPI() { return jManager.getJson("Questionaire.json","ApiLinked").equals("true"); }


    // returns a date object created the last time the questionaire was updated, returns null if error
    public Date getLastCompleted()
    {
        // only bother checking the last completed date if it has been completed
        if(this.getCompleted())
        {
            // need the formatter to convert from string to Date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try
            {
                return format.parse(jManager.getJson("Questionaire.json", "lastCompleted"));

            } catch (ParseException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    // returns an integer symbolizing the option selected for question 1
    public int getQ1() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q1")); }

    // returns an integer symbolizing the option selected for question 2
    public int getQ2() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q2")); }

    // returns an integer symbolizing the option selected for question 3
    public int getQ3() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q3")); }

    // returns an integer symbolizing the option selected for question 4
    public int getQ4() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q4")); }

    // returns an integer symbolizing the option selected for question 5
    public int getQ5() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q5")); }

    // returns an integer symbolizing the option selected for question 6
    public int getQ6() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q6")); }

    // returns an integer symbolizing the option selected for question 7
    public int getQ7() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q7")); }

    // returns an integer symbolizing the option selected for question 8
    public int getQ8() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q8")); }

    // returns an integer symbolizing the option selected for question 9
    public int getQ9() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q9")); }

    // returns an integer symbolizing the option selected for question 10
    public int getQ10() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q10")); }

    // returns an integer symbolizing the option selected for question 11
    public int getQ11() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q11")); }

    // returns an integer symbolizing the option selected for question 12
    public int getQ12() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q12")); }

    // returns an integer symbolizing the option selected for question 13
    public int getQ13() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q13")); }

    // returns an integer symbolizing the option selected for question 14
    public int getQ14() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q14")); }

    // returns an integer symbolizing the option selected for question 15
    public int getQ15() { return Integer.parseInt(jManager.getJson("Questionaire.json","Q15")); }


    public void setCompleted(boolean status) { jManager.setJson("Questionaire.json", "Completed", ""+status); }

    public void setAPI(boolean status) { jManager.setJson("Questionaire.json", "ApiLinked", ""+status); }

    public void setLast() { jManager.setJson("Questionaire.json", "LastCompleted", Calendar.getInstance().getTime().toString()); }


    public void setQ1(int q1) { jManager.setJson("Questionaire.json", "Q1", ""+q1); }


    public void setQ2(int q2) { jManager.setJson("Questionaire.json", "Q2", (""+q2)); }


    public void setQ3(int q3) { jManager.setJson("Questionaire.json", "Q3", (""+q3)); }


    public void setQ4(int q4) { jManager.setJson("Questionaire.json", "Q4", (""+q4)); }


    public void setQ5(int q5) { jManager.setJson("Questionaire.json", "Q5", (""+q5)); }

    public void setQ6(int q6) { jManager.setJson("Questionaire.json", "Q6", (""+q6)); }

    public void setQ7(int q7) { jManager.setJson("Questionaire.json", "Q7", (""+q7)); }

    public void setQ8(int q8) { jManager.setJson("Questionaire.json", "Q8", (""+q8)); }

    public void setQ9(int q9) { jManager.setJson("Questionaire.json", "Q9", (""+q9)); }

    public void setQ10(int q10) { jManager.setJson("Questionaire.json", "Q10", (""+q10)); }

    public void setQ11(int q11) { jManager.setJson("Questionaire.json", "Q11", (""+q11)); }

    public void setQ12(int q12) { jManager.setJson("Questionaire.json", "Q12", (""+q12)); }

    public void setQ13(int q13) { jManager.setJson("Questionaire.json", "Q13", (""+q13)); }

    public void setQ14(int q14) { jManager.setJson("Questionaire.json", "Q14", (""+q14)); }

    public void setQ15(int q15) { jManager.setJson("Questionaire.json", "Q15", (""+q15)); }

    public void resetAll(){
        jManager.validateFile("Questionaire.json", cont);
    }
}
