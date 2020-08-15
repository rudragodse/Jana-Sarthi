package com.example.janasarthi.AarogyaSarthi;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

interface donationsresponse
{
    void donationsresponsefinish(JSONArray jsonArray);
}


public class FetchDonationsInfo extends AsyncTask<String, Void, JSONArray> {
    ArrayList<JSONArray> freefooddonationslist = new ArrayList<>();
    donationsresponse delegate = null;
    String singleparsed="";
    String dataparsed="";
    @Override
    protected JSONArray doInBackground(String... strings) {
        try
        {
            URL url = new URL("https://api.covid19india.org/resources/resources.json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            StringBuffer stringBuffer = new StringBuffer();
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            JSONObject jo = new JSONObject(stringBuffer.toString());
            JSONArray ja = jo.getJSONArray("resources");
            return ja;


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        delegate.donationsresponsefinish(jsonArray);
        super.onPostExecute(jsonArray);
    }
}

