package com.example.ecologicalfootprintapp.data;

import java.util.List;

public class Scores {
    private List<Score> scores;

    public void addScore(double score)
    {
        scores.add(new Score(""+score));
    }

    public List<Score> getScores()
    {
        return scores;
    }
}
