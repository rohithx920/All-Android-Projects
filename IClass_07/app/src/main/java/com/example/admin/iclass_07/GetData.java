package com.example.admin.iclass_07;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GetData extends AsyncTask<String,Void,ArrayList<TopPods>> {
    BufferedReader br;
    MainActivity mainActivity;
    static String TAG="demo";
    public GetData(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    @Override
    protected ArrayList<TopPods> doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            Log.d("demo5555",url.toString());
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if(con.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream tp = con.getInputStream();
                Log.d(TAG, "doInBackground: " + tp.toString());
                return TopAppsUtil.TopAppsSAXParser.parseTopApps(tp);
            }
            else{
                Log.d(TAG, "not connected");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @Override
    protected void onPostExecute(final ArrayList<TopPods> arrayList) {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
