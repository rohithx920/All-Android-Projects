package com.example.rohith.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
final static String TAG="demo";
    final static String Name_Key="name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText user=(EditText) findViewById(R.id.username_id);
        final EditText email=(EditText) findViewById(R.id.email_id);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.Radio_g_id);

        //final int radioid=rg.getCheckedRadioButtonId();
findViewById(R.id.submit_id).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (user.getText().length() == 0 || email.getText().length() == 0) {
            Toast.makeText(MainActivity.this, "Enter values", Toast.LENGTH_SHORT).show();

        } else {
            if (rg.getCheckedRadioButtonId() == R.id.Java_id) {
                Student std = new Student(user.getText().toString(), email.getText().toString(), "Java");
                Intent_call(std);
                Log.d(TAG, "------- " + "java");

            } else if (rg.getCheckedRadioButtonId() == R.id.c_id) {
                Student std = new Student(user.getText().toString(), email.getText().toString(), "C");
                Intent_call(std);

            } else if (rg.getCheckedRadioButtonId() == R.id.c_shark_id) {
                Student std = new Student(user.getText().toString(), email.getText().toString(), "C#");
                Intent_call(std);

            } else {
                Log.d(TAG, "onCreate: ");
            }


        }
    }
});

    ;
    }

    public void Intent_call(Student student){

        Intent i= new Intent(this,DisplayActivity.class);
        i.putExtra(Name_Key,student);
        startActivity(i);


    }
}
