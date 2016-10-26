package com.example.rohith.googlemapslistview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Rohith on 6/28/2016.
 */
public class MapAdapter extends ArrayAdapter<String> {
    Context mcontext;
    int mresource;
    List<String> maplist;
    private Interface inter;
    interface Interface{
        void sendPostion(int postion);
    }
    public MapAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
        maplist = objects;
        this.inter= (Interface) context;
        //inter.aMethod();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mresource, parent, false);
        }
        final AutoCompleteTextView autoCompView = (AutoCompleteTextView) convertView.findViewById(R.id.autoCompleteTextView);
        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(mcontext, R.layout.list_item));
        autoCompView.setText(maplist.get(position));
        autoCompView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String) adapterView.getItemAtPosition(i);
                autoCompView.setText(str);
                Log.d("555", ""+str);
                String modified_string=convertTextToGoogleURLFORM(str);
                maplist.set(position,modified_string);
                MainActivity.locations.set(position,modified_string);
                Toast.makeText(mcontext, str, Toast.LENGTH_SHORT).show();
            }
        });

        //editText.setText(maplist.get(position));
        convertView.findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCompView.setText("");
            }
        });
        convertView.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //maplist.remove(position);
                inter.sendPostion(position);
                Toast.makeText(MainActivity.mainActivity, "removing"+position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;

    }
    public String convertTextToGoogleURLFORM(String string){
        Log.d("55567", ""+string.replaceAll(",","+").replaceAll("\\s",""));
        return string.replaceAll(",","+").replaceAll("\\s","");
    }


}

