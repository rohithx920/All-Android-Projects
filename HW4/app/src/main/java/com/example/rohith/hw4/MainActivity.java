package com.example.rohith.hw4;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements GetData.SendingValuesBack {

    static MediaPlayer mediaPlayer ;
    ImageView buttonPlayPause;
    int length;

    private RecyclerView mRecyclerView;
    //private MyAdapter mAdapter;
    private Ted_Adapter ted_adapter;
    private Ted_Grid_adapter ted_Grid_adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context mainactivity;
    ArrayList<Ted> mylist;
    ProgressBar pbar;
    Timer timer;
    String prev_url;
    boolean change=true;
    boolean new_one=false;
    Handler handler = new Handler();
    boolean pause=false;


    @Override
    public void sendValuesToMain(ArrayList<Ted> ted_list) {
        mylist=new ArrayList<Ted>();
        mylist=ted_list;
        //pbar= (ProgressBar) findViewById(R.id.progressBar2);
        //buttonPlayPause = (ImageView)findViewById(R.id.imageView3);

        Log.d("hhhhhd",mylist.get(0).toString());
        main_method();
            }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("TED Radio Hour Podcast");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mediaPlayer = new MediaPlayer();

         pbar =(ProgressBar) findViewById(R.id.progressBar2);
         pbar.setVisibility(View.GONE);
        buttonPlayPause = (ImageView)findViewById(R.id.imageView3);
         buttonPlayPause.setVisibility(View.GONE);
        //setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvContacts);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mainactivity=this;

        new GetData(this).execute("http://www.npr.org/rss/podcast.php?id=510298");

        if(iscon()){
            Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
            new GetData(this).execute("http://www.npr.org/rss/podcast.php?id=510298");
        }
        else{
            Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
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
    @Override
    public void onBackPressed ()
    {
        if (mediaPlayer != null)
            mediaPlayer.stop();
        super.onBackPressed();
        if(timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_refresh:
                change=!change;
                if(change==true){
                   //main_method();
                    Log.d("hhhhhd",mylist.get(0).toString());
                    mRecyclerView = (RecyclerView) findViewById(R.id.rvContacts);
                    mLayoutManager = new LinearLayoutManager(this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setHasFixedSize(true);
                    if (mediaPlayer != null)
                        mediaPlayer.stop();
                    //super.onBackPressed();
                    if(timer != null) {
                        timer.cancel();
                        timer.purge();
                        timer = null;
                    }
                    pbar.setProgress(0);
                    pbar.setVisibility(View.GONE);

                    buttonPlayPause.setVisibility(View.GONE);
                    main_method();

                }
                else{
                    if (mediaPlayer != null)
                        mediaPlayer.stop();
                    //super.onBackPressed();
                    if(timer != null) {
                        timer.cancel();
                        timer.purge();
                        timer = null;
                    }

                    mRecyclerView = (RecyclerView) findViewById(R.id.rvContacts);
                    mLayoutManager = new GridLayoutManager(this,2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setHasFixedSize(true);
                   // ted_adapter=new Ted_Adapter(MainActivity.this,mylist);
                    pbar.setProgress(0);
                    pbar.setVisibility(View.GONE);
                    buttonPlayPause.setVisibility(View.GONE);
                    main_method_Grid_view();

                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setAdapter(ted_Grid_adapter);
                    ted_adapter.notifyDataSetChanged();

                }

                break;
        }
        return super.onOptionsItemSelected(item);

    }
        private void ProgressUpdater() {
            //progressBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/length)*100));
            // This math construction give a percentage of "was playing"/"song length"
            if (mediaPlayer.isPlaying()) {
                Runnable notification = new Runnable() {
                    public void run() {
                        ProgressUpdater();
                    }
                };
                handler.postDelayed(notification,1000);
            }

        }
    public void main_method(){

            ted_adapter=new Ted_Adapter(mylist, new

                Ted_Adapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(Ted item) {
                        Log.d("demo", item.getDescription());
                        pbar.setVisibility(View.VISIBLE);
                        buttonPlayPause.setVisibility(View.VISIBLE);
                        buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                        int delay = 1000; // delay for 1 sec.
                        int period = 10000;
                        timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask()
                        {
                            public void run()
                            {
                                if(mediaPlayer!=null) {

                                    //seekbar.setProgress(mediaPlayer.getCurrentPosition());  // display the data
                                    pbar.setProgress(mediaPlayer.getCurrentPosition());  // display the data

                                    Log.d("888", "run: " + mediaPlayer.getCurrentPosition());
                                }
                                else{
                                    Log.d("dd", "run:not set ");
                                }
                            }
                        }, delay, period);


                        String url = item.getUrl().toString();
                        if(mediaPlayer!=null) {
                            mediaPlayer.stop();
                            mediaPlayer = null;
                        }
                        mediaPlayer = new MediaPlayer();

                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            mediaPlayer.prepare(); // might take long! (for buffering, etc)
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        //seekbar.setMax(mediaPlayer.getDuration());
                        pbar.setMax(mediaPlayer.getDuration());

                    }

                });

        mRecyclerView.setAdapter(ted_adapter);
        ted_adapter.notifyDataSetChanged();

        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (pause == false) {
                        pause = true;
                        mediaPlayer.pause();
                        Log.d("5888", "onClick: " + mediaPlayer.getCurrentPosition());
                        buttonPlayPause.setImageResource(android.R.drawable.ic_media_play);
                        pbar.setProgress(mediaPlayer.getCurrentPosition());
                    } else {
                        mediaPlayer.start();
                        buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                        pause = false;
                    }
                }
            }
        });



    }

    public void main_method_Grid_view(){

        ted_Grid_adapter=new Ted_Grid_adapter(mylist, new
                Ted_Grid_adapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(Ted item) {
                        Log.d("demo", item.getDescription());
                        pbar.setVisibility(View.VISIBLE);
                        buttonPlayPause.setVisibility(View.VISIBLE);
                        buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                        int delay = 1000; // delay for 1 sec.
                        int period = 10000;
                        timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask()
                        {
                            public void run()
                            {
                                if(mediaPlayer!=null) {

                                    //seekbar.setProgress(mediaPlayer.getCurrentPosition());  // display the data
                                    pbar.setProgress(mediaPlayer.getCurrentPosition());  // display the data
                                    Log.d("888", "run: " + mediaPlayer.getCurrentPosition());
                                }
                                else{
                                    Log.d("dd", "run:not set ");
                                }
                            }
                        }, delay, period);


                        String url = item.getUrl().toString();
                        if(mediaPlayer!=null) {
                            mediaPlayer.stop();
                            mediaPlayer = null;
                        }
                        mediaPlayer = new MediaPlayer();

                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            mediaPlayer.prepare(); // might take long! (for buffering, etc)
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        //seekbar.setMax(mediaPlayer.getDuration());
                        pbar.setMax(mediaPlayer.getDuration());

                    }

                });

        mRecyclerView.setAdapter(ted_Grid_adapter);
        ted_Grid_adapter.notifyDataSetChanged();

        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (pause == false) {
                        pause = true;
                        mediaPlayer.pause();
                        Log.d("5888", "onClick: " + mediaPlayer.getCurrentPosition());
                        buttonPlayPause.setImageResource(android.R.drawable.ic_media_play);
                        pbar.setProgress(mediaPlayer.getCurrentPosition());
                    } else {
                        mediaPlayer.start();
                        buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                        pause = false;
                    }
                }
            }
        });


    }



}
