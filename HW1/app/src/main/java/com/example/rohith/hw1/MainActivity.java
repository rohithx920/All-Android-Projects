package com.example.rohith.hw1;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
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

public class MainActivity extends AppCompatActivity {
//private View view1,view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttons();
    }

    public void buttons() {
        setContentView(R.layout.activity_main);
        Switch s = (Switch)findViewById(R.id.switch_id);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    radiobuttons();
                }
                else{
                    buttons();
                }
            }
        });
        ifChecked();


    }
    public void radiobuttons(){
        setContentView(R.layout.radio_buttons);
        Switch s = (Switch)findViewById(R.id.switch_id);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttons();
                }
                else{
                    radiobuttons();
                }
            }

        });
        ifNotChecked();

    }
    public void ifChecked(){
        final Button est=(Button)findViewById(R.id.EST_id);
        Button cst=(Button)findViewById(R.id.CST_id);
        Button mst=(Button)findViewById(R.id.MST_id);
        Button pst=(Button)findViewById(R.id.PST_id);
        Button clr=(Button)findViewById(R.id.Clear_All_id);
        final TextView hrs=(TextView)findViewById(R.id.hours_id);
        final TextView min=(TextView)findViewById(R.id.min_id);
        final TextView result=(TextView)findViewById(R.id.Result_id);

        validationCheck();

        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws NullPointerException {
                main_method("EST:", 5);
            }
        });
        cst.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                main_method("CST:", 6);
            }
        });
        mst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_method("MST:", 7);
            }
        });
        pst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_method("PST:", 8);
            }
        });
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrs.setText("");
                min.setText("");
                result.setText("");
            }
        });

    }
    public void ifNotChecked(){
        final RadioGroup rg=(RadioGroup)findViewById(R.id.RG_id);
        final TextView hrs=(TextView)findViewById(R.id.hours_id);
        final TextView min=(TextView)findViewById(R.id.min_id);
        final TextView result=(TextView)findViewById(R.id.Result_id);
        Button convert=(Button)findViewById(R.id.Convert_id);
        validationCheck();
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId()==R.id.EST_id_rg){
                    Log.d("***", "click");
                    main_method("EST:",5);
                }
                else if(rg.getCheckedRadioButtonId()==R.id.rg_cst_id){
                    Log.d("***", "click");
                    main_method("CST:",6);
                }
                else if(rg.getCheckedRadioButtonId()==R.id.rg_mst_id){
                    Log.d("***", "click");
                    main_method("MST:",7);
                }
                else if(rg.getCheckedRadioButtonId()==R.id.rg_pst_id){
                    Log.d("***", "click");
                    main_method("PST:",8);
                }
                else if(rg.getCheckedRadioButtonId()==R.id.clearall_id_rg){
                    hrs.setText("");
                    min.setText("");
                    result.setText("");
                }


            }
        });


    }
    public void main_method (String string,int hour_diff){
        TextView hrs=(TextView)findViewById(R.id.hours_id);
        TextView min=(TextView)findViewById(R.id.min_id);
        TextView result=(TextView)findViewById(R.id.Result_id);

        try {
            //Log.d("G", "------- "+min.getText().toString());
            if(hrs.getText().toString().equals("")&&min.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Enter Hours and Minutes", Toast.LENGTH_SHORT).show();
            }
            else if((hrs.getText().toString().equals(""))){
                Toast.makeText(MainActivity.this, "Enter Hours", Toast.LENGTH_SHORT).show();}

            else if(min.getText().toString().equals(""))
                Toast.makeText(MainActivity.this, "Enter Minutes", Toast.LENGTH_SHORT).show();
            else {
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
    public void validationCheck(){
        final TextView hrs=(TextView)findViewById(R.id.hours_id);
        final TextView min=(TextView)findViewById(R.id.min_id);
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
                        Toast.makeText(MainActivity.this, "Hours value cannot exceed value 23", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "Hours value cannot exceed value 59", Toast.LENGTH_SHORT).show();
                        hrs.setText("");
                        min.setText("");
                    }
                }
                catch (Exception e){
                    Log.d("dd", "exc");
                }

            }
        });

    }


}
