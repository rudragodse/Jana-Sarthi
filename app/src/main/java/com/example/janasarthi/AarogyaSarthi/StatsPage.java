package com.example.janasarthi.AarogyaSarthi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.janasarthi.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class StatsPage extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener,AsyncResponse,LineChartAsyncResponse {

    public static TextView card_title;
    public static JSONArray apidata;
    String dataCountry;
    JSONArray apidataarray;
    private BarChart barChart;
    public ArrayList<Entry> yvalues;
    String[] dates;
    Integer[] cases;
    ArrayList<BarEntry> infospread;
    String newurl = "https://api.covid19api.com/live/country/india/status/confirmed";
    LineChart lineChart;
    Integer[]Deaths;
    Integer[]Recovered;
    Integer[]ConfirmedCases;
    String[]datesforlinechart;
    public ArrayList<Entry> yvaluesforRecovered,yvaluesforDeathcount;
    MaterialButton start_date_btn,end_date_btn;
    DatePickerDialog.OnDateSetListener start_date_listener,end_date_listener;
    String startdate="",enddate="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);
        Objects.requireNonNull(getSupportActionBar()).hide();

        card_title = findViewById(R.id.card_title);

        barChart = findViewById(R.id.infection_spreadBarChart);
        lineChart = findViewById(R.id.comparisonChart);
        start_date_btn = findViewById(R.id.start_date_btn);
        end_date_btn = findViewById(R.id.end_date_btn);

        start_date_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        StatsPage.this,
                        start_date_listener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        end_date_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(
                        StatsPage.this,
                        end_date_listener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog1.show();
            }
        });

        start_date_listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int propermonth= month+1;
                String fm=""+propermonth;
                String fd=""+dayOfMonth;
                if(month<10){
                    fm ="0"+propermonth;
                }
                if (dayOfMonth<10){
                    fd="0"+dayOfMonth;
                }
                startdate= ""+year+"-"+fm+"-"+fd;
                Log.i("startDate",startdate);

            }
        };

        end_date_listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int propermonth= month+1;
                String fm=""+propermonth;
                String fd=""+dayOfMonth;
                Log.i("month",String.valueOf(month));
                if(month<10){
                    fm ="0"+propermonth;
                }
                if (dayOfMonth<10){
                    fd="0"+dayOfMonth;
                }
                enddate= ""+year+"-"+fm+"-"+fd;
//                Log.d("tag",""+date);
//                String endDate = year+"-"+ month+"-"+dayOfMonth;
                Log.i("EndDate",enddate);
                Log.i("startdateval",startdate);
                Log.i("url","https://api.covid19api.com/country/India/status/confirmed/live?from="+startdate+"T00:00:00Z&to="+enddate+"T00:00:00Z");
                card_title.setText("Infection Spread of Covid 19 from"+ " "+startdate+" "+"to"+" "+enddate);
                FetchData fetchDatafromdate = new FetchData();
                fetchDatafromdate.execute("https://api.covid19api.com/country/India/status/confirmed/live?from="+startdate+"T00:00:00Z&to="+enddate+"T00:00:00Z");
                fetchDatafromdate.delegate=StatsPage.this;



            }
        };






        FetchData fetchData = new FetchData();
        fetchData.delegate = this;
        card_title.setText("Infection Spread of Covid 19 from 2020-06-02 to 2020-06-03");
        fetchData.execute("https://api.covid19api.com/country/India/status/confirmed/live?from=2020-06-02T00:00:00Z&to=2020-06-03T00:00:00Z");



        FetchLinechartData fetchLinechartData = new FetchLinechartData();
        fetchLinechartData.delegate = this;
        fetchLinechartData.execute("https://api.covid19api.com/live/country/India/status/confirmed/date/2020-04-21T00:00:00Z");


        infospread = new ArrayList<>();
        yvalues = new ArrayList<>();
        yvaluesforRecovered = new ArrayList<>();
        yvaluesforDeathcount = new ArrayList<>();

    }

    @Override
    public void processFinish(JSONArray jsonArray) {

        Log.i("JSON FROM FETCHED DATA",jsonArray.toString());
        dates = new String[jsonArray.length()];
        cases = new Integer[jsonArray.length()];// Json has been fetched properly:
        for(int i=0; i<jsonArray.length();i++)
        {
            try{
                JSONObject infoObject = jsonArray.getJSONObject(i);
                dates[i] = infoObject.getString("Date");
                cases[i] = infoObject.getInt("Cases");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        for(int i=0;i<dates.length;i++)
        {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            try {
                Date date = simpleDateFormat.parse(dates[i]);
                dates[i] = simpleDateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


//            String actual_date = dates[i].substring(0,10);
//            dates[i] = actual_date;
            Log.i("Data format:",dates[i]);
            infospread.add(new BarEntry(i,cases[i]));

            IndexAxisValueFormatter indexAxisValueFormatter = new IndexAxisValueFormatter(){
                @Override
                public String getFormattedValue(float value) {
                    return dates[(int)value];
                }

            };
            XAxis xAxis = barChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(indexAxisValueFormatter);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        }
        BarDataSet set = new BarDataSet(infospread,"No of Confirmed Covid 19 cases");
        set.setColor(getResources().getColor(R.color.red));
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);
        barChart.setData(data);
        barChart.setFitBars(true);
        Legend legend =barChart.getLegend();
        legend.setTextSize(14f);
        legend.setYEntrySpace(40f);
        barChart.invalidate();

    }

    @Override
    public void successfulDataRetreival(JSONArray jsonArray) {

        Log.i("LineChartApiData:",jsonArray.toString());
        Deaths = new Integer[jsonArray.length()];
        Recovered = new Integer[jsonArray.length()];
        ConfirmedCases=new Integer[jsonArray.length()];
        datesforlinechart = new String[jsonArray.length()];

        for(int i=0;i<jsonArray.length();i++)
        {
            try{
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Deaths[i] = jsonObject.getInt("Deaths");
                Recovered[i]=jsonObject.getInt("Recovered");
                ConfirmedCases[i]=jsonObject.getInt("Confirmed");
                datesforlinechart[i] = jsonObject.getString("Date");

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for(int i=0;i<datesforlinechart.length;i++)
        {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
            try {
                Date date = simpleDateFormat.parse(datesforlinechart[i]);
                datesforlinechart[i] = simpleDateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


//            String actual_date = dates[i].substring(0,10);
//            dates[i] = actual_date;
            Log.i("Data format:",datesforlinechart[i]);

            IndexAxisValueFormatter indexAxisValueFormatter = new IndexAxisValueFormatter(){
                @Override
                public String getFormattedValue(float value) {
                    return datesforlinechart[(int)value];
                }

            };
            XAxis  xAxis = lineChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(indexAxisValueFormatter);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        }



        for (int i=0;i<ConfirmedCases.length;i++)
        {
            yvalues.add(new Entry(i,ConfirmedCases[i]));
            yvaluesforRecovered.add(new Entry(i,Recovered[i]));
            yvaluesforDeathcount.add(new Entry(i,Deaths[i]));

        }






        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet RecoveredDataset = new LineDataSet(yvaluesforRecovered,"Recovered Cases");
        RecoveredDataset.setColor(getResources().getColor(R.color.colorPrimary));
        RecoveredDataset.setLineWidth(4.5f);
        RecoveredDataset.setValueTextSize(8f);

        LineDataSet DeathCountDataset = new LineDataSet(yvaluesforDeathcount,"Deaths Count");
        DeathCountDataset.setColor(getResources().getColor(R.color.greyishblack));
        DeathCountDataset.setLineWidth(4.5f);
        DeathCountDataset.setValueTextSize(8f);

        lineDataSets.add(RecoveredDataset);
        lineDataSets.add(DeathCountDataset);


        lineChart.setData(new LineData(lineDataSets));
        Legend legend = lineChart.getLegend();
        legend.setTextSize(14f);
        legend.setXEntrySpace(40);
        legend.setYEntrySpace(40f);
        lineChart.invalidate();




    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}