package com.example.rohith.afinal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiftList extends AppCompatActivity {
    ListView list;
    ArrayList<Gift> mylist;
    Person p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list);
        p= (Person) getIntent().getExtras().get("person");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(p.getPerson_name());
        mylist = new ArrayList<Gift>();
        //getList();
        //actionBar.setLogo(R.drawable.launcher);
        //MainActivity.myref2.child("")
        list= (ListView) findViewById(R.id.listView3);
        getListOnCreate();
        method();
    }
    void getListOnCreate(){
        MainActivity.myref2.child(p.getPerson_name()).child("Gifts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("de", "" + dataSnapshot);
                int temp_counting_budget=0;
                if (dataSnapshot.getChildrenCount() != 0) {
                    mylist.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Log.d("999", "" + data);
                        if (data.hasChildren()) {
                            //for (DataSnapshot m : data.getChildren()) {
                            //Log.d("jjj", "onDataChange: " + m);
                            Gift gift=new Gift();
                            gift.setGift(data.child("gift").getValue(String.class));
                            gift.setPrice(data.child("price").getValue(String.class));
                            gift.setImage(data.child("image").getValue(String.class));

                            //setReceiver(m.child("sender").getValue(String.class));
                            //mes.setMessage(m.child("message").getValue(String.class));
                            //Message u = m.getValue(Message.class);
                            //listitems.add(mes);
                            //temp_counting_budget=
                            mylist.add(gift);
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
                    //viewValues();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void method(){
        if(mylist!=null){
            GiftAdapter ad = new GiftAdapter(this, R.layout.row_layout_2, mylist);
            ad.setNotifyOnChange(true);
            list.setAdapter(ad);
            saveGiftstoFirebase();

        }
    }
    public void saveGiftstoFirebase(){
        int size=mylist.size();
        if(size!=0)
        MainActivity.myref2.child(p.getPerson_name()).child("Gifts").child(mylist.get(size-1).getGift()).setValue(mylist.get(size-1));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflator=getMenuInflater();
        menuinflator.inflate(R.menu.add_gift_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_gift :
                //Toast.makeText(Inbox.this, "Create new messege", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this,AddGift.class);
                i.putExtra("person",p);
                startActivityForResult(i,123);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){
            if(data!=null) {
                if(resultCode==RESULT_OK) {
                    Gift g = (Gift) data.getExtras().get("GIFT");
                    mylist.add(g);
                    int value = Integer.parseInt(p.getPerson_spent()) + Integer.parseInt( g.getPrice());
                    Log.d("", "onActivityResult: "+value);
                    int no_gifts=Integer.parseInt(p.getPerson_no_gifts()) +1;
                    Log.d("", "onActivityResult: "+no_gifts);
                    p.setPerson_spent(""+value);
                    p.setPerson_no_gifts(""+no_gifts);
                    MainActivity.myref2.child(p.getPerson_name()).child("person_no_gifts").setValue(p.getPerson_no_gifts());
                    MainActivity.myref2.child(p.getPerson_name()).child("person_spent").setValue(p.getPerson_spent());
                    method();
                }
                else{
                    Toast.makeText(GiftList.this, "else", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getListOnCreate();
        method();
    }
    /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent l=new Intent();
        l.putExtra("update",)
        finish();
    } */
}
