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

import com.example.ecologicalfootprintapp.Questionaire;

public class JsonManager
{
    private Context context;

    // creating a class that will hold functions for editing JSON from anywhere in the App
    public JsonManager(Context activityContext)
    {
        // manager can be used in any function added to this class in place of the context getActivity()
        context = activityContext;
    }

    // takes a filename and a string of the value in the json file
    public String getJson(String filename, String key) {
        /*
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
        */
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

    public void setJson(String filename, String key, String value)
    {
        /*
        Log.d("in get json","SET JSON CALLED");
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
        */
        try
        {
            JSONArray jArray = JArrayFromFile(filename, context);
            jArray.getJSONObject(0).put(key, value);
            String jsonString = jArray.toString();
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


    // takes a filename as a string and returns a JSONArray of its contents
    public JSONArray JArrayFromFile(String filename, Context context)
    {
        String ret = "";
        try
        {
            InputStream inStream = context.openFileInput(filename);

            if(inStream != null)
            {
                InputStreamReader inReader = new InputStreamReader(inStream);
                BufferedReader bufferedReader = new BufferedReader(inReader);
                String outputString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((outputString = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(outputString);
                }

                inStream.close();
                ret = stringBuilder.toString();

                try
                {
                    return new JSONArray(ret);
                }
                catch (JSONException e1)
                {
                    e1.printStackTrace();
                }

            }
        }
        catch (FileNotFoundException e)
        {
            Log.e("login activity", "File not found: " + e.toString());
        }
        catch (IOException e)
        {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

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
