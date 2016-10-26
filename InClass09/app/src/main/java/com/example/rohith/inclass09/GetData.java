package com.example.rohith.inclass09;

/**
 * Created by This on 6/23/16.
 */
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


import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GetData extends AsyncTask<String,Void,ArrayList<TopApps>>{
    BufferedReader br;
    AppsList appsList;
    static String TAG="demo";
    public AsyncResponse delegate;
    private static DataManager dm;
    ProgressDialog pd;
    public GetData(AppsList mainActivity){
        this.appsList=mainActivity;
        this.delegate= mainActivity;
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
        final ListView listView= (ListView) appsList.findViewById(R.id.listView);
        Log.d(TAG, "%%%%%%%%%"+arrayList.size());
        AppsAdapter appsAdapter=new AppsAdapter(appsList,R.layout.row_layout,arrayList,0);
        listView.setAdapter(appsAdapter);
        appsAdapter.setNotifyOnChange(true);
        //ArrayList mylist<TopApps>=arrayList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "onItemClick: "+position);
                Toast.makeText(appsList, "This is preview", Toast.LENGTH_SHORT).show();
                TopApps topapps=arrayList.get(position);
                Intent intent=new Intent(appsList,Preview.class);
                intent.putExtra("obj",topapps);


                appsList.startActivity(intent);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemLongClick: ");

                TopApps topapps = arrayList.get(position);
                String appname = topapps.getAppname();
                String email=MainActivity.email_fav;
                MainActivity.myRef.child(email).child("fav").setValue(appname);
                DataManager datamanager = new DataManager(appsList);
                if (datamanager.getNote(appname) == null) {
                    TopApps note = new TopApps();
                    Toast.makeText(appsList, "Adding it to database", Toast.LENGTH_SHORT).show();
                    note.setAppname(topapps.getAppname());
                    note.setDevloper_name(topapps.getDevloper_name());
                    note.setPrice(topapps.getPrice());
                    note.setRelease_Date(topapps.getRelease_Date());
                    note.setCategory(topapps.getCategory());
                    note.setImage(topapps.getImage());
                    topapps.setFav(1);

                    //arrayList.add(position,topapps);
                    AppsAdapter appsAdapter=new AppsAdapter(appsList,R.layout.row_layout,arrayList,0);
                    listView.setAdapter(appsAdapter);
                    appsAdapter.setNotifyOnChange(true);
                    AppsList.dm.saveNote(note);
                    return false;
                }
                else{

                    AppsAdapter appsAdapter=new AppsAdapter(appsList,R.layout.row_layout,arrayList,0);
                    listView.setAdapter(appsAdapter);
                    appsAdapter.setNotifyOnChange(true);
                    topapps.setFav(0);
                    datamanager.deleteNote(topapps);
                    Toast.makeText(appsList, "delete in database", Toast.LENGTH_SHORT).show();
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
        pd=new ProgressDialog(appsList);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
