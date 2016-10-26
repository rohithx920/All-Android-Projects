package com.example.rohith.p_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rohith on 6/14/2016.
 */
public class ColorAdapter extends ArrayAdapter<Color> {
    List<Color> mData;
    Context mContext;
    int mResource;
    public ColorAdapter(Context context, int resource, List<Color> objects) {
        super(context, resource, objects);
    this.mContext=context;
        this.mData=objects;
    this.mResource=resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView!=null){
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mResource,parent,false);
        }
        TextView textView= (TextView) convertView.findViewById(R.id.textView);
        TextView textView2= (TextView) convertView.findViewById(R.id.textView2);
        Color color=mData.get(position);
        textView.setText(color.color_name);
        textView2.setText(color.color_hex);
        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
