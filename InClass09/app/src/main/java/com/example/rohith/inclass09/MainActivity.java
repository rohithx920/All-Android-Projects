package com.example.rohith.inclass09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String TAG = "demo";
    static DatabaseReference myRef;
    FirebaseDatabase database;
    FirebaseAuth auth;
    Button createacc_button, login;
    EditText emailET, passwordET;
    boolean isAlreadySaved = false;
    boolean isAlreadySaved2 = false;
    static String email_fav;
    MainActivity main;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        main=this;
        //writeNewUser("ruuu", "b666", "1233");
        emailET = (EditText) findViewById(R.id.editText);
        passwordET = (EditText) findViewById(R.id.editText2);
        createacc_button = (Button) findViewById(R.id.create_acc_button);
        //final Firebase myFirebaseRef = new Firebase("https://<YOUR-FIREBASE-APP>.firebaseio.com/");
        Firebase.setAndroidContext(this);
        final Firebase rootRef = new Firebase("https://console.firebase.google.com/project/inclass09-2c16f/database/data");
        rootRef.child("Users");
        createacc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*boolean user2=writeNewUser("", emailET.getText().toString(), passwordET.getText().toString());
                if(user2==false) {
                    Intent acc_it = new Intent(MainActivity.this, Login.class);
                    acc_it.putExtra("email",emailET.getText().toString());
                    acc_it.putExtra("pwd",passwordET.getText().toString());
                    startActivityForResult(acc_it,100);
                }*/
              /*  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail();
              mGoogleApiClient=new GoogleApiClient.Builder(this)
                      .enableAutoManage(this,this); */


/*                rootRef.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                    }
                });
*/
            }
        });


        login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailET.getText().toString().equals("") || passwordET.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter values", Toast.LENGTH_SHORT).show();
                } else {
                    myRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String d = dataSnapshot.toString();
                            isAlreadySaved2 = dataSnapshot.child(emailET.getText().toString()).exists();
                            Log.d(TAG, "onDataChange: " + isAlreadySaved);
                            if (isAlreadySaved2 == true) {
                                String st=dataSnapshot.child(emailET.getText().toString()).child("Pwd").getValue(String.class);
                                String st2=passwordET.getText().toString();
                                if(st.equals(passwordET.getText().toString())){
                                    Intent app_it = new Intent(getApplicationContext(), AppsList.class);
                                    email_fav=emailET.getText().toString();
                                    startActivity(app_it);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Mismatach", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Account does Not exists", Toast.LENGTH_SHORT).show();
                            }
                            //Log.d(TAG, "Value is: " + value);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });




                }
            }
        });

    }

    public void createNewAccount() {

    }

    public void loginToApp() {
        emailET = (EditText) findViewById(R.id.editText);
        passwordET = (EditText) findViewById(R.id.editText2);



    }


    private boolean writeNewUser(String name, final String email, String pwd) {
        Log.d(TAG, "writeNewUser: " + myRef.child(email).getDatabase());
        final String name2 = name;
        final String pwd2 = pwd;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String d = dataSnapshot.toString();
                //String em=email;
                isAlreadySaved = dataSnapshot.child(email).exists();
                Log.d(TAG, "onDataChange: " + isAlreadySaved);
                if (isAlreadySaved == true) {
                    Toast.makeText(MainActivity.this, "User Already exists", Toast.LENGTH_SHORT).show();

                }
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    return isAlreadySaved;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            emailET.setText("");
            passwordET.setText("");
        }
    }

}

    /*private void writeNewUser(User user) {
        myRef.child(user.getId()).setValue(user);
    }
*/




