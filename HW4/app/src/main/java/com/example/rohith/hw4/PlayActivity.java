package com.example.rohith.hw4;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.TimedMetaData;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class PlayActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    //SeekBar seekbar;
    ProgressBar pbar;
    Timer timer;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    //Handler mHandler = new Handler();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        final Ted value = (Ted) getIntent().getExtras().get("key");
        TextView episode= (TextView) findViewById(R.id.Episode_id);
        TextView des= (TextView) findViewById(R.id.Description_id);
        TextView pub_date= (TextView) findViewById(R.id.Pub_date);
        TextView duration= (TextView) findViewById(R.id.Duration_id);
        ImageView image= (ImageView) findViewById(R.id.Ted_image_id);
        final ImageView play_but= (ImageView) findViewById(R.id.Play_id);
        pbar= (ProgressBar) findViewById(R.id.progressBar);
        //seekbar= (SeekBar) findViewById(R.id.seekBar);
        episode.setText(value.getTitle());
        des.setText(value.getDescription());
        pub_date.setText(value.getPubdate());
        duration.setText(value.getDuration());
        Picasso.with(this).load(value.getHref()).into(image);

        play_but.setOnClickListener(new View.OnClickListener() {
            boolean pause=false;
            @Override
            public void onClick(View v) {

                String url = value.getUrl().toString();
                // your URL here

                if (mediaPlayer != null) {
                    if(pause==false){
                        pause=true;
                        mediaPlayer.pause();
                        Log.d("5555", "onClick: "+mediaPlayer.getCurrentPosition());
                        play_but.setImageResource(android.R.drawable.ic_media_play);
                        pbar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                    else{
                        mediaPlayer.start();
                        play_but.setImageResource(android.R.drawable.ic_media_pause);
                        pause=false;
                    }
                } else {
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
                    play_but.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });
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


/*        mediaPlayer.setOnTimedMetaDataAvailableListener(new MediaPlayer.OnTimedMetaDataAvailableListener() {
            @Override
            public void onTimedMetaDataAvailable(MediaPlayer mp, TimedMetaData data) {

            }
        });*/
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

}
