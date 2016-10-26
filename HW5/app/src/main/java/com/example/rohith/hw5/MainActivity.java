package com.example.rohith.hw5;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
FirebaseDatabase firebaseDatabase;
    static DatabaseReference myref;
    EditText UserName_login,Password_login;
    static String user_name;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle("Message Me!");
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        //String defaultValue = getResources().get("User");
        String valid_user = sharedPref.getString("User",null);
        if(valid_user!=null){
            Intent i=new Intent(MainActivity.this,Inbox.class);
            user_name=valid_user;
            startActivity(i);
            finish();
        }
        /* To clear shared pref content

        SharedPreferences.Editor.clear();
        commit();
         */
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Message Me!");
        actionBar.setLogo(R.drawable.whatsapp);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);

        //setActionBar(new Toolbar(this,));
        //getActionBar().setCustomView(iv,new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        firebaseDatabase=FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Users");

        UserName_login= (EditText) findViewById(R.id.username_id);
        Password_login= (EditText) findViewById(R.id.pwd_id);
        findViewById(R.id.login_id).setOnClickListener(this);
        findViewById(R.id.new_user_id).setOnClickListener(this);
    }
    public void sharedPref(){
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("User", user_name);
        Log.d("", "sharedPref: ");
        editor.commit();
    }
    public void signup(){
        Intent intent=new Intent(this,Signup.class);
        startActivity(intent);
    }
    public void login(){
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("demo", "onDataChange: "+dataSnapshot);
                if(dataSnapshot.child(UserName_login.getText().toString()).exists()){
                    String pwd=dataSnapshot.child(UserName_login.getText().toString()).child("password").getValue(String.class);
                    if(pwd.equals(Password_login.getText().toString())){
                        Intent i=new Intent(MainActivity.this,Inbox.class);
                        user_name=UserName_login.getText().toString();
                        sharedPref();
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "User does not exist, Plese signup", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, ""+databaseError, Toast.LENGTH_SHORT).show();
            }
        });
        //myref.child(UserName_login.getText().toString())
    }

    @Override
    public void onClick(View view) {
        Log.d("", "onClick: "+view.getId());
        switch (view.getId()) {
            case R.id.login_id:
                login();
                break;
            case R.id.new_user_id:
                signup();
                break;
            default:
                Toast.makeText(MainActivity.this, "Unknown", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
