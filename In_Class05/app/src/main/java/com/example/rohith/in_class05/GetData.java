package com.example.rohith.in_class05;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetData extends AsyncTask<String, Void, ArrayList<String>> {
    BufferedReader br;
    ArrayList<String> mylist=new ArrayList<String>();
    static String TAG="demo";

    public interface AsyncResponse {
        ArrayList<String> processFinish(ArrayList<String> output);
    }
    public AsyncResponse delegate = null;

    public GetData(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        try {
            //Log.d(TAG, "doInBackground: ");
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
            String mystring=sb.toString();
            Log.d(TAG, "mystr "+mystring);
            if(mystring!=null) {
                String[] split = mystring.split(";");
                String keyword = split[0];
                mylist.add(keyword);
                int length = split.length;
                int i = 1;

                if (i <= length) {
                    for (int j = 1; j <length; j++) {
                        Log.d(TAG, "links"+split[j]);
                        String s=split[j];
                        mylist.add(s);

                    }
                }
            }
            return mylist;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
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
    protected void onPostExecute(final ArrayList<String> Url_list) {
        delegate.processFinish(Url_list);
        new GetImage((MainActivity) delegate).execute(Url_list.get(1));
    }

}