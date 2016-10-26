package com.example.rohith.hw3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Apps extends AppCompatActivity {
int i=0;
    ArrayList<TopApps> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Apps Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        final TextView apptitle= (TextView) findViewById(R.id.textView);
        final TextView devname= (TextView) findViewById(R.id.textView2);
        final TextView releasedate= (TextView) findViewById(R.id.textView3);
        final TextView price= (TextView) findViewById(R.id.textView4);
        mylist= (ArrayList<TopApps>) getIntent().getExtras().get("sending_vlaues");
        apptitle.setText(mylist.get(i).getApp_title());
        devname.setText(mylist.get(i).getDeveloper_name());
        releasedate.setText(mylist.get(i).release_date);
        price.setText(mylist.get(i).getApp_price());
        //boolean b= (boolean) getIntent().getExtras().get("EXIT");
        //if(b){
          //  finish();
        //}
        //apptitle.setText(mylist.get(0).app_title);
        new GetImage(this).execute(mylist.get(0).getLarge_photo_url());
        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i==0){
                    i=99;

                }
                else {
                    i--;
                }
                apptitle.setText(mylist.get(i).getApp_title());
                devname.setText(mylist.get(i).getDeveloper_name());
                releasedate.setText(mylist.get(i).release_date);
                price.setText(mylist.get(i).getApp_price());
                new GetImage(Apps.this).execute(mylist.get(i).getLarge_photo_url());
            }
        });
        findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==99){
                    i=0;
                }
                else {
                    i++;
                }
                apptitle.setText(mylist.get(i).getApp_title());
                devname.setText(mylist.get(i).getDeveloper_name());
                releasedate.setText(mylist.get(i).release_date);
                price.setText(mylist.get(i).getApp_price());
                new GetImage(Apps.this).execute(mylist.get(i).getLarge_photo_url());
            }
        });
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=mylist.get(i).getUrl();
                Intent intent=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
