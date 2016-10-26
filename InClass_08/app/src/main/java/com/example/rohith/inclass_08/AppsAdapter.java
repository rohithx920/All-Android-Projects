package com.example.rohith.inclass_08;

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
 * Created by Rohith on 6/14/2016.
 */
public class AppsAdapter extends ArrayAdapter<TopApps> {
    List<TopApps> mData;
    Context mContext;
    int mResource;
    public AppsAdapter(Context context, int resource, List<TopApps> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mData=objects;
        this.mResource=resource;

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

         TopApps topApps=mData.get(position);
        textView.setText(topApps.getAppname());
        textView2.setText(topApps.getDevloper_name());
        Log.d("hello", "getView: "+topApps.getDevloper_name());
        textView3.setText(topApps.getPrice());
        textView4.setText(topApps.getRelease_Date());
        textView5.setText(topApps.getCategory());


        Picasso.with(mContext).load(topApps.getImage()).into(image);        //return super.getView(position, convertView, parent);
        //new GetImage((MainActivity) mContext).execute(topApps.getImage());
        return convertView;
    }
}
