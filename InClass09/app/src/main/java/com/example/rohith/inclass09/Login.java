package com.example.rohith.inclass09;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

/**
 * Created by This on 6/23/16.
 */
public class Login extends AppCompatActivity{


    Button signup_button, cancel_button;
    EditText name,email,pwd;
    String Name_entered,Email_entered,pwd_entered;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name= (EditText) findViewById(R.id.fullname_input_id);
        email= (EditText)findViewById(R.id.email_input_id);
        pwd= (EditText)findViewById(R.id.password_input_id);
        email.setText(getIntent().getExtras().get("email").toString());;
        pwd.setText(getIntent().getExtras().get("pwd").toString());
        //getIntent().getExtras().get("pwd");
        cancel_button = (Button)findViewById(R.id.cancel_id);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        signup_button= (Button) findViewById(R.id.signup_id);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Account is created", Toast.LENGTH_SHORT).show();
                Name_entered=name.getText().toString();
                Email_entered=email.getText().toString();
                pwd_entered=pwd.getText().toString();
                MainActivity.myRef.child(Email_entered);


                MainActivity.myRef.child(Email_entered).child("Name").setValue(Name_entered);
                //myRef.child("user").child("Email").push().setValue(email);
                MainActivity.myRef.child(Email_entered).child("Pwd").setValue(pwd_entered);

                Intent i=new Intent();
                finish();
            }

        });
    }


}
