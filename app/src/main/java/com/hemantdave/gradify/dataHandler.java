package com.hemantdave.gradify;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by INDIA on 4/4/2016.
 */
public class dataHandler extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        URL k;
        HttpURLConnection connection;
        String Result="";
        try {
          k =new URL(params[0]);
            connection= (HttpURLConnection) k.openConnection();
            InputStream in=connection.getInputStream();
            InputStreamReader reader=new InputStreamReader(in);
            int data=reader.read();
            while(data!=-1){
                char curent=(char)data;
                Result+=curent;
                data=reader.read();

            }
            return Result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result;
    }


}
