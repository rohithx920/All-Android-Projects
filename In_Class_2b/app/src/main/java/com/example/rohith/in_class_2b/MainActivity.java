package com.example.rohith.in_class_2b;
//In class 2b
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
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioButton rb_euro=(RadioButton)findViewById(R.id.euro_id);
        RadioButton rb_cad=(RadioButton)findViewById(R.id.cad_id);
        RadioButton rb_gbp=(RadioButton)findViewById(R.id.gbp_id);
        RadioButton rb_jpy=(RadioButton)findViewById(R.id.jpy_id);
        RadioButton rb_clearall=(RadioButton)findViewById(R.id.clearall_id);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.radiogroup);
        final Button bt=(Button)findViewById(R.id.convert_id);
        final EditText et1=(EditText)findViewById(R.id.editText1);
        final EditText et2=(EditText)findViewById(R.id.editText2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.euro_id){

                    et2.setHint("1 USD=0.84 EUR");


                }
                else if(checkedId==R.id.clearall_id){
                    et2.setHint("1 USD=0.84 EUR");
                    et1.setText("");
                    et1.setHint("1");

                }
                else if(checkedId==R.id.cad_id){
                    et2.setHint("1 USD=1.19 CAD");                }
                else if(checkedId==R.id.gbp_id){
                    et2.setHint("1 USD= 0.65 GBP");
                }
                else if(checkedId==R.id.jpy_id){
                    et2.setHint("1 USD=117.62 JPY");

                }

                }


        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws NullPointerException {

                if(rg.getCheckedRadioButtonId()==R.id.euro_id){

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
                else if(rg.getCheckedRadioButtonId()==R.id.cad_id){
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
                else if(rg.getCheckedRadioButtonId()==R.id.gbp_id){
                    try{
                        float i=Float.parseFloat(et1.getText().toString());
                        float result= (float) (i*0.65);
                        float result1= (float) (Math.round(result*100.0)/100.0);
                        et2.setText(et1.getText().toString()+"USD="+result1+"GBP");


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
                else if(rg.getCheckedRadioButtonId()==R.id.jpy_id){
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
                else if(rg.getCheckedRadioButtonId()==R.id.clearall_id){
                    et2.setHint("1 USD= 0.84 EUR");

                }

            }
        });


    }
}
