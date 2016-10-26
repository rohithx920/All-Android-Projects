package com.example.rohith.p_intents;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static String KEY = "key";
    static int REQ_CODE = 1000;
    static String AGE = "age";
    final CharSequence[] arr={"1","2","3"};
     ProgressDialog pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_id);
        Button b = new Button(this);
        b.setText("Second Activity");
        b.setId(R.id.but_id);
        b.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        relativeLayout.addView(b);
        b.setOnClickListener(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.but_id);
        Button b2 = new Button(this);
        b2.setText("no Activity");
        b2.setId(R.id.but2_id);
        b2.setLayoutParams(lp);
        relativeLayout.addView(b2);
        b2.setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        pb=new ProgressDialog(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                String s = data.getExtras().getString(KEY);


                Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.but_id) {
            Intent intent = new Intent(getApplicationContext(), Second_activity.class);
            intent.putExtra(KEY, "This is Extra");
            intent.putExtra(AGE, (double) 22);
            User user = new User(10, "roh");
            intent.putExtra("user_object", user);
            Non_User nonuser = new Non_User("ramu", "ramu@1");
            intent.putExtra("non_user_object", nonuser);
            startActivityForResult(intent, REQ_CODE);
        }
        if (v.getId() == R.id.but2_id) {
            Toast.makeText(MainActivity.this, "Implicit Intent", Toast.LENGTH_SHORT).show();

        }
        if(v.getId()==R.id.button4){
            final AlertDialog.Builder b=new AlertDialog.Builder(this);
            b.setTitle("HI").setSingleChoiceItems(arr,-1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pb.setTitle("wait");
                    pb.show();
                }
            });
            final AlertDialog a=b.create();
            a.show();
        }
    }
}
