package com.example.rohith.p_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Color> color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView= (ListView) findViewById(R.id.listView);
        color=new ArrayList<Color>();
        color.add(new Color("Red","#0000"));
        color.add(new Color("Blue","#0000"));
        color.add(new Color("green","#0000"));
        color.add(new Color("brown","#0000"));
        ArrayAdapter<Color> adapter=new ArrayAdapter<Color>(this,android.R.layout.simple_list_item_1,color);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "onItemClick: "+position+color.get(position));
            }
        });
        }




    }

