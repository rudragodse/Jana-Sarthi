package com.example.janasarthi.AarogyaSarthi;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

interface LineChartAsyncResponse{
    void successfulDataRetreival(JSONArray jsonArray);
}

public class FetchLinechartData extends AsyncTask<String,Void, JSONArray> {
    public LineChartAsyncResponse delegate = null;
    @Override
    protected JSONArray doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer= new StringBuffer();
            String line ="";
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            return new JSONArray(stringBuffer.toString());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        delegate.successfulDataRetreival(jsonArray);
        super.onPostExecute(jsonArray);

    }
}

