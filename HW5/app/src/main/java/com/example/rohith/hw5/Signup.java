package com.example.rohith.hw5;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity{
    EditText f_name,l_name,u_name,pwd,c_pwd;
    User user;
    DatabaseReference Ref;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //setTitle("Message Me!(Signup)");
        ActionBar actionbar=getSupportActionBar();
        //ImageView iv=new ImageView(this);
        actionbar.setTitle("Message Me!(Signup)");
        actionbar.setLogo(R.drawable.whatsapp);

        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        //iv.setImageResource(android.R.drawable.btn_star);
        //getActionBar().setCustomView(iv,new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        f_name= (EditText) findViewById(R.id.first_id);
        l_name= (EditText) findViewById(R.id.last_id);
        u_name= (EditText) findViewById(R.id.uname_id);
        pwd= (EditText) findViewById(R.id.signup_pwd_id);
        c_pwd= (EditText) findViewById(R.id.signup_con_pwd_id);
        Ref=MainActivity.myref;
        findViewById(R.id.Signup_id_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    createUserObject();
                    addDataToFirebase();
                }
            }
        });

    }
    public void createUserObject() {
        if (pwd.getText().toString().equals(c_pwd.getText().toString())) {
            user = new User();
            user.setFirst_name(f_name.getText().toString());
            user.setLast_name(l_name.getText().toString());
            user.setUsername(u_name.getText().toString());
            user.setPassword(pwd.getText().toString());
        }
    }
    public void addDataToFirebase(){
        Ref.child(u_name.getText().toString()).setValue(user);
        Toast.makeText(Signup.this, "data added", Toast.LENGTH_SHORT).show();
    }
    public boolean validate(){
        if(f_name.getText().toString().equals("") || l_name.getText().toString().equals("")
              ||u_name.getText().toString().equals("")||pwd.getText().toString().equals("") ){
            Toast.makeText(Signup.this, "Please fill the all details", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Ref.child(u_name.getText().toString())==null){
            Toast.makeText(Signup.this, "Does Not exists", Toast.LENGTH_SHORT).show();
        }
        Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(u_name.getText().toString()).exists()) {
                    Toast.makeText(Signup.this, "UserName Taken", Toast.LENGTH_SHORT).show();
                }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return true;


    }


}
