package com.example.rohith.hw2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AlertDialog.Builder builder;
    String[] items = {"Beacon", "Cheese", "Garlic", "Green Pepper","Mushroom","Olives"
    ,"Onion","Red Pepper","Tomato"};
    static ArrayList mylist=new ArrayList();
    //ArrayList my_saved_list=new ArrayList();
    //HashMap<Integer,String> mymap=HashMap<Integer,String>();
    HashMap<Integer, Integer> mymap = new HashMap<Integer, Integer>();
    final String TAG="demo";
    String array_values="";
    static TableLayout DynamicLayout;
    static LinearLayout DynamicLayout2;
    static CheckBox delivery_check_box,previous_order_check_box;
     //LinearLayout DynamicLayout;
    ImageView imageview;
    static ProgressBar pb;
    //static TableRow tableRow=null;
   // TableRow tableRow, tableRow2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle("Pizza Store");
        ActionBar actionbar=getSupportActionBar();
        actionbar.setLogo(R.drawable.app_icon);
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setTitle("Pizza Store");
        actionbar.setDisplayHomeAsUpEnabled(true);
        //getActionBar().setIcon(R.drawable.app_icon);
        DynamicLayout= (TableLayout) findViewById(R.id.Table_id);
        DynamicLayout2= (LinearLayout) findViewById(R.id.dynamic_2);
        DynamicLayout2.setOrientation(LinearLayout.HORIZONTAL);
        DynamicLayout.setOrientation(LinearLayout.HORIZONTAL);
        mymap.put(0,R.drawable.bacon);
        mymap.put(1,R.drawable.cheese);
        mymap.put(2,R.drawable.garlic);
        mymap.put(3,R.drawable.green_pepper);
        mymap.put(4,R.drawable.mushroom);
        mymap.put(5,R.drawable.olives);
        mymap.put(6,R.drawable.onion);
        mymap.put(7,R.drawable.red_pepper);
        mymap.put(8,R.drawable.tomato);
        delivery_check_box= (CheckBox) findViewById(R.id.delivery_id);

        pb=(ProgressBar)findViewById(R.id.progressBar2);
        pb.setMax(10);
        builder=new AlertDialog.Builder(MainActivity.this)
                .setTitle("Choose a Topping").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        //Log.d(TAG, "onClick: "+);
                        Log.d(TAG, items[item].toString());
                        if (pb.getProgress() <= 9) {
                            Log.d(TAG, "onClick: "+ items[item].toString().toLowerCase());
                            String str= items[item].toString().toLowerCase();
                            mylist.add(item);
                            pb.incrementProgressBy(1);
                            addToppings();

                        }
                       /* else{
                            Toast.makeText(MainActivity.this, "Maximum capacity!", Toast.LENGTH_SHORT).show();
                        } */
                    }
                });
        final AlertDialog singleItemAlert = builder.create();

        findViewById(R.id.add_topping_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mylist.size()<10) {
                    singleItemAlert.show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Maximum capacity!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        findViewById(R.id.clear_pizza_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setProgress(0);
                mylist.clear();
                delivery_check_box.setChecked(false);
                previous_order_check_box.setChecked(false);
                addToppings();

            }
        });
        findViewById(R.id.checkout_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "888888");
                Intent i = new Intent(getBaseContext(), OrderActivity.class);
                i.putExtra("Arraylist",mylist);
                Log.d(TAG, "lskkafkla+fansnf+size "+mylist.size());
                array_values="";
                for(int j=0;j<mylist.size();j++){
                    Log.d(TAG, "value:"+String.valueOf(mylist.get(j)));
                    array_values=array_values.concat(String.valueOf(mylist.get(j)));
                    Log.d(TAG, "99999995"+array_values);
                    Log.d(TAG, "values"+mylist.get(j).toString());
                }
                if(delivery_check_box.isChecked()){
                 i.putExtra("delivery_charges","$2.0");
                }
                startActivity(i);
                SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("myarrayvalues",array_values);
                editor.putString("delivery_details", String.valueOf(delivery_check_box.isChecked()));
                Log.d("###############",""+array_values);
                editor.commit();
            }
        });
        previous_order_check_box= (CheckBox) findViewById(R.id.previous_order_id);
        previous_order_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                    //String defaultValue = getResources().getString("");
                    Log.d(TAG, "5555555"+array_values);
                    String values = sharedPref.getString("myarrayvalues","");
                    String Previous_delivery_details = sharedPref.getString("delivery_details","");
                    if(Previous_delivery_details.equals("true")){
                        delivery_check_box.setChecked(true);
                    }
                    else{
                        delivery_check_box.setChecked(false);
                    }
                    Log.d(TAG, "values9999999"+values.length());
                    mylist.clear();
                    if(values.length()==0){
                        Toast.makeText(MainActivity.this, "There is no previous order to load!”", Toast.LENGTH_SHORT).show();
                    }
                    pb.setProgress(values.length());
                    for(int k=0; k<values.length();k++) {
                        int tr=(int)values.charAt(k);
                        int tr1=Character.getNumericValue(tr);
                        Log.d(TAG, ""+tr1);
                        mylist.add(tr1);
                        for(int l=0;l<mylist.size();l++){
                            Log.d(TAG, "mylist_values"+mylist.get(l));
                        }

                        addToppings();
                    }
                }

            }
        });


    }
    public void addToppings() {
        DynamicLayout.removeAllViews();
        DynamicLayout2.removeAllViews();
        LinearLayout a = new LinearLayout(MainActivity.this);;
        LinearLayout b = new LinearLayout(MainActivity.this);;
        Log.d(TAG, "size" + mylist.size());
        for (int i = 0; i < mylist.size(); i++) {
            if (a.getChildCount() < 5) {
                int temp = (int) mylist.get(i);
                Log.d(TAG, "index: "+temp);
                imageview = new ImageView(MainActivity.this);
                imageview.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
                imageview.setImageResource(mymap.get(temp));
                imageview.setTag(i);
                a.setOrientation(LinearLayout.HORIZONTAL);
                a.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                a.addView(imageview);
                imageview.setOnClickListener(MainActivity.this);
            }
            else {
                int temp = (int) mylist.get(i);
                Log.d(TAG, "---------------------");
                Log.d(TAG, "index: "+temp);
                imageview = new ImageView(MainActivity.this);
                imageview.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
                imageview.setImageResource(mymap.get(temp));
                imageview.setTag(i);
                b.setOrientation(LinearLayout.HORIZONTAL);
                b.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                b.addView(imageview);
                imageview.setOnClickListener(MainActivity.this);
            }


        }
        DynamicLayout.addView(a);
        DynamicLayout2.addView(b);
    }

    @Override
    public void onClick(View v) {
        for(int i=0;i<mylist.size();i++) {
            int p= (int) mylist.get(i);
            Log.d("****",""+p);
        }
        Log.d("*********************",v.getTag()+"");
       pb.incrementProgressBy(-1);

        Log.d(TAG, "onClick: "+mylist.size());
        mylist.remove((int)v.getTag());
        Log.d(TAG, "onClick: "+mylist.size());
        addToppings();
    }

}



