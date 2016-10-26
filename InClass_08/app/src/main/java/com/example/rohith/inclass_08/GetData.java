package com.example.rohith.inclass_08;

//import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.rohith.inclass_08.MainActivity;
import com.example.rohith.inclass_08.TopApps;
import com.example.rohith.inclass_08.TopAppsUtil;

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
public class GetData extends AsyncTask<String,Void,ArrayList<TopApps>> implements ListFragment.next123{
    BufferedReader br;
    MainActivity mainActivity;
    static String TAG="demo";
    public GetData(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
   // ProgressDialog progressDialog;
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
    protected void onPostExecute(final ArrayList<TopApps> arrayList) {
        //prog.dismiss();
        //super.onPostExecute(s);
     //   progressDialog.dismiss();
        Log.d(TAG, "onPostExecute: ");
        ListView listView= (ListView) mainActivity.findViewById(R.id.listView);
        Log.d(TAG, "%%%%%%%%%"+arrayList.size());
        AppsAdapter appsAdapter=new AppsAdapter(mainActivity,R.layout.row_layout,arrayList);
        listView.setAdapter(appsAdapter);
        appsAdapter.setNotifyOnChange(true);
        //ArrayList mylist<TopApps>=arrayList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "onItemClick: "+position);
                TopApps topapps=arrayList.get(position);
/*                Intent intent=new Intent(mainActivity,Preview.class);
                intent.putExtra("obj",topapps);

                mainActivity.startActivity(intent);*/
                nextvalue(topapps.getImage(),topapps.getAppname());
            }
        });
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // progressDialog=new ProgressDialog(MainActivity.mainActivity);
        //progressDialog.setMessage("load");
        //progressDialog.show();

    }


    @Override
    public void nextvalue(String image, String name) {
        mainActivity.getFragmentManager().beginTransaction()
                .add(R.id.container,new Preview_Fragment(mainActivity,image,name),"tag_preview_fragment" ).commit();

    }
}

