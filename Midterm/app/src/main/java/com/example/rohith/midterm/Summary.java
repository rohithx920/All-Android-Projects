package com.example.rohith.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        String st=(String)getIntent().getExtras().get("getvalues");
        String st2=(String)getIntent().getExtras().get("unit");
        //setTitle("Charlotte,NC,US");
        setTitle(st);
        TextView temp = (TextView) findViewById(R.id.textView2);
        TextView hum = (TextView) findViewById(R.id.textView3);
        TextView pres = (TextView) findViewById(R.id.textView4);
        TextView weather = (TextView) findViewById(R.id.textView5);
        ArrayList<weather> mylist= (ArrayList<com.example.rohith.midterm.weather>) getIntent().getExtras().get("sending_vlaues");
        Log.d("fill",""+mylist.size());
        //mylist.getClass(weather);

        if(st2.equals("metric")) {
            temp.setText("Temperature:\t"+mylist.get(0).getTemperature()+"Celsius");
            hum.setText("Humidity:\t"+mylist.get(0).getHumidity()+"%");
            pres.setText("Pressure:\t"+mylist.get(0).getPressure()+"hpa");
            weather.setText("Weather:\t"+mylist.get(0).getDescription());

        }
        else{
            temp.setText("Temperature:\t"+mylist.get(0).getTemperature()+"Fahrenheit");
            hum.setText("Humidity:\t"+mylist.get(0).getHumidity()+"%");
            pres.setText("Pressure:\t"+mylist.get(0).getPressure()+"hpa");
            weather.setText("Weather:\t"+mylist.get(0).getDescription());

        }
        String image_url=mylist.get(0).getIcon();
        String url="http://openweathermap.org/img/w/"+image_url+".png";
        //weather weather_obj= (com.example.rohith.midterm.weather)
        new GetImage(this).execute(url);


    }
}
