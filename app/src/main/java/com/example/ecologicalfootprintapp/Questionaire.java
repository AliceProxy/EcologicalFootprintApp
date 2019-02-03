package com.example.ecologicalfootprintapp;

import android.view.View;

public class Questionaire {
    private boolean completed;
    private String question1, question2, question3, question4, question5;

    public Questionaire()
    {
        this.completed = false;
        this.question1 = "Select an option";
        this.question2 = "Select an option";
        this.question3 = "Select an option";
        this.question4 = "Select an option";
        this.question5 = "Select an option";
    }

    public Questionaire(String question1, String question2, String question3, String question4, String question5)
    {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
    }

    public Questionaire(boolean completed, String question1, String question2, String question3, String question4, String question5)
    {
        this.completed = completed;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
    }

    public String getQ1()
    {
        return question1;
    }
    public String getQ2()
    {
        return question2;
    }
    public String getQ3()
    {
        return question3;
    }
    public String getQ4()
    {
        return question4;
    }
    public String getQ5()
    {
        return question5;
    }
    public String toJSONstring()
    {
        return "{ \"Q1\": \"Select an option\", \n" +
                "{ \"Q2\": \"Select an option\", \n" +
                "{ \"Q3\": \"Select an option\", \n" +
                "{ \"Q4\": \"Select an option\", \n" +
                "{ \"Q5\": \"Select an option\"}";
    }
    public void writeJson(View view)
    {

    }

    public void readFromJSON()
    {

    }



}
