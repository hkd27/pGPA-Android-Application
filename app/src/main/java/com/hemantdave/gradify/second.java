package com.hemantdave.gradify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class second extends AppCompatActivity {
    String CourseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent ik=getIntent();
        int id=Integer.parseInt(ik.getStringExtra("id"));
        CourseName=ik.getStringExtra("CourseName");
        TextView name=(TextView)findViewById(R.id.CourseName);
        name.setText(CourseName);
        CourseName=CourseName.toLowerCase();

        CourseName=CourseName.replace(" ","");
        String result="";
        String grd="";
        TextView grade=(TextView)findViewById(R.id.GradeCharacter);

        dataHandler dataDownload=new dataHandler();
        String URL="http://136.176.45.136:8080/book/book/course/"+id+"/"+CourseName;
        //Log.d("Name",CourseName);
        try {
            result= dataDownload.execute(URL).get();
           // Log.d("OUPUT",result);

            if((result.toString()).equals("[]")){
                result="N.A";
            }else{
                result=result.replace("[","");
                result=result.replace("]","");
            }
            grade.setText(result.toString());

           // Log.e("IMMMMMMMMMMMMMMMM", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Log.d("Out", id + CourseName);
        String suggestion_results="";
        String Suggestions="http://136.176.45.136:8080/book/book/excel/"+id+"/"+CourseName;
        dataHandler dataDownload2=new dataHandler();
        try {
            suggestion_results= dataDownload2.execute(Suggestions).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ListView SuggestionList=(ListView)findViewById(R.id.Course_suggestion);
        suggestion_results=suggestion_results.replace("[","");
        suggestion_results=suggestion_results.replace("]","");

        final String[] tokens = suggestion_results.split(",", -1);
        //Log.d("test", tokens[1]);
        final ArrayList<String> mySuggestion=new ArrayList<String>();
        mySuggestion.addAll(Arrays.asList(tokens));

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mySuggestion);
        SuggestionList.setAdapter(adapter);
        SuggestionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
                String Changeresult;
               //Log.d("ANSW",+" ");
                dataHandler dataDownload3=new dataHandler();
                String pos=tokens[position];
                pos=pos.replace(" ","");
                pos=pos.toLowerCase();
                Intent ik1=getIntent();
                int kid=Integer.parseInt(ik1.getStringExtra("id"));
                String URL1="http://136.176.45.136:8080/book/book/course/"+kid+"/"+pos;

                try {
                   Changeresult= dataDownload3.execute(URL1).get();
               // Log.d("OUPUT",result);


                    Changeresult=Changeresult.replace("[","");
                    Changeresult=Changeresult.replace("]","");
                    TextView grade1=(TextView)findViewById(R.id.GradeCharacter);
                   grade1.setText(Changeresult.toString());
                    TextView name=(TextView)findViewById(R.id.CourseName);
                    CourseName=mySuggestion.get(position);
                    name.setText(mySuggestion.get(position));
                    Button hide_btn=(Button) findViewById(R.id.btn_stats);
                    hide_btn.setVisibility(view.INVISIBLE);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    public void stat(View v){
        Intent i=new Intent(getApplicationContext(),piechart.class);
        i.putExtra("courseName",CourseName);
        startActivity(i);
    }

}
