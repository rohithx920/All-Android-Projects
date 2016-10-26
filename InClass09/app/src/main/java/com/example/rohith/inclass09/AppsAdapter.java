package com.example.rohith.inclass09;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by This on 6/23/16.
 */
public class AppsAdapter extends ArrayAdapter<TopApps> {
        List<TopApps> mData;
        Context mContext;
        int mResource;
        int value;
        public AppsAdapter(Context context, int resource, List<TopApps> objects,int value) {
            super(context, resource, objects);
            this.mContext=context;
            this.mData=objects;
            this.mResource=resource;
            this.value=value;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(mResource,parent,false);
            }
            TextView textView= (TextView) convertView.findViewById(R.id.textView);
            TextView textView2= (TextView) convertView.findViewById(R.id.textView2);
            TextView textView3= (TextView) convertView.findViewById(R.id.textView3);
            TextView textView4= (TextView) convertView.findViewById(R.id.textView4);
            TextView textView5= (TextView) convertView.findViewById(R.id.textView5);
            ImageView image= (ImageView) convertView.findViewById(R.id.imageView);
            ImageView image_fav= (ImageView) convertView.findViewById(R.id.imageView3);

            TopApps topApps=mData.get(position);
            textView.setText(topApps.getAppname());
            textView2.setText(topApps.getDevloper_name());
            Log.d("hello", "getView: "+topApps.getDevloper_name());
            textView3.setText(topApps.getPrice());
            textView4.setText(topApps.getRelease_Date());
            textView5.setText(topApps.getCategory());
            if(topApps.getFav()==0)
                image_fav.setImageResource(R.drawable.favoritesgrey);
            else
                image_fav.setImageResource(R.drawable.favoritesyellow);
            if(value==1){
                image_fav.setImageResource(R.drawable.favoritesyellow);
            }

            Picasso.with(mContext).load(topApps.getImage()).into(image);        //return super.getView(position, convertView, parent);
            //new GetImage((MainActivity) mContext).execute(topApps.getImage());
            return convertView;
        }
    }


