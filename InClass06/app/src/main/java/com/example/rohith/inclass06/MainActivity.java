package com.example.rohith.inclass06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetData.AsyncResponse {
    ArrayList<TopApps> mytop_apps_list;
    static String TAG = "demo";
    static DataManager dm;
    ListView myListView;
    AppsAdapter appsAdapter;
    AppsAdapter appsAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Apps Activity");
        dm = new DataManager(this);
        new GetData(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");

        /*final ImageView fav= (ImageView) findViewById(R.id.imageView3);
        fav.setImageResource(R.drawable.favoritesgrey);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fav.setImageResource(R.drawable.favoritesyellow);
            }
        }); */

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
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

                DataManager dat=new DataManager(this);
                try {
                    ArrayList<TopApps> my = (ArrayList<TopApps>) dat.getAllNotes();
                    //ListView lv= (ListView) findViewById(R.id.listView);
                    myListView = (ListView) findViewById(R.id.listView);
                    appsAdapter = new AppsAdapter(this, R.layout.row_layout, my, 1);
                    myListView.setAdapter(appsAdapter);
                    appsAdapter.setNotifyOnChange(true);
                    Toast.makeText(MainActivity.this, "Favorites shown", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.d(TAG, "Exc"+e);
                    Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
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
