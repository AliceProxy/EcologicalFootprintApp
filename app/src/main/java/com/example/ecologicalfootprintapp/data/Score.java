package com.example.ecologicalfootprintapp.data;

public class Score {
    private String score;

    public Score(String val)
    {
        score = val;
    }

    public double getScore()
    {
        return Double.parseDouble(score);
    }

    public void setScore(double val)
    {
        score = ""+val;
    }
}
