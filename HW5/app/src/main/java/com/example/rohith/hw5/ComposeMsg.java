package com.example.rohith.hw5;

import android.content.DialogInterface;
import android.os.*;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComposeMsg extends AppCompatActivity {

    public static List<String> listItems;
    TextView cont;
    TextView message_des;
    Map<String, User> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listItems=new ArrayList<String>();
        map=new HashMap<String, User>();
        setContentView(R.layout.activity_compose_msg);
        cont= (TextView) findViewById(R.id.contact_id);
        message_des= (TextView) findViewById(R.id.message_id);
        MainActivity.myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d("de", "" + dataSnapshot);

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //Log.d("999", "" + data);
                    User u = data.getValue(User.class);
                    listItems.add(u.getFirst_name()+" "+ u.getLast_name());

                    map.put(u.getFirst_name()+" "+u.getLast_name(),u);
                    Log.d("", "" + u.getFirst_name() + u.getLast_name());
                    Log.d("size", "onDataChange: "+listItems.size());

                }
                method();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        findViewById(R.id.send_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message=new Message();
                message.setMessage(message_des.getText().toString());
                message.setSender(MainActivity.user_name);
                message.setReceiver(cont.getText().toString());
                message.setTimestamp(ServerValue.TIMESTAMP);
                Log.d("0000", "onClick: "+message.getTimestamp());
                Log.d("time", "onClick: "+ServerValue.TIMESTAMP+"&&&&"+map.get(".sv"));
                String timeStamp = new String(String.valueOf(System.currentTimeMillis()));
                Log.d("7777", "onClick: "+timeStamp);
                //Sender
                MainActivity.myref.child(MainActivity.user_name).child("messages").child(cont.getText().toString())
                        .child(timeStamp).setValue(message);
                Log.d("8888", "onClick: "+map.get(cont.getText().toString()));
                //Recevier
                MainActivity.myref.child(String.valueOf(map.get(cont.getText().toString()).getUsername())).child("messages").child(MainActivity.user_name)
                        .child(timeStamp).setValue(message);
                Toast.makeText(ComposeMsg.this, "Message sent", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    void method() {
        final CharSequence[] items =listItems.toArray(new CharSequence[listItems.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Contact").
                setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.d("yyyy", items[item].toString());
                        cont.setText(items[item].toString());
                    }
                });

        final AlertDialog singleItemAlert = builder.create();
        ((ImageView) findViewById(R.id.pick_contact_id)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                singleItemAlert.show();
            }
        });
    }

}
