package com.example.rohith.p_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.Time;

public class Second_activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      String string= (String) getIntent().getExtras().getString(MainActivity.KEY);
      Double age= (Double) getIntent().getExtras().get(MainActivity.AGE);
        User u= (User) getIntent().getExtras().getSerializable("user_object");
        Non_User nu=getIntent().getExtras().getParcelable("non_user_object");
        Toast.makeText(Second_activity.this, ""+u.getId()+u.getName()+nu.getName()+
                nu.getEmail(), Toast.LENGTH_SHORT).show();
        Intent i=new Intent();
        i.putExtra(MainActivity.KEY,"hello");
        setResult(RESULT_OK,i);
        finish();

    }
}
