package com.example.rohith.midterm;

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
public class GetData extends AsyncTask<String,Void,ArrayList<weather>>{
        BufferedReader br;
        MainActivity mainActivity;
        static String TAG="demo";
        public GetData(MainActivity mainActivity){
                this.mainActivity=mainActivity;
        }
        @Override
        protected ArrayList<weather> doInBackground(String... params) {
                try {
                        URL url=new URL(params[0]);
                        HttpURLConnection con= (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        InputStream tp=con.getInputStream();
                        br=new BufferedReader(new InputStreamReader(tp));
                        StringBuilder sb=new StringBuilder();
                        String line="";
                        Log.d(TAG, "doInBackground: ");
                        while((line=br.readLine())!=null) {
                                sb.append(line+"\n");
                                line=br.readLine();
                        }
                        Log.d("back",""+sb.toString());
                        return weatherUtil.PersonJsonParser.parseStudent(sb.toString());

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
        protected void onPostExecute(ArrayList<weather> arrayList) {
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

