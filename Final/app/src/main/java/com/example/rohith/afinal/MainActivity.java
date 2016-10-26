package com.example.rohith.afinal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static FirebaseDatabase firebaseDatabase;
    static DatabaseReference myref;
    static DatabaseReference myref2;
    ArrayList<Person> listitems;
    ListView list;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase= FirebaseDatabase.getInstance();
        Log.d("values", "onCreate: "+firebaseDatabase);
        myref = firebaseDatabase.getReference("Gifts");
        myref2 = firebaseDatabase.getReference("Persons");
        list= (ListView) findViewById(R.id.listView);
        //myref.child("user1").setValue("hii");

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Christmas List");
        listitems = new ArrayList<Person>();
        getList();
        actionBar.setLogo(R.drawable.launcher);

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        myref2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getList();
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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("v2222", "onItemClick: "+i);
                Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(MainActivity.this,GiftList.class);
                Person p=listitems.get(i);
                i1.putExtra("person",p);
                startActivityForResult(i1,111);
            }
        });



    }

    void getList() {
        MainActivity.myref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("de", "" + dataSnapshot);

                //MainActivity.myref.orderByChild(");
                Log.d("child", "onDataChange: "+dataSnapshot.getChildrenCount());
                if (dataSnapshot.getChildrenCount() != 0) {
                        listitems.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Log.d("999", "" + data);
                        if (data.hasChildren()) {
                            //for (DataSnapshot m : data.getChildren()) {
                                //Log.d("jjj", "onDataChange: " + m);
                                Person mes = new Person();
                                mes.setPerson_name(data.child("person_name").getValue(String.class));
                                mes.setPerson_budget(data.child("person_budget").getValue(String.class));
                                mes.setPerson_spent(data.child("person_spent").getValue(String.class));
                                mes.setPerson_no_gifts(data.child("person_no_gifts").getValue(String.class));

                                //setReceiver(m.child("sender").getValue(String.class));
                                //mes.setMessage(m.child("message").getValue(String.class));
                                //Message u = m.getValue(Message.class);
                                listitems.add(mes);
                                //listItems.add(u.getFirst_name()+"" + u.getLast_name());
                                //Log.d("", "" + u.getMessage() + u.getSender());
                                //Log.d("size", "onDataChange: "+listItems.size());
                                /*Query myTopPostsQuery = MainActivity.myref.child(MainActivity.user_name)
                                        .child("messages")
                                        .orderByValue();
                                Log.d("query", "onDataChange:"+myTopPostsQuery); */
                            //}
                        }
                    }
                    viewValues();

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    void viewValues(){
            PersonAdapter ad = new PersonAdapter(MainActivity.this, R.layout.rowlayout, listitems);
            ad.setNotifyOnChange(true);
            list.setAdapter(ad);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflator=getMenuInflater();
        menuinflator.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.new_person :
                //Toast.makeText(Inbox.this, "Create new messege", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this,AddPerson.class);
                startActivityForResult(i,100);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            getList();
        }
        if(requestCode ==111){

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }
}
