package rohith.example.indra.inclass05;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by indra on 6/7/16.
 */
public class LoadingImages extends AsyncTask<String,Void,Bitmap> {

    ProgressDialog progressDialog;
    MainActivity activity;
    LoadingImages(MainActivity activity){

        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {
         progressDialog= new ProgressDialog(activity);
        progressDialog.setTitle("Loading Image");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
                Log.d("demo","image requested");

                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        if(bitmap!=null)
        {
            Log.d("demo","image shown");
            progressDialog.dismiss();
            ImageView imageView = (ImageView)activity.findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
            Log.d("demo",bitmap.toString());

        }
    }


}
