package com.example.rohith.midterm;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    //final ProgressDialog prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText_id);
        final Switch sw = (Switch) findViewById(R.id.switch1);
        ProgressDialog pd;
        sw.setChecked(true);
        try {
            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(editText.length()!=0) {
                        if (sw.isChecked()) {
                            String url = "http://api.openweathermap.org/data/2.5/forecast/city?q=" + editText.getText() + "&units=metric&APPID=9b02226522d157a12cfe5e7ff52caae1";
                            new GetData(MainActivity.this).execute(url);
                            //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();

                        } else {
                            String url = "http://api.openweathermap.org/data/2.5/forecast/city?q=" + editText.getText() + "&units=&APPID=9b02226522d157a12cfe5e7ff52caae1";
                            new GetData(MainActivity.this).execute(url);
                            //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();


                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Please enter values", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        catch(Exception e){

        }
    }

    public void call_worker(){
        String url="http://api.openweathermap.org/data/2.5/forecast/city?q="+editText.getText()+"&units=imperial&APPID=9b02226522d157a12cfe5e7ff52caae1";

    }
}
