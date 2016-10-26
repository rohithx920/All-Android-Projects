package com.example.rohith.google_map_test;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rohith on 7/20/2016.
 */
public class MapAdapter {
    Context mcontext;
    int mresource;
    List<Map> movieList;/*

    public MapAdapter(Context context, int resource, List<Map> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
        movieList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mresource, parent, false);
        }
        final EditText name = (EditText) convertView.findViewById(R.id.editText3);
        //final TextView budget = (TextView) convertView.findViewById(R.id.textView5);
        //final TextView gifts= (TextView) convertView.findViewById(R.id.textView6);

        int size=movieList.size();
        Map m=movieList.get(position);

        name.setText(m.getSource());

        Log.d("++++", " "+m.getPerson_spent().trim());
        gifts.setText(m.getPerson_no_gifts()+"gifts bought");
        if(m.getPerson_budget()!=null){
            if(m.getPerson_spent().equals("0")){
                String s=m.getPerson_spent().trim()+"/"+m.getPerson_budget().trim();
                Log.d("temp", ""+s);
                budget.setText(s);
                budget.setTextColor(Color.GRAY);
                Log.d("6", "getView: ");
            }
            else if(Integer.parseInt(m.getPerson_spent().toString())< Integer.parseInt(m.getPerson_budget())){
                budget.setText(m.getPerson_spent().trim()+"/"+m.getPerson_budget().trim());
                budget.setTextColor(Color.RED);
                Log.d("7", "getView: ");
            }
            else if(Integer.parseInt(m.getPerson_spent().toString())== Integer.parseInt(m.getPerson_budget())){
                budget.setText(m.getPerson_spent().trim()+"/"+m.getPerson_budget().trim());
                budget.setTextColor(Color.GREEN);
                Log.d("8", "getView: ");
            }
        }

        // budget.setText(m.getPerson_budget());

        return convertView;
    }*/
}
