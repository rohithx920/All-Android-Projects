package com.example.rohith.googlemapslistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MapAdapter.Interface {
static MainActivity mainActivity;
    static ArrayList<String> locations;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;
        locations=new ArrayList<String>();
        list= (ListView) findViewById(R.id.listView);
        locations.add("");locations.add("");locations.add("");
        callingMapAdaptor();
        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locations.add("");
                callingMapAdaptor();
            }
        });
        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+locations.get(0));
                new GetData(MainActivity.this).execute(locations);

            }
        });
    }

    @Override
    public void sendPostion(int postion) {
        locations.remove(postion);
        callingMapAdaptor();
    }
    public void callingMapAdaptor(){
        MapAdapter ad = new MapAdapter(this, R.layout.fragment_auto_completion, locations);
        ad.setNotifyOnChange(true);
        list.setAdapter(ad);
    }

}
