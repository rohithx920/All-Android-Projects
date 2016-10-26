package com.example.rohith.hw4;

import android.app.ProgressDialog;
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
 * Created by Rohith on 6/19/2016.
 */
public class GetData extends AsyncTask<String,Void,ArrayList<Ted>>{
    BufferedReader br;
    MainActivity mainActivity;
    ProgressDialog pd;
    SendingValuesBack sendingValuesBack;
    static interface SendingValuesBack{
        void sendValuesToMain(ArrayList<Ted> ted_list);
    }
    public GetData(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        this.sendingValuesBack=mainActivity;
    }
    @Override
    protected ArrayList<Ted> doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream tp=con.getInputStream();
            br=new BufferedReader(new InputStreamReader(tp));
            /*StringBuilder sb=new StringBuilder();
            String line="";
            while((line=br.readLine())!=null) {
                sb.append(line+"\n");
                line=br.readLine();
            }
            Log.d("back",""+sb.toString());
*/
            return TedUtil.TedSAXParser.parseTed(tp);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
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
    protected void onPostExecute(ArrayList<Ted> arrayList) {
       pd.dismiss();
        Log.d("list:", "onPostExecute: "+arrayList.get(0));
        sendingValuesBack.sendValuesToMain(arrayList);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(mainActivity);
        pd.setMessage("Loading Episodes");
        pd.setCancelable(false);
        pd.show();

    }

}
