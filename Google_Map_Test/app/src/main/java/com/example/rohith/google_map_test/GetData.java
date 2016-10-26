package com.example.rohith.google_map_test;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

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
public class GetData extends AsyncTask<ArrayList<String>,Void,ArrayList<Map>> {
    BufferedReader br;
    MainActivity mainActivity;
    static String TAG="demo";
    static int TOTAL_LOCATIONS;
    ProgressDialog progressBar;
    public GetData(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    @Override
    protected ArrayList<Map> doInBackground(ArrayList<String>... arrayLists) {
        try {
            int no_of_locations=arrayLists[0].size();
            TOTAL_LOCATIONS=no_of_locations;
            ArrayList<Map> mapArrayList=new ArrayList<Map>();

            //One direction details(reverse)
            /* for(int j=0;j<no_of_locations-1;j++){
                for(int k=j+1;k<no_of_locations;k++){
                    String url1 = "https://maps.googleapis.com/maps/api/directions/json?origin="+
                            arrayLists[0].get(j)+ "&destination="+arrayLists[0].get(k)+"&key=AIzaSyApfn0IRy81vEVRPSA1DXZUqGO_27vcXNE";
                    //Log.d(TAG, "dataGetter: "+url1);
                    URL url=new URL(url1);
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    InputStream tp=con.getInputStream();
                    br=new BufferedReader(new InputStreamReader(tp));
                    StringBuilder sb=new StringBuilder();
                    String line="";
                    Log.d(TAG, "doInBackground: ");
                    while((line=br.readLine())!=null) {
                        sb.append(line+"\n");
                        //Log.d("999999", "value"+sb.toString());
                        //line=br.readLine();
                    }
                    //MapUtil.PersonJsonParser.parseStudent(sb.toString());
                    //if(j==no_of_locations-1 || k==no_of_locations){
                         mapArrayList.add(MapUtil.PersonJsonParser.parseStudent(sb.toString()));
                    //}
                }


            }
            */
            //TO get directions in one way
           /* int no_objects=no_of_locations*no_of_locations-1/2;
            progressBar.setMax(no_objects);
            int no_objects_done=0;
            for(int j=no_of_locations-1;j>0;j--){
                for(int k=j-1;k>-1;k--){
                    no_objects_done++;
                    progressBar.setProgress((int)(no_objects_done));
                    String url1 = "https://maps.googleapis.com/maps/api/directions/json?origin="+
                            arrayLists[0].get(j)+ "&destination="+arrayLists[0].get(k)+"&key=AIzaSyApfn0IRy81vEVRPSA1DXZUqGO_27vcXNE";
                    //Log.d(TAG, "dataGetter: "+url1);
                    URL url=new URL(url1);
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    InputStream tp=con.getInputStream();
                    br=new BufferedReader(new InputStreamReader(tp));
                    StringBuilder sb=new StringBuilder();
                    String line="";
                    Log.d(TAG, "doInBackground: ");
                    while((line=br.readLine())!=null) {
                        sb.append(line+"\n");
                        //Log.d("999999", "value"+sb.toString());
                        //line=br.readLine();
                    }
                    //MapUtil.PersonJsonParser.parseStudent(sb.toString());
                    //if(j==no_of_locations-1 || k==no_of_locations){
                    //mapArrayList.add(MapUtil.PersonJsonParser.parseStudent(sb.toString()));
                    Map temp = MapUtil.PersonJsonParser.parseStudent(sb.toString());
                    if(temp!=null)
                    mapArrayList.add(temp);
                    //}
                }


            }
            */
            //To get directions in both directions
            int no_objects=no_of_locations*no_of_locations-1;
            progressBar.setMax(no_objects);
            int no_objects_done=0;
            for(int j=0;j<no_of_locations;j++)
            {
                //k=j;
                for(int k=0;k<no_of_locations;k++)
                {
                    if(j!=k)
                    {
                        no_objects_done++;
                        progressBar.setProgress((int)(no_objects_done));
                            String url1 = "https://maps.googleapis.com/maps/api/directions/json?origin="+
                            arrayLists[0].get(j)+ "&destination="+arrayLists[0].get(k)+"&key=AIzaSyApfn0IRy81vEVRPSA1DXZUqGO_27vcXNE";

                            URL url=new URL(url1);
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    InputStream tp=con.getInputStream();
                    br=new BufferedReader(new InputStreamReader(tp));
                    StringBuilder sb=new StringBuilder();
                    String line="";
                    Log.d(TAG, "doInBackground: ");
                    while((line=br.readLine())!=null) {
                        sb.append(line+"\n");
                        //Log.d("999999", "value"+sb.toString());
                        //line=br.readLine();
                    }
                    //MapUtil.PersonJsonParser.parseStudent(sb.toString());
                    //if(j==no_of_locations-1 || k==no_of_locations){
                         mapArrayList.add(MapUtil.PersonJsonParser.parseStudent(sb.toString()));
                    //}

                    }
                }


            }


            //Log.d("back",""+sb.toString());
            Log.d(TAG, "&&&&&"+mapArrayList.size());
            return mapArrayList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
    protected void onPostExecute(ArrayList<Map> arrayList) {
        Intent intent=new Intent(mainActivity,MapsActivity.class);
        int p=arrayList.size();
        intent.putExtra("sending_vlaues",arrayList);
        intent.putExtra("Total_locations",TOTAL_LOCATIONS);
        mainActivity.startActivity(intent);
        progressBar.dismiss();


        /*
        //prog.dismiss();
        //super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: ");
        Intent intent=new Intent(mainActivity,Summary.class);
        intent.putExtra("sending_vlaues",arrayList);
        TextView tv= (TextView) mainActivity.findViewById(R.id.editText_id);
        Log.d(TAG, "hhhhhh"+tv.getText());
        intent.putExtra("getvalues",tv.getText().toString());
        Switch sw = (Switch) mainActivity.findViewById(R.id.switch1);
        //intent.putExtras("unit",);
        if(sw.isChecked()) {
            intent.putExtra("unit","metric");
        }
        else{
            intent.putExtra("unit","imperial");
        }
        mainActivity.startActivity(intent);
        */
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar= new ProgressDialog(MainActivity.mainActivity);
        //progressBar.set
        progressBar.setMessage("Loading");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

