package com.example.rohith.hw1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rohith on 5/28/2016.
 */
public class RadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_buttons);
        Switch sw=(Switch)findViewById(R.id.switch_id);
        final Intent i=new Intent(this,MainActivity.class);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                startActivity(i);
            }
        });
        final TextView hrs=(TextView)findViewById(R.id.hours_id);
        final TextView min=(TextView)findViewById(R.id.min_id);
        final TextView result=(TextView)findViewById(R.id.Result_id);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.RG_id);
        Button convert=(Button)findViewById(R.id.Convert_id);


        //Text View change listener for hours field
        hrs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)  {


            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    Log.d("dd", "----------" + s.toString());
                    int dif = 23 - Integer.parseInt(s.toString());
                    Log.d("dd", "----------" + dif);
                    if (dif < 0) {
                        //To limit the hour value in between 0 to 23
                        Toast.makeText(RadioActivity.this, "Hours value cannot exceed value 23", Toast.LENGTH_SHORT).show();
                        hrs.setText("");
                        min.setText("");
                    }
                }
                catch (Exception e){
                    Log.d("dd", "exc");
                }

            }
        });
        //Text View change listener for minutes field
        min.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Log.d("dd", "----------" + s.toString());
                    int dif = 59 - Integer.parseInt(s.toString());
                    Log.d("dd", "----------" + dif);
                    if (dif < 0) {
                        //To limit the min value in between 0 to 59
                        Toast.makeText(RadioActivity.this, "Hours value cannot exceed value 59", Toast.LENGTH_SHORT).show();
                        hrs.setText("");
                        min.setText("");
                    }
                }
                catch (Exception e){
                    Log.d("dd", "exc");
                }

            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId()==R.id.EST_id_rg){
                    Log.d("***", "click");
                    m_method("EST:",5);
                                   }
                else if(rg.getCheckedRadioButtonId()==R.id.rg_cst_id){
                    Log.d("***", "click");
                    m_method("CST:",6);
                }
                else if(rg.getCheckedRadioButtonId()==R.id.rg_mst_id){
                    Log.d("***", "click");
                    m_method("MST:",7);
                }
                else if(rg.getCheckedRadioButtonId()==R.id.rg_pst_id){
                    Log.d("***", "click");
                    m_method("PST:",8);
                }
                else if(rg.getCheckedRadioButtonId()==R.id.clearall_id_rg){
                    hrs.setText("");
                    min.setText("");
                    result.setText("");
                }

            }
        });



    }
    public void m_method(String string,int hour_diff){
         TextView hrs=(TextView)findViewById(R.id.hours_id);
         TextView min=(TextView)findViewById(R.id.min_id);
         TextView result=(TextView)findViewById(R.id.Result_id);

        try {
            //Log.d("G", "------- "+min.getText().toString());
            if(hrs.getText().toString().equals("")&&min.getText().toString().equals("")){
                Toast.makeText(RadioActivity.this, "Enter Hours and Minutes", Toast.LENGTH_SHORT).show();
            }
            else if((hrs.getText().toString().equals(""))){
                Toast.makeText(RadioActivity.this, "Enter Hours", Toast.LENGTH_SHORT).show();}

            else if(min.getText().toString().equals(""))
                Toast.makeText(RadioActivity.this, "Enter Minutes", Toast.LENGTH_SHORT).show();
            else {
                //Log.d("oh", "-----" + min.getText().toString());

                if(hrs.getText().toString().length()==1){
                    String temp=hrs.getText().toString();
                    hrs.setText("0"+temp);
                }
                if(min.getText().toString().length()==1){
                    String temp=min.getText().toString();
                    min.setText("0"+temp);
                }
                final String space25=new String(new char[25]).replace('\0', ' ');

                int Res = Integer.parseInt(hrs.getText().toString()) - hour_diff;
                if (Res < 0) {

                    SpannableString ss=  new SpannableString(string + space25 +
                            Integer.toString(24 + Res) + ":" + min.getText().toString() +
                            space25 +getResources().getString(R.string.Previous_day_label));
                    ss.setSpan(new ForegroundColorSpan(Color.RED), 58, 71, 0);
                    result.setText(ss);
                } else {

                    result.setText(string +space25 + Integer.toString(Res) + ":" + min.getText().toString() + "");
                }
            }


        }
        catch (Exception e){
            Log.d("Exception", "-----"+e.toString());
        }

    }
}
