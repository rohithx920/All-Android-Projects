package com.example.rohith.midterm;

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
 * Created by Rohith on 6/9/2016.
 */
class GetImage extends AsyncTask<String, Void, Bitmap> {
    BufferedReader br;
    Summary summary;
    ProgressDialog pd;
    public GetImage(Summary summary ){
        this.summary=summary;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(summary);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        //myinterface.getString("hello");
        pd.dismiss();
        ImageView iv= (ImageView) summary.findViewById(R.id.imageView);
        iv.setImageBitmap(bitmap);

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
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

}