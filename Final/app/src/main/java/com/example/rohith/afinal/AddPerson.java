package com.example.rohith.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddPerson extends AppCompatActivity {
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        et1= (EditText) findViewById(R.id.editText);
        et2= (EditText) findViewById(R.id.editText2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflator=getMenuInflater();
        menuinflator.inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_person :
                //Toast.makeText(Inbox.this, "Create new messege", Toast.LENGTH_SHORT).show();
                String name=et1.getText().toString();
                String budget=et2.getText().toString();
                if(name.equals("")||budget.equals("")){
                    Toast.makeText(AddPerson.this, "please enter values", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(budget)==0||Integer.parseInt(budget)<0){
                    Toast.makeText(AddPerson.this, "Enter valid budget", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Add to firebase
                    Person p=new Person();
                    p.setPerson_name(name);
                    p.setPerson_budget(budget);
                    p.setPerson_icon("default");
                    p.setPerson_no_gifts("0");
                    p.setPerson_spent("0");
                    Log.d("TAG:", "onOptionsItemSelected: "+MainActivity.myref);
                    //MainActivity.firebaseDatabase.getReference("Persons").child(name).setValue(p);
                    MainActivity.myref2.child(name).setValue(p);

                    finish();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
