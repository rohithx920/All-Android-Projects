package com.example.rohith.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddGift extends AppCompatActivity {
    ArrayList<Gift> listitems;
    ListView list;
    Person person;
    int amount_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift);
        listitems=new ArrayList<Gift>();
        list= (ListView) findViewById(R.id.listView2);
        person= (Person) getIntent().getExtras().getSerializable("person");
        try {
            amount_left = Integer.parseInt(person.getPerson_budget()) - Integer.parseInt(person.getPerson_spent());
            Log.d("amount", ""+amount_left);
        }
        catch(Exception e){
            Log.d("", "Exception"+e);
        }
        MainActivity.myref.addListenerForSingleValueEvent(new ValueEventListener() {
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
                            Gift mes = new Gift();
                            //mes.getGift(data.child("gift").getValue(String.class));
                            mes.setGift(data.child("gift").getValue(String.class));
                            mes.setImage(data.child("imageUrl").getValue(String.class));
                            mes.setPrice(data.child("price").getValue(Long.class).toString());
                            if(Integer.parseInt(mes.getPrice())<=amount_left) {
                                listitems.add(mes);
                            }
                        }
                    }
                    viewValues();

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent u=new Intent();
                Gift g=new Gift();
                g=listitems.get(i);
                int prev=Integer.parseInt(person.getPerson_spent());
                prev=prev+Integer.parseInt(g.getPrice());
                int no_gifts=Integer.parseInt(person.getPerson_no_gifts());
                person.setPerson_spent(String.valueOf(prev));
                person.setPerson_no_gifts(String.valueOf(no_gifts+1));
                //updateValuesToFirebase();
                Log.d("4444", "onItemClick: "+g.getGift());
                u.putExtra("GIFT",g);
                setResult(RESULT_OK, u);
                finish();
            }
        });


    }
    /*public void updateValuesToFirebase() {
        MainActivity.myref2.child(person.getPerson_name()).child("Gifts").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    MainActivity.myref2.child(person.getPerson_name()).child("person_no_gifts").setValue(person.getPerson_no_gifts());
                    MainActivity.myref2.child(person.getPerson_name()).child("person_spent").setValue(person.getPerson_spent());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
    void viewValues(){
        Log.d("@@@@", "viewValues: "+listitems.size());
        GiftAdapter ad = new GiftAdapter(AddGift.this, R.layout.row_layout_2, listitems);
        ad.setNotifyOnChange(true);
        list.setAdapter(ad);

    }

}

