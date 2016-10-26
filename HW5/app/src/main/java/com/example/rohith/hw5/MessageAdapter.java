package com.example.rohith.hw5;

import android.content.Context;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rohith on 6/28/2016.
 */
public class MessageAdapter extends ArrayAdapter<Message> {
    Context mcontext;
    int mresource;
    List<Message> movieList;

    public MessageAdapter(Context context, int resource, List<Message> objects) {
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
        final TextView f_l_name = (TextView) convertView.findViewById(R.id.f_l_name_id);
        final TextView time = (TextView) convertView.findViewById(R.id.t_id);
        final TextView first_line_of_message = (TextView) convertView.findViewById(R.id.msg_f_line_id);

        /*convertView.findViewById(R.id.r_or_not_id).setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Toast.makeText(mcontext, "edit is clicked"+position, Toast.LENGTH_SHORT).show();
        movie_name.setText("laagan");
        }
        });
        }*/
        int size=movieList.size();
        Message m=movieList.get(position);

        f_l_name.setText(m.getReceiver());
        //time.setText();
        first_line_of_message.setText(m.getMessage());


        return convertView;
    }
}

