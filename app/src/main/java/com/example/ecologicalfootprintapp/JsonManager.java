package com.example.ecologicalfootprintapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.ecologicalfootprintapp.Questionaire;

public class JsonManager
{
    private AssetManager manager;

    // creating a class that will hold functions for editing JSON from anywhere in the App
    public JsonManager(Context activityContext)
    {
        // manager can be used in any function added to this class in place of the context getActivity()
        manager = activityContext.getAssets();
    }

    // takes a filename and a string of the value in the json file
    public String getJson(String filename, String key)
    {
        try
        {
            JSONObject jsonObj = JArrayFromFile(filename, manager).getJSONObject(0);
            return jsonObj.getString(key);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("in get json","asd");
        }
        return "";
    }

    public void setJson(String filename, String key, String value)
    {

        JSONObject jsonObj = null;
        try
        {
            jsonObj = JArrayFromFile(filename, manager).getJSONObject(0);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        // updating the JSON
        try
        {
            // write the value
            jsonObj.put(key, value);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }


    // takes a filename as a string and returns a JSONArray of its contents
    public static JSONArray JArrayFromFile(String filename, AssetManager mn)
    {
        String json;

        try
        {
            InputStream inStream = mn.open(filename);
            int streamSize = inStream.available();
            // buffer to receive json data
            byte[] buffer = new byte[streamSize];

            // read the data into the buffer
            inStream.read(buffer);
            inStream.close();

            // load the file contents into a string before parsing
            json = new String(buffer, "UTF-8");

            // getting the array of all the JSON objects in our file
            return new JSONArray(json);

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.d("jsonman","io error");
            return null;

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Jsonman", "jsonexception");
            return null;

        }
    }

}
