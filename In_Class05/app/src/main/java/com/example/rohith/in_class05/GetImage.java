package com.example.rohith.in_class05;

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
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.show();
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
        pd.dismiss();
        ImageView iv= (ImageView) mainActivity.findViewById(R.id.imageView);
        iv.setImageBitmap(bitmap);
    }


}