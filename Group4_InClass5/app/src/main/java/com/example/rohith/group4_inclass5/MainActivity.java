package com.example.rohith.group4_inclass5;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final CharSequence[] items = {"UNCC","Android","Winter","Aurora","Wonders"};
    String TAG="demo";
    TextView search;
    static Context myContext;
    //static ArrayList<String> mylist2=new ArrayList<String>();
    ArrayList<String> mylist;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContext=this;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        search=(TextView)findViewById(R.id.search_id);
        builder.setTitle("Choose a Keyword")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.d(TAG, items[item].toString());
                        search.setText(""+items[item].toString());
                        if(iscon()){
                            Toast.makeText(MainActivity.this, "Connected"+items[item].toString().toLowerCase().toString(), Toast.LENGTH_SHORT).show();
                            String link="http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword="+items[item].toString().toLowerCase();
                           //MainActivity.mylist2.clear();
                            new GetData().execute(link);
                            //Log.d(TAG, "**** "+MainActivity.mylist2.size());
                            //String image_url=mylist2.get(1);
                            //new GetImage().execute(image_url);

                        }
                        else{
                            Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        final AlertDialog singleItem=builder.create();
        ((Button) findViewById(R.id.go_id)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                singleItem.show();
            }
        });

    }
    public void setArraylist(ArrayList<String> array_list){

        Log.d(TAG, "setArraylist:---- "+array_list.size());
        for(int i=0;i<array_list.size();i++){
           //MainActivity.mylist2.add(array_list.get(i));
        }
        //Log.d(TAG, "setArraylist: "+mylist2.size());
    }

    public boolean iscon() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null & ni.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
    public class GetData extends AsyncTask<String, Void, ArrayList<String>>{
        BufferedReader br;
         ArrayList<String> mylist=new ArrayList<String>();

        @Override
        protected void onPostExecute(final ArrayList<String> strings) {
            //super.onPostExecute(strings);
            Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
            String st=strings.get(i);
            //MainActivity obj=new MainActivity();
            setArraylist(strings);
            Log.d(TAG, "onPostExecute: ");
            new GetImage().execute(strings.get(i));
            ImageView next= (ImageView) findViewById(R.id.next_id);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i++;
                    if(strings.size()<=i){
                        new GetImage().execute(strings.get(i));
                    }
                }
            });

        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            try {
                Log.d(TAG, "doInBackground: ");
                URL url=new URL(params[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStream tp=con.getInputStream();
                br=new BufferedReader(new InputStreamReader(tp));
                StringBuilder sb=new StringBuilder();
                String line="";

                while((line=br.readLine())!=null) {

                    sb.append(line+"\n");
                    line=br.readLine();

                }
                String mystring=sb.toString();
                Log.d(TAG, "mystr "+mystring);
                if(mystring!=null) {
                    String[] split = mystring.split(";");
                    String keyword = split[0];
                    mylist.add(keyword);
                    int length = split.length;
                    int i = 1;

                    if (i <= length) {
                        for (int j = 1; j <length; j++) {
                            Log.d(TAG, "links"+split[j]);
                            String s=split[j];
                            mylist.add(s);

                        }
                    }
                }
                return mylist;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(br!=null)
                        br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
    class GetImage extends AsyncTask<String, Void, Bitmap> {
        BufferedReader br;


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
            ImageView iv= (ImageView) findViewById(R.id.imageView);
            iv.setImageBitmap(bitmap);
        }


}

}

