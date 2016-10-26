package com.example.rohith.p_fragpments_2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TestFragment.SendValuesBack{
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.container,new TestFragment(),"testfrag").commit();
        getFragmentManager().beginTransaction().add(R.id.container,new TestFragment(),"testfrag2").commit();
        tv= (TextView) findViewById(R.id.textView);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestFragment fragment=(TestFragment)getFragmentManager().findFragmentByTag("testfrag");
                TestFragment fragment2=(TestFragment)getFragmentManager().findFragmentByTag("testfrag2");
                fragment.sendingFromActivityToFragment("Hi this is Main");
                fragment2.sendingFromActivityToFragment("Hi");

            }
        });
    }

    @Override
    public void getValues(String string) {
        tv.setText(string);
    }

}
