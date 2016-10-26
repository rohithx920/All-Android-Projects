package com.example.rohith.in_class_05;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rohith on 6/7/2016.
 */
class GetImage extends AsyncTask<String, Void, Bitmap> {
    BufferedReader br;
    MainActivity mainActivity;
    ProgressDialog pd;
    public GetImage(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(mainActivity);

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            String myvalues=params[0];
            //Log.d("Hello",""+params[1]);
            URL url=new URL(myvalues);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Bitmap image= BitmapFactory.decodeStream(con.getInputStream());
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

        }


        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        //MainActivity.myContext;
        ImageView imageView= (ImageView) mainActivity.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }


}