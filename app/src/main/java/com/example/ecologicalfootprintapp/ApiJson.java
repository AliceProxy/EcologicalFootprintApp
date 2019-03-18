package com.example.ecologicalfootprintapp;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ApiJson extends AsyncTask<String, String, String>
{

    public ProgressDialog progDiag;
    public TextView resultText;

    public ApiJson(ProgressDialog pd, TextView tv)
    {
        progDiag = pd;
        resultText = tv;
    }

    public void onPreExecute()
    {
        super.onPreExecute();

        progDiag.setMessage("Please wait");
        progDiag.setCancelable(false);
        progDiag.show();
    }

    public String doInBackground(String... params)
    {


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try
        {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null)
            {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
            }

            return buffer.toString();


        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onPostExecute(String result)
    {
        super.onPostExecute(result);
        if (progDiag.isShowing())
        {
            progDiag.dismiss();
        }
        resultText.setText(result);
    }
}