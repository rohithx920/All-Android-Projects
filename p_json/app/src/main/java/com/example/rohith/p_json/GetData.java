package com.example.rohith.p_json;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
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
public class GetData extends AsyncTask<String,Void,ArrayList<Student>>{
    BufferedReader br;
    MainActivity mainActivity;
    public GetData(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    @Override
    protected ArrayList<Student> doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream tp=con.getInputStream();
            br=new BufferedReader(new InputStreamReader(tp));
            StringBuilder sb=new StringBuilder();
            String line="";
            while((line=br.readLine())!=null) {
                sb.append(line+"\n");
                line=br.readLine();
            }
            Log.d("back",""+sb.toString());
            return PersonUtil.PersonJsonParser.parseStudent(sb.toString());

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
    protected void onPostExecute(ArrayList<Student> arrayList) {
        //super.onPostExecute(s);
        ListView listView= (ListView) mainActivity.findViewById(R.id.listView);
        TextView textView=new TextView(mainActivity);
        String name=arrayList.get(0).getName();
        textView.setText(name);
        textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        listView.addView(textView);
    }
}
