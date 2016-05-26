package com.hemantdave.gradify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

public class frag1 extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private PieChart mChart;
    String CourseName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag1);


      /* Log.d("Hello From d", "HD");
        CourseName=savedInstanceState.getString("CourseName").toLowerCase();

        String result="";
        CourseName=CourseName.replace(" ","");

        dataHandler dataDownload=new dataHandler();
        String URL="http://10.0.2.2:8080/book/book/graph/"+CourseName;

        try {
            result= dataDownload.execute(URL).get();



                result=result.replace("[","");
                result=result.replace("]", "");
            final String[] tokens = result.split(",", -1);
            Log.d("Data",tokens[1]);*/

           /* } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }*/


    }
    public void call(View v){
        Toast.makeText(getApplicationContext(),"Hello from here",Toast.LENGTH_SHORT).show();
    }
}
