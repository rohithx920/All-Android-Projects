package com.example.rohith.hw4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Ted_Adapter extends RecyclerView.Adapter<Ted_Adapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Ted item);
    }

    private static  ArrayList<Ted> items = null;
    private final OnItemClickListener listener;

    public Ted_Adapter(ArrayList<Ted> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ted, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private TextView name;
        private ImageView imageView;
        private ImageView image;
        private TextView pubdate;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            imageView =(ImageView) itemView.findViewById(R.id.imageView2);
            pubdate=(TextView) itemView.findViewById(R.id.textView2);
        }

        public void bind(final Ted item, final OnItemClickListener listener) {
            name.setText(item.getTitle());
            pubdate.setText(item.getPubdate());
            itemView.setOnClickListener(this);
            Picasso.with(itemView.getContext()).load(item.getHref()).into(image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

        @Override
        public void onClick(View v) {
            MainActivity.mediaPlayer.stop();
            MainActivity.mediaPlayer=null;
            Intent intent=new Intent(v.getContext(),PlayActivity.class);
            Log.d("chekk", "onClick: "+Ted_Adapter.items.get(getPosition()));
            intent.putExtra("key",Ted_Adapter.items.get(getPosition()));
            v.getContext().startActivity(intent);
        }
    }
}