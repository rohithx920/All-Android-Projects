package com.example.rohith.inclass09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AppsList extends AppCompatActivity implements GetData.AsyncResponse {

    ArrayList<TopApps> mytop_apps_list;
    static String TAG = "demo";
    static DataManager dm;
    ListView myListView;
    AppsAdapter appsAdapter;
    AppsAdapter appsAdapter2;
    AppsList appsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);
        appsList = this;
        dm = new DataManager(this);
        new GetData(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    //@Override
    public void processFinish(ArrayList<TopApps> output) {
        mytop_apps_list = new ArrayList<TopApps>();
        mytop_apps_list=output;
        Log.d(TAG, "processFinish: " + output.size());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_favorites:
                Log.d("demo", "Favorites");
                String fav=MainActivity.myRef.child(MainActivity.email_fav).child("fav").toString();
                DataManager dat=new DataManager(this);
                try {
                    ArrayList<TopApps> my = (ArrayList<TopApps>) dat.getAllNotes();
                    //ListView lv= (ListView) findViewById(R.id.listView);
                    myListView = (ListView) findViewById(R.id.listView);
                    appsAdapter = new AppsAdapter(this, R.layout.row_layout, my, 1);
                    myListView.setAdapter(appsAdapter);
                    appsAdapter.setNotifyOnChange(true);
                    Toast.makeText(AppsList.this, "Favorites shown", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.d(TAG, "Exc"+e);
                    Toast.makeText(AppsList.this, "Nothing to show", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.show_all:
                Log.d("demo", "Show All");
                myListView = (ListView) findViewById(R.id.listView);
                Log.d(TAG, "onOptionsItemSelected: "+mytop_apps_list.size());
                appsAdapter2=new AppsAdapter(this,R.layout.row_layout,mytop_apps_list,0);
                myListView.setAdapter(appsAdapter2);
                appsAdapter2.setNotifyOnChange(true);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
