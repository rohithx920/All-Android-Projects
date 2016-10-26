package com.example.rohith.in_class05;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetData.AsyncResponse {
    final CharSequence[] items = {"UNCC","Android","Winter","Aurora","Wonders"};
    String TAG="demo";
    TextView search;
    static Context myContext;
    int i=1;
    ArrayList<String> mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        search=(TextView)findViewById(R.id.search_id);
        builder.setTitle("Choose a Keyword")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.d(TAG, items[item].toString());
                        search.setText(""+items[item].toString());
                        if(iscon()){
                            Toast.makeText(MainActivity.this, "Connected"+items[item].toString().toLowerCase().toString(), Toast.LENGTH_SHORT).show();
                            String link="http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword="+items[item].toString().toLowerCase();
                            new GetData(MainActivity.this).execute(link);
                            }
                        else{
                            Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        final AlertDialog singleItem=builder.create();
        ((Button) findViewById(R.id.go_id)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                singleItem.show();
            }
        });


        ImageView next= (ImageView) findViewById(R.id.next_id);
        ImageView prev= (ImageView) findViewById(R.id.prev_id);
        prev.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                    Log.d(TAG, "onClick: " + mylist.size() + "99");

                i--;
                if (i == 0) {
                    i = mylist.size()-1;
                    new GetImage(MainActivity.this).execute(mylist.get(i));

                } else if (mylist.size() > i) {
                    new GetImage(MainActivity.this).execute(mylist.get(i));
                    Log.d(TAG, "onClick: &&&&&");

                }
            }
        });
        //ImageView prev= (ImageView) findViewById(R.id.prev_id);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(mylist.size()>i){
                    new GetImage(MainActivity.this).execute(mylist.get(i));
                    Log.d(TAG, "onClick: &&&&&");
                }
                else{
                    i=1;
                    new GetImage(MainActivity.this).execute(mylist.get(i));
                    Log.d(TAG, "onClick: ((((");
                }
            }
        });



    }

    public boolean iscon() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null & ni.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<String> processFinish(ArrayList<String> output) {
        mylist =new ArrayList<String>();
        mylist=output;
     return output;
    }
}
