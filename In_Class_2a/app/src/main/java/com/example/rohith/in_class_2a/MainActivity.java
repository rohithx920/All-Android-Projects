package com.example.rohith.in_class_2a;
//In Class 2a
//Rohith Sagar Mogili
//Christian Hartman
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button euro_button=(Button)findViewById(R.id.euro_id);
        Button cad_button=(Button)findViewById(R.id.cand_id);
        Button GBP_button=(Button)findViewById(R.id.brit_id);
        Button jpy_button=(Button)findViewById(R.id.jap_id);
        Button clear_button=(Button)findViewById(R.id.clear_id);
        final EditText et1=(EditText)findViewById(R.id.editText1);
        final EditText et2=(EditText)findViewById(R.id.editText2);
        assert euro_button != null;
        euro_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws NullPointerException {
                try {


                        float i = Float.parseFloat(et1.getText().toString());
                        float result = (float) (i * 0.849282);
                        float result1 = (float) (Math.round(result * 100.0) / 100.0);
                        et2.setText(et1.getText().toString() + "USD=" + result1 + "EUR");


                }
                catch(Exception e){
                    Log.d("exp", "exception ");
                    Context context = getApplicationContext();
                    CharSequence text = "NO VALUE Entered";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
            }
        });
        cad_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws NullPointerException {
                try {


                    float i = Float.parseFloat(et1.getText().toString());
                    float result = (float) (i * 1.19);
                    //Math.round(result);
                    float result1 = (float) (Math.round(result * 100.0) / 100.0);
                    et2.setText(et1.getText().toString() + "USD=" + result1 + "CAD");

                } catch (Exception e) {
                    Log.d("exp", "exception ");
                    Context context = getApplicationContext();
                    CharSequence text = "NO VALUE Entered";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }
            }
        });
        GBP_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)throws NullPointerException  {
                try{
                        float i = Float.parseFloat(et1.getText().toString());
                        float result = (float) (i * 0.65);
                        float result1 = (float) (Math.round(result * 100.0) / 100.0);
                        et2.setText(et1.getText().toString() + "USD=" + result1 + "GBP");


            }
                catch(Exception e){
                    Log.d("exp", "exception ");
                    Context context = getApplicationContext();
                    CharSequence text = "NO VALUE Entered";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }}
        });
        assert jpy_button != null;
        jpy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws NullPointerException {
                try {
                    float i = Float.parseFloat(et1.getText().toString());
                    float result = (float) (i * 117.62);
                    float result1 = (float) (Math.round(result * 100.0) / 100.0);
                    et2.setText(et1.getText().toString() + "USD=" + result1 + "JPY");


                }
                catch(Exception e){
                    Log.d("exp", "exception ");
                    Context context = getApplicationContext();
                    CharSequence text = "NO VALUE Entered";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
            }
        });
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et2.setText("");
                et1.setText("");

            }
        });



    }
}
