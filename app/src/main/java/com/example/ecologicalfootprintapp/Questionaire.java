package com.example.ecologicalfootprintapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.View;
import com.example.ecologicalfootprintapp.JsonManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Questionaire
{
    public boolean completed;
    public Date lastCompleted;
    // Integers to represent the option number selected for the question, -1 if unselected
    public int question1, question2, question3, question4, question5;

    private AssetManager manager;
    private JsonManager jManager;

    // pass in the context on construction to use methods from getAssets();
    public Questionaire(Context activityContext)
    {
        // get and store the assets
        manager = activityContext.getAssets();

        //initialize the Json Manager
        jManager = new JsonManager(activityContext);
    }


    // returns the completed status boolean
    public boolean getCompleted() { return jManager.getJson("Questionaire.json","Completed").equals("true"); }


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


    public void setCompleted(boolean status) { jManager.setJson("Questionaire.json", "Completed", ""+status); }


    public void setLast() { jManager.setJson("Questionaire.json", "LastCompleted", Calendar.getInstance().getTime().toString()); }


    public void setQ1(int q1) { jManager.setJson("Questionaire.json", "Q1", ""+q1); }


    public void setQ2(int q2) { jManager.setJson("Questionaire.json", "Q2", (""+q2)); }


    public void setQ3(int q3) { jManager.setJson("Questionaire.json", "Q3", (""+q3)); }


    public void setQ4(int q4) { jManager.setJson("Questionaire.json", "Q4", (""+q4)); }


    public void setQ5(int q5) { jManager.setJson("Questionaire.json", "Q5", (""+q5)); }


}
