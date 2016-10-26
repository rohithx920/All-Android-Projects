package com.example.rohith.inclass06;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.rohith.inclass06.MainActivity;
import com.example.rohith.inclass06.TopApps;
import com.example.rohith.inclass06.TopAppsUtil;

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
    public AsyncResponse delegate;
    private static DataManager dm;
    ProgressDialog pd;
    public GetData(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        this.delegate=mainActivity;
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
    protected void onPostExecute(final ArrayList<TopApps> arrayList) {
        //prog.dismiss();
        //super.onPostExecute(s);
        pd.dismiss();
        ArrayList<TopApps> mylist=new ArrayList<TopApps>();
        mylist=arrayList;
        delegate.processFinish(mylist);
        Log.d(TAG, "onPostExecute: 5555"+mylist.size());
        Log.d(TAG, "onPostExecute: ");
        final ListView listView= (ListView) mainActivity.findViewById(R.id.listView);
        Log.d(TAG, "%%%%%%%%%"+arrayList.size());
        AppsAdapter appsAdapter=new AppsAdapter(mainActivity,R.layout.row_layout,arrayList,0);
        listView.setAdapter(appsAdapter);
        appsAdapter.setNotifyOnChange(true);
        //ArrayList mylist<TopApps>=arrayList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "onItemClick: "+position);
                Toast.makeText(mainActivity, "This is preview", Toast.LENGTH_SHORT).show();
                TopApps topapps=arrayList.get(position);
                Intent intent=new Intent(mainActivity,Preview.class);
                intent.putExtra("obj",topapps);

                mainActivity.startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemLongClick: ");

                TopApps topapps = arrayList.get(position);
                String appname = topapps.getAppname();
                DataManager datamanager = new DataManager(mainActivity);
                if (datamanager.getNote(appname) == null) {
                    TopApps note = new TopApps();
                    Toast.makeText(mainActivity, "Adding it to database", Toast.LENGTH_SHORT).show();
                    note.setAppname(topapps.getAppname());
                    note.setDevloper_name(topapps.getDevloper_name());
                    note.setPrice(topapps.getPrice());
                    note.setRelease_Date(topapps.getRelease_Date());
                    note.setCategory(topapps.getCategory());
                    note.setImage(topapps.getImage());
                    topapps.setFav(1);

                    //arrayList.add(position,topapps);
                    AppsAdapter appsAdapter=new AppsAdapter(mainActivity,R.layout.row_layout,arrayList,0);
                    listView.setAdapter(appsAdapter);
                    appsAdapter.setNotifyOnChange(true);
                    MainActivity.dm.saveNote(note);
                    return false;
                }
                else{
                    datamanager.deleteNote(topapps);
                    Toast.makeText(mainActivity, "delete in database", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }
    static interface AsyncResponse {

        void processFinish(ArrayList<TopApps> output);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(mainActivity);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}

