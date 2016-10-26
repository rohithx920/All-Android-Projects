package com.example.rohith.hw3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.rohith.hw3.MainActivity;
import com.example.rohith.hw3.TopApps;
import com.example.rohith.hw3.TopAppsUtil;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rohith on 6/9/2016.
 */
public class GetData extends AsyncTask<String,Void,ArrayList<TopApps>>{
    BufferedReader br;
    MainActivity mainActivity;
    static String TAG="demo";
    public GetData(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    @Override
    protected ArrayList<TopApps> doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if(con.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream tp = con.getInputStream();
                Log.d(TAG, "doInBackground: ");

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
    protected void onPostExecute(ArrayList<TopApps> arrayList) {
        //prog.dismiss();
        //super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: ");
        Intent intent=new Intent(mainActivity,Apps.class);
        intent.putExtra("sending_vlaues",arrayList);
        //intent.putExtra("EXIT", true);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainActivity.startActivity(intent);
        mainActivity.finish();
        Log.d(TAG, "onPostExecute: ");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //prog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

