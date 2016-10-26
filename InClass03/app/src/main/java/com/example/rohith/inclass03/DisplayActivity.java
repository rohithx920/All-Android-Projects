package com.example.rohith.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DisplayActivity extends AppCompatActivity {
    final static String Name_Key="Edit";
    final static String Edit_Name="edit_name";
    final static String Edit_Email="edit_email";
    final static String Edit_Prog="edit_prog";
    final static int edit_em=102;

    final static String Sel_key="selection";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        final Student stud=(Student)getIntent().getExtras().getParcelable(MainActivity.Name_Key);

        final EditText name=(EditText)findViewById(R.id.Name_edit_id);
         EditText email=(EditText)findViewById(R.id.Email_edit_id);
        final EditText prog_lang=(EditText)findViewById(R.id.Prog_edit_id);
        name.setText(stud.name);
        email.setText(stud.emailAddress);
        prog_lang.setText(stud.fvprog);
        findViewById(R.id.edit_name_button_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent_call(stud,Edit_Name);

            }
        });
        findViewById(R.id.edit_email_button_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent_call(stud,Edit_Email);

            }
        });
        findViewById(R.id.edit_pl_button_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent_call(stud,Edit_Prog);

            }
        });


    }


    public void Intent_call(Student student, String sel){

        Intent i= new Intent(this,EditActivity.class);
        i.putExtra(Name_Key,student);
        i.putExtra(Sel_key,sel);
        startActivityForResult(i,101);
        //finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

       /* String n=sd1.name.toString();
        String e=sd1.emailAddress.toString();
        String f=sd1.fvprog.toString();
        Log.d("demo", "onActivityResult: "+n+e+f); */

        if(resultCode==101){
            //Log.d("checkcode", "-----"+9090);
            //Student sd1=data.getExtras().getParcelable(Edit_Name);
            final EditText name=(EditText)findViewById(R.id.Name_edit_id);
            final EditText email=(EditText)findViewById(R.id.Email_edit_id);
            final EditText prog_lang=(EditText)findViewById(R.id.Prog_edit_id);
            Student sd=data.getExtras().getParcelable(DisplayActivity.Edit_Name);
            name.setText(sd.name);

        }
        if(resultCode==102){
            //Log.d("Demo", "----- "+sd.emailAddress);
             EditText email=(EditText)findViewById(R.id.Email_edit_id);
            Student sd=data.getExtras().getParcelable(DisplayActivity.Edit_Email);
            email.setText(sd.emailAddress);

        }
        if(resultCode==103){
            final EditText email=(EditText)findViewById(R.id.Email_edit_id);
            EditText prog_lang=(EditText)findViewById(R.id.Prog_edit_id);
            Student sd=data.getExtras().getParcelable(DisplayActivity.Edit_Prog);
            prog_lang.setText(sd.fvprog);

        }
    }

}
