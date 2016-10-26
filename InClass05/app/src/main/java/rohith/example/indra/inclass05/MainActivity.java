package rohith.example.indra.inclass05;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends Activity {

    String[] Keywords = {"Uncc","Android","Winter","Aurora","Wonders"};
   String[] images = null;
    String[] imagesurl=null;
    int index =0;
    int check =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if(check ==0) {
            findViewById(R.id.imageViewNext).setEnabled(false);
            findViewById(R.id.imageViewPrevious).setEnabled(false);
        }
*/

        findViewById(R.id.imageViewNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(imagesurl.length>=2 ) {
                    index++;
                    index = index % imagesurl.length;
                    new LoadingImages(MainActivity.this).execute(imagesurl[index]);
                }
                else{
                    findViewById(R.id.imageViewNext).setEnabled(false);
                    findViewById(R.id.imageViewPrevious).setEnabled(false);
                }

            }
        });


        findViewById(R.id.imageViewPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagesurl.length>=2 ) {
                    index--;
                    if (index == -1) {

                        index = imagesurl.length - 1;
                    }
                    index = index % imagesurl.length;
                    new LoadingImages(MainActivity.this).execute(imagesurl[index]);
                }

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose a Keywoard");
        builder.setItems(Keywords, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new DoWork().execute(Keywords[which]);



            }
        });
        final AlertDialog alertDialog = builder.create();


        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            findViewById(R.id.buttonGo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });
        }
        else{

            Log.d("demo","Network problem");
        }

    }

    public boolean iSConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;

    }

    public class DoWork extends AsyncTask<String,Void,String> {

        BufferedReader reader = null;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("demo",s);
            images = s.split(";",-1);
            TextView textView = (TextView)findViewById(R.id.textViewSearch);
            textView.setText(images[0]);
             imagesurl = Arrays.copyOfRange(images, 1, images.length);

            for(int  i =0; i<imagesurl.length;i++){
                Log.d("demo",imagesurl[i]);

            }
            index = 0;

            check = 1;
            findViewById(R.id.imageViewNext).setEnabled(true);
            findViewById(R.id.imageViewPrevious).setEnabled(true);

           new LoadingImages(MainActivity.this).execute(imagesurl[index]);



        }

        @Override
        protected String doInBackground(String... params) {
            String link = "http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword=";
            link = link+params[0];
            try {
                URL url = new URL(link);
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line="";
                    while((line= reader.readLine())!=null){
                        sb.append(line).append("\n");
                    }

                    return sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }finally {
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            return null;
        }
    }


}
