package com.hemantdave.gradify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void nextScreen(View v){
        EditText id=(EditText)findViewById(R.id.Student_id);
        Spinner Course=(Spinner)findViewById(R.id.t);

        Intent i=new Intent(MainActivity.this,second.class);
        i.putExtra("id",id.getText().toString());
        i.putExtra("CourseName",Course.getSelectedItem().toString());

        startActivity(i);
    }
}
