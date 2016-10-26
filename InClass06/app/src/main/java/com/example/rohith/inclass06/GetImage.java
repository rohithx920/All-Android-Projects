package com.example.rohith.inclass06;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rohith on 6/14/2016.
 */
public class GetImage extends AsyncTask<String,Void,RequestCreator>{
    MainActivity mainActivity;
    public GetImage(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    protected RequestCreator doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream tp=con.getInputStream();
            Picasso picasso=new Picasso.Builder(mainActivity).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    //generate exception
                    Log.d("demo", "onImageLoadFailed: "+exception);
                }
            }).build();
            RequestCreator image = picasso.load(String.valueOf(url));
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(RequestCreator rq) {
        super.onPostExecute(rq);
        ImageView iv= (ImageView) mainActivity.findViewById(R.id.imageView);
        rq.into(iv);
    }
}
