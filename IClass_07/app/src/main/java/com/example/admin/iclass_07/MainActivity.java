package com.example.admin.iclass_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetData(this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml");

    }
}
