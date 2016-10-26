package com.example.rohith.inclass_08;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Preview_Fragment extends Fragment {
    Activity activity;
    String image_url;
    String name;

    public Preview_Fragment() {
        // Required empty public constructor
    }
    public Preview_Fragment(Activity activity,String image_url,String name)
    {
        this.activity=activity;
        this.image_url=image_url;
        this.name=name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_preview, container, false);
        ImageView imageView= (ImageView) view.findViewById(R.id.imageView2);
        TextView textView = (TextView) view.findViewById(R.id.textView6);
        Picasso.with(activity).load(image_url).into(imageView);
        return view;
    }


}
