package com.example.rohith.inclass_08;

import android.app.*;
import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements UserNameFragment.OnFragement{
    static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;
        getFragmentManager().beginTransaction()
                .add(R.id.container,new UserNameFragment(),"tag_uname_fragment" ).commit();

        //new GetData(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");
    }

    @Override
    public void callNextFragment(int i) {
        if(i==1){
            //new GetData(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");


            getFragmentManager().beginTransaction()
                    .replace(R.id.container,new ListFragment(),"tag_list_fragment" ).commit();

        }
    }

}
