package com.example.ecologicalfootprintapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.example.ecologicalfootprintapp.Questionaire;

public class JsonManager
{
    private Context context;

    // creating a class that will hold functions for editing JSON from anywhere in the App
    public JsonManager(Context activityContext)
    {
        // manager can be used in any function added to this class in place of the context getActivity()
        context = activityContext;
        //validateFile("Questionaire.json", activityContext);
        // uncomment the previous line to reset the stored contents of questionaire
    }

    // takes a filename and a string of the value in the json file
    public String getJson(String filename, String key) {

        // getting a JSONArray for the contents of the specified file name
        JSONArray jArray = JArrayFromFile(filename, context);
        try
        {
            if(jArray != null)
                return jArray.getJSONObject(0).getString(key);
            else {
                validateFile(filename, context);
                return jArray.getJSONObject(0).getString(key);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    // helper function that can set a single value within the JSON data, takes filename, the key to change, and the desired value
    public void setJson(String filename, String key, String value)
    {
        try
        {
            // using helper function to get the JSONArray
            JSONArray jArray = JArrayFromFile(filename, context);
            // this array is only one entry
            jArray.getJSONObject(0).put(key, value);
            String jsonString = jArray.toString();
            // after modification, write to file to save
            OutputStreamWriter outStream = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outStream.write(jsonString);
            outStream.close();
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void addScore(double score)
    {
        List<Double> scores = getScore();
        JSONArray jArray = JArrayFromFile("Questionaire.json", context);
        try
        {
           jArray.getJSONObject(jArray.length()).put("score", score);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public List<Double> getScore()
    {
        List<Double> scores = new ArrayList<Double>();
        // getting a JSONArray for the contents of the specified file name
        JSONArray jArray = JArrayFromFile("Questionaire.json", context);
        try
        {
            if(jArray != null)
                for(int i = 1; i < jArray.length(); i++)
                {
                    scores.add(jArray.getJSONObject(i).getDouble("score"));
                }

        } catch (JSONException e)
        {
            e.printStackTrace();

        }
        return scores;
    }


    // takes a filename as a string and returns a JSONArray of its contents
    public JSONArray JArrayFromFile(String filename, Context context)
    {
        // string to be returned
        String ret = "";
        try
        {
            InputStream inStream = context.openFileInput(filename);

            if(inStream != null)
            {
                // read all contents into the string builder
                InputStreamReader inReader = new InputStreamReader(inStream);
                BufferedReader bufferedReader = new BufferedReader(inReader);
                String outputString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((outputString = bufferedReader.readLine()) != null)
                    stringBuilder.append(outputString);

                inStream.close();
                ret = stringBuilder.toString();

                // attempt to create a JSONArray from the stringbuilder
                try { return new JSONArray(ret); }
                catch (JSONException e1) { e1.printStackTrace(); }
            }
        }
        catch (FileNotFoundException e) { Log.e("login activity", "File not found: " + e.toString()); }
        catch (IOException e) { Log.e("login activity", "Can not read file: " + e.toString()); }

        return null;
    }

    // function that copies the read-only initial state of Questionaire.json and coppies the initial state to the wrtiable version that will be stored in internal storage
    public void validateFile(String filename, Context context)
    {
        String json;
        try
        {
            AssetManager manager = context.getAssets();
            InputStream inStream = manager.open(filename);
            int streamSize = inStream.available();
            // buffer to receive json data
            byte[] buffer = new byte[streamSize];

            // read the data into the buffer
            inStream.read(buffer);
            inStream.close();

            // load the file contents into a string before parsing
            json = new String(buffer, "UTF-8");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.d("jsonman","io error");
        }


    }


}
