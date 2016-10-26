package com.example.rohith.testapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static String Name_key="name";
    final static int Request_code=111;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Request_code){
            if(resultCode==RESULT_OK){
                String str= (String) data.getExtras().get(Name_key);

                Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i=new Intent(getBaseContext(),newa.class);
                Intent i=new Intent("com.example.rohith.testapp2.intent.action.VIEW");
                i.putExtra(Name_key,"rohith");
                startActivityForResult(i,Request_code);

            }
        });
    }
}
