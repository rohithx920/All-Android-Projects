package com.example.rohith.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderActivity extends AppCompatActivity {
    double topping_cost;
    double delivery_cost;
    HashMap<Integer, String> mymap = new HashMap<Integer, String>();
    String temp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mymap.put(0,"Bacon");
        mymap.put(1,"Cheese");
        mymap.put(2,"Garlic");
        mymap.put(3,"Green Pepper");
        mymap.put(4,"Mushroom");
        mymap.put(5,"Olives");
        mymap.put(6,"Onion");
        mymap.put(7,"Red pepper");
        mymap.put(8,"Tomato");
        setContentView(R.layout.activity_order);
        TextView toppings = (TextView) findViewById(R.id.ttoppings);
        TextView all_toppings = (TextView) findViewById(R.id.toppingsList);
        TextView delivery = (TextView) findViewById(R.id.delivery);
        TextView total = (TextView) findViewById(R.id.total);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey("Arraylist")) {
                ArrayList toppings_list = getIntent().getExtras().getIntegerArrayList("Arraylist");
                int size = toppings_list.size();
                topping_cost = 1.5 * size;
                toppings.setText("$" + topping_cost);
                int t=toppings_list.size()-1;
                for(int i=0;i<toppings_list.size();i++) {

                      temp=temp.concat(mymap.get(toppings_list.get(i)));
                    if(t>0){
                        temp = temp.concat(",");
                    }
                    t--;
                }
                all_toppings.setText(""+temp.toString());
            }
            if (getIntent().getExtras().containsKey("delivery_charges")) {
                delivery.setText("$2.0");
                delivery_cost = 2;
            }
            else{
                delivery.setVisibility(View.GONE);
                findViewById(R.id.textView7).setVisibility(View.GONE);
            }

            double result = 6.5 + topping_cost + delivery_cost;
            total.setText("$" + result);
        }
        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.DynamicLayout.removeAllViews();
                MainActivity.DynamicLayout2.removeAllViews();
                MainActivity.mylist.clear();
                MainActivity.pb.setProgress(0);
                MainActivity.delivery_check_box.setChecked(false);
                MainActivity.previous_order_check_box.setChecked(false);
                finish();
            }
        });
    }
}
