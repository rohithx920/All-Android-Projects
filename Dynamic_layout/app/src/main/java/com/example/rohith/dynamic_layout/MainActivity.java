package com.example.rohith.dynamic_layout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout r= (RelativeLayout) findViewById(R.id.R_id);
        Button b=new Button(this);
        b.setLayoutParams
                (new ActionBar.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        b.setText("Main");

        r.addView(b);
        b.setId(R.id.p);
        TextView t=new TextView(this);
        t.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        t.setText("This is my app");
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW,b.getId());
        t.setLayoutParams(lp);
        r.addView(t);
        //r.removeView(b);
    }
}
