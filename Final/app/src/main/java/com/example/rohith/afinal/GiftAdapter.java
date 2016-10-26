package com.example.rohith.afinal;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rohith on 6/28/2016.
 */
public class GiftAdapter extends ArrayAdapter<Gift> {
    Context mcontext;
    int mresource;
    List<Gift> movieList;

    public GiftAdapter(Context context, int resource, List<Gift> objects) {
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
         TextView name = (TextView) convertView.findViewById(R.id.textView7);
         TextView budget = (TextView) convertView.findViewById(R.id.textView8);
         ImageView iv= (ImageView) convertView.findViewById(R.id.imageView2);

        int size=movieList.size();
        Gift m=movieList.get(position);
        Log.d("###", "getView: "+m.getGift()+m.getPrice()+m.getImage());
        name.setText(m.getGift().toString());
        budget.setText(m.getPrice().toString());
        Picasso.with(mcontext).load(m.getImage().toString()).into(iv);


        //budget.setText(m.getPerson_budget());

        return convertView;
    }
}
