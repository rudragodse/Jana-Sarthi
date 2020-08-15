package com.example.janasarthi.AarogyaSarthi;

import android.os.AsyncTask;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

interface  AsyncResponse{
    void processFinish(JSONArray jsonArray );
}



public class FetchData extends AsyncTask<String, Void, JSONArray> {
    AsyncResponse delegate = null;

    ArrayList<Entry> mapdatapoints= new ArrayList<>();
    ArrayList<JSONArray>datafromurls = new ArrayList<>();
    String startDate,endDate;
    FetchData()
    {}




    @Override
    protected JSONArray doInBackground(String... strings) {
//        Log.i("New Url from activity:", Arrays.toString(strings));

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer= new StringBuffer();
            String line="";
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            return new JSONArray(stringBuffer.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        return null;


    }

    FetchData(String url)
    {
        super();
    }




//    @Override
//    protected JSONArray doInBackground(String url) {
//
//
//        try {
//            URL url = new URL("https://api.covid19api.com/country/India/status/confirmed/live?from=2020-04-01T00:00:00Z&to=2020-04-07T00:00:00Z");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = urlConnection.getInputStream();
//            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
//            StringBuffer stringBuffer= new StringBuffer();
//            String line="";
//            while ((line=bufferedReader.readLine())!=null)
//            {
//                stringBuffer.append(line);
//            }
//            return new JSONArray(stringBuffer.toString());
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    @Override
    protected void onPostExecute(JSONArray response) {
        Log.i("StringBUffer",response.toString());
        delegate.processFinish(response);
        super.onPostExecute(response);
    }


}

