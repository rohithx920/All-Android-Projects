package com.example.rohith.hw4;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Rohith on 6/21/2016.
 */
public class Vh extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView Episode_title;
    public TextView Posted_date;
    public TextView Play_now;
    public ImageView mImageView;
    public ImageView mImageView2;
    public Vh(View itemView) {
        super(itemView);
        this.mImageView= (ImageView) itemView.findViewById(R.id.imageView);
        this.mImageView2= (ImageView) itemView.findViewById(R.id.imageView2);
        this.Episode_title= (TextView) itemView.findViewById(R.id.textView);
        this.Posted_date= (TextView) itemView.findViewById(R.id.textView2);
        this.Play_now= (TextView) itemView.findViewById(R.id.textView3);
        this.mImageView2.setOnClickListener(this);

    }

    public void bind(final Ted item, final AdapterView.OnItemClickListener listener) {
        Play_now.setText(item.getTitle());
        Picasso.with(itemView.getContext()).load(item.getHref()).into(mImageView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(v.getContext(),PlayActivity.class);
      // intent.putExtra("key",Ted_Adapter.tedArrayList.get(getPosition()));
        v.getContext().startActivity(intent);
    }
}
