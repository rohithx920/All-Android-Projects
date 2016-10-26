package com.example.rohith.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Student stud=(Student)getIntent().getExtras().getParcelable(DisplayActivity.Name_Key);
        final EditText name=(EditText)findViewById(R.id.username_id);
        final EditText email=(EditText)findViewById(R.id.email_id);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.Radio_g_id);
        TextView fav=(TextView)findViewById(R.id.fav_id);
        String valdations= (String) getIntent().getExtras().get(DisplayActivity.Sel_key);
        Button save=(Button)findViewById(R.id.save_id);

        if(valdations.equals(DisplayActivity.Edit_Name)){
            email.setVisibility(View.GONE);
            rg.setVisibility(View.GONE);
            fav.setVisibility(View.GONE);
            Log.d("demo", ""+stud);
        }
        else if(valdations.equals(DisplayActivity.Edit_Email)){
            name.setVisibility(View.GONE);
            //email.setVisibility(View.GONE);
            rg.setVisibility(View.GONE);
            fav.setVisibility(View.GONE);
        }
        else if(valdations.equals(DisplayActivity.Edit_Prog)){
            name.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            //rg.setVisibility(View.GONE);

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (getIntent().getExtras().get(DisplayActivity.Sel_key).equals(DisplayActivity.Edit_Name)) {
                        Student stud=(Student)getIntent().getExtras().getParcelable(DisplayActivity.Name_Key);
                        stud.name = name.getText().toString();
                        Intent intent = new Intent();
                        intent.putExtra(DisplayActivity.Edit_Name,stud);
                        setResult(101,intent);
                        finish();
                        //intent.putExtra(MainActivity.Name_Key, stud);
                        //startActivity(intent);
                     //   finish();
                    } else if (getIntent().getExtras().get(DisplayActivity.Sel_key).equals(DisplayActivity.Edit_Email)) {
                        Student stud=(Student)getIntent().getExtras().getParcelable(DisplayActivity.Name_Key);
                        Log.d("demooooooo!!!!!!!!!",email.getText().toString());
                        Log.d("demo!!!!!!!!",stud.emailAddress.toString());
                        stud.emailAddress = email.getText().toString();
                        Intent intent = new Intent();
                        intent.putExtra(DisplayActivity.Edit_Email,stud);
                        setResult(DisplayActivity.edit_em,intent);
                        finish();
                    } else if (getIntent().getExtras().get(DisplayActivity.Sel_key).equals(DisplayActivity.Edit_Prog)) {
                       // final int radioid=rg.getCheckedRadioButtonId();
                        Student stud=(Student)getIntent().getExtras().getParcelable(DisplayActivity.Name_Key);
                        if(rg.getCheckedRadioButtonId()==R.id.Java_id){
                            stud.fvprog="Java";
                            Intent intent = new Intent();
                            intent.putExtra(DisplayActivity.Edit_Prog,stud);
                            setResult(103,intent);
                            finish();
                        }
                        else if(rg.getCheckedRadioButtonId()==R.id.c_id){
                            stud.fvprog="C";
                            Intent intent = new Intent();
                            intent.putExtra(DisplayActivity.Edit_Prog,stud);
                            setResult(103,intent);
                            finish();
                        }
                        else if(rg.getCheckedRadioButtonId()==R.id.c_shark_id){
                            Log.d("#####",stud.fvprog.toString());
                            stud.fvprog="C#";
                            Log.d("###",stud.fvprog.toString());
                            Intent intent = new Intent();
                            intent.putExtra(DisplayActivity.Edit_Prog,stud);
                            setResult(103,intent);
                            finish();
                        }

                    } else {
                        Log.d("demo", "nothing else");
                    }
                }
                catch (Exception e){
                    Log.d("demo", "ddaf "+e);
                }


            }
        });



    }
}
