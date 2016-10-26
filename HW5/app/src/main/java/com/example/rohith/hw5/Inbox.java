package com.example.rohith.hw5;

import android.content.Intent;
import android.os.*;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;

public class Inbox extends AppCompatActivity {
ArrayList<Message> listitems;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Inbox");
        actionBar.setLogo(R.drawable.whatsapp);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        list= (ListView) findViewById(R.id.listView);
        listitems = new ArrayList<Message>();
        getList();

    }
    void viewMessages(){

        //Log.d("", "viewMessages: "+ ServerValue.TIMESTAMP);
        MessageAdapter ad = new MessageAdapter(Inbox.this, R.layout.rowlayout, listitems);
        ad.setNotifyOnChange(true);
        list.setAdapter(ad);


    }
    void getList(){
        MainActivity.myref.child(MainActivity.user_name).child("messages").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("de", "" + dataSnapshot);

                //MainActivity.myref.orderByChild(");
                if (dataSnapshot.getChildrenCount() != 0) {
                    listitems.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Log.d("999", "" + data);
                        if (data.hasChildren()) {
                            for (DataSnapshot m : data.getChildren()) {
                                Log.d("jjj", "onDataChange: "+m);
                                Message mes=new Message();
                                mes.setReceiver(m.child("sender").getValue(String.class));
                                mes.setMessage(m.child("message").getValue(String.class));
                                //Message u = m.getValue(Message.class);
                                listitems.add(mes);
                                //listItems.add(u.getFirst_name()+"" + u.getLast_name());
                                //Log.d("", "" + u.getMessage() + u.getSender());
                                //Log.d("size", "onDataChange: "+listItems.size());
                                Query myTopPostsQuery = MainActivity.myref.child(MainActivity.user_name)
                                        .child("messages")
                                        .orderByValue();
                                Log.d("query", "onDataChange:"+myTopPostsQuery);
                            }
                        }
                    }
                    viewMessages();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflator=getMenuInflater();
        menuinflator.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_id :
                Toast.makeText(Inbox.this, "Create new messege", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this,ComposeMsg.class);
                startActivity(i);
                break;
            case R.id.menu_id2:
                Toast.makeText(Inbox.this, "Refresh", Toast.LENGTH_SHORT).show();
                getList();
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
    }
}
