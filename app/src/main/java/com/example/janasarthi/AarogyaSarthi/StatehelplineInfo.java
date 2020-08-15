package com.example.janasarthi.AarogyaSarthi;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

interface Helplineresponse
{
    void HelplineResponseFinish(JSONArray jsonArray) throws JSONException;
}


public class StatehelplineInfo extends AsyncTask<String,Void, JSONArray> {

    Helplineresponse delegate = null;
    @Override
    protected JSONArray doInBackground(String... strings) {

        try
        {
            URL url = new URL("https://api.rootnet.in/covid19-in/contacts");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            StringBuffer stringBuffer = new StringBuffer();
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);
            }
            Log.i("stringbuffer",stringBuffer.toString());
            JSONObject mainjo = new JSONObject(stringBuffer.toString());
            JSONObject dataobj = mainjo.getJSONObject("data");
            JSONObject contactobj = dataobj.getJSONObject("contacts");
            JSONArray ja = contactobj.getJSONArray("regional");
            return ja;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
//        Log.i("statehelpline:",jsonArray.toString());
        try {
            delegate.HelplineResponseFinish(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(jsonArray);
    }
}
