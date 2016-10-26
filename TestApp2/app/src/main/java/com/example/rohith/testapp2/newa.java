package com.example.rohith.testapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class newa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newa);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i=new Intent(newa.this,MainActivity.class);
                Intent i=new Intent();
                i.putExtra(MainActivity.Name_key,"helll");
                setResult(RESULT_OK,i);
                finish();

            }
        });
    }
}
