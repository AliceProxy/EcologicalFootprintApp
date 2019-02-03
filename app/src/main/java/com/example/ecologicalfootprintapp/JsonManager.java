package com.example.ecologicalfootprintapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonManager
{

    public static void writeToFile(File file, String str) throws IOException
    {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(str.getBytes());
        outputStream.close();
    }

    public static void writeToFile(Context context, String fileName, String str)
    {
        try
        {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(str.getBytes(), 0, str.length());
            outputStream.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static String stringFromAsset(Context context, String assetFileName)
    {
        AssetManager assetManager = context.getAssets();
        try
        {
            InputStream inputStream = assetManager.open(assetFileName);
            //String result = JsonManager.stringFromStream(inputStream);
            inputStream.close();
            //return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


}
