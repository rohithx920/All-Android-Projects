package com.example.rohith.inclass09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class Preview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        setTitle("Preview Activity");
        TopApps topApps= (TopApps) getIntent().getExtras().getSerializable("obj");
        //topApps.getAppname();
        TextView textView= (TextView) findViewById(R.id.textView6);
        textView.setText(topApps.getAppname());
        ImageView iv= (ImageView) findViewById(R.id.imageView2);
        ImageView iv2= (ImageView) findViewById(R.id.imageView4);
        Picasso.with(this).load(topApps.getImage()).into(iv);

        if(topApps.getFav() == 1){
            iv2.setImageResource(R.drawable.favoritesyellow);
        } else {
            iv2.setVisibility(View.GONE);
        }

    }
}
