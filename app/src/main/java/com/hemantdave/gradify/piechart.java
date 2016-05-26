package com.hemantdave.gradify;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class piechart extends AppCompatActivity {

    private PieChart mChart;
    RelativeLayout container;
    String CourseName;
    float[]  yData;

    String[] xdata = {"A", "B", "C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_piechart);
        CourseName = getIntent().getStringExtra("courseName");
        String result = "";
        CourseName = CourseName.replace(" ", "");

        dataHandler dataDownload = new dataHandler();
        String URL = "http://136.176.45.136:8080/book/book/graph/" + CourseName;

        try {
            result = dataDownload.execute(URL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        String[] dataString = result.replaceAll("\\[", "").replaceAll("\\]", "").split(",");


        ArrayList<String> pidata=new ArrayList<>();
        pidata.addAll(Arrays.asList(dataString));
       yData = new float[3];


        for (int i = 0; i < dataString.length; i++) {
            try {
                yData[i] = Float.parseFloat(pidata.get(i));
                System.out.println(yData[i]);
            } catch (NumberFormatException nfe) {};
        }



        mChart = (PieChart) findViewById(R.id.chart1);




       // container.setBackgroundColor(Color.parseColor("#448AFF"));

        mChart.setUsePercentValues(true);
        mChart.setDescription("Total Student Grade Predictions");
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        mChart.setHoleRadius(7f);
        mChart.setTransparentCircleAlpha(60);
        mChart.setTransparentCircleColor(Color.parseColor("#448AFF"));
        mChart.setTransparentCircleRadius(10);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int i, Highlight highlight) {
                if (e == null) {
                    return;
                }
            }

            @Override
            public void onNothingSelected() {

            }


        });
        addData();
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }
        private void addData() {
            ArrayList<Entry> yval = new ArrayList<>();
            for (int i = 0; i < yData.length; i++) {
                yval.add(new Entry(yData[i], i));

            }
            ArrayList<String> xval = new ArrayList<>();
            for (int i = 0; i < xdata.length; i++) {
                xval.add(xdata[i]);

            }
            PieDataSet dataset=new PieDataSet(yval,"Student Grade Distribution");
            dataset.setSliceSpace(3);
            dataset.setSelectionShift(5);
            ArrayList<Integer>colors=new ArrayList<>();

                for(int c: ColorTemplate.VORDIPLOM_COLORS){
                    colors.add(c);
                }

                for(int c: ColorTemplate.JOYFUL_COLORS){
                    colors.add(c);
                }

                for(int c: ColorTemplate.COLORFUL_COLORS){
                    colors.add(c);
                }

                for(int c: ColorTemplate.LIBERTY_COLORS){
                    colors.add(c);
                }

                for(int c: ColorTemplate.PASTEL_COLORS){
                    colors.add(c);
                }
            colors.add(ColorTemplate.getHoloBlue());
            dataset.setColors(colors);
            PieData data=new PieData(xval,dataset);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(20f);
            data.setValueTextColor(Color.GRAY);

            mChart.setData(data);
            mChart.highlightValue(null);
            mChart.invalidate();






    }

}











