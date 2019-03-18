package com.example.ecologicalfootprintapp;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GlobalFootprintAPI {
    private final static String OPENWEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private final static String OPENWEATHER_SEARCH_QUERY_PARAM = "q";
    private final static String SEARCH_QUERY_PARAM_TWO = "units";
    private final static String SEARCH_QUERY_PARAM_THREE = "metric";
    private final static String OPENWEATHER_APPID = "APPID";
    private final static String OPENWEATHER_APPID_KEY = "f86d0e7b17193c228947d492315877fd";

    public static URL buildOpenWeatherSearchURL(String city) {
        Uri openweatherSearchUri = Uri.parse(OPENWEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(OPENWEATHER_SEARCH_QUERY_PARAM, city).appendQueryParameter(SEARCH_QUERY_PARAM_TWO, SEARCH_QUERY_PARAM_THREE)
                .appendQueryParameter(OPENWEATHER_APPID, OPENWEATHER_APPID_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(openweatherSearchUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

        public static  class SearchResult implements Serializable {
            public static final String EXTRA_SEARCH_RESULT = "OpenWeatherUtils.SearchResult";
            public String DT;
            public double mainTemp;
            public String mainWeather;
            public String display;

        }

        public static ArrayList<SearchResult> parseOpenWeatherSearchResultsJSON(String searchResultsJSON) {
            try {
                JSONObject searchResultsObj = new JSONObject(searchResultsJSON);
                JSONArray searchResultsItems = searchResultsObj.getJSONArray("list");

                ArrayList<SearchResult> searchResultsList = new ArrayList<SearchResult>();
                for (int i = 0; i < searchResultsItems.length(); i++) {
                    SearchResult searchResult = new SearchResult();
                    JSONObject searchResultItem = searchResultsItems.getJSONObject(i);
                    searchResult.DT = searchResultItem.getString("dt_txt");
                    JSONObject main = searchResultItem.getJSONObject("main");
                    searchResult.mainTemp = main.getDouble("temp");

                    JSONArray weatherArray = searchResultItem.getJSONArray("weather");
                    for(int j = 0 ;j < weatherArray.length(); j++){
                        JSONObject weather = weatherArray.getJSONObject(j);
                        searchResult.mainWeather = weather.getString("main");
                    }
                    searchResult.display = searchResult.DT + " " + searchResult.mainTemp + "C" + " " +  searchResult.mainWeather;
                    searchResultsList.add(searchResult);
                }
                return searchResultsList;
            } catch (JSONException e) {
                return null;
            }
        }

    }


