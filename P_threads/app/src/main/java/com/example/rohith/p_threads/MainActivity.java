package com.example.rohith.p_threads;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressDialog pd;
    static Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //progressBar= (ProgressBar) findViewById(R.id.progressBar2);
        //progressBar.setMax(1000);
        pd=new ProgressDialog(this);
        pd.setTitle("loading");
        pd.setMessage("progress amount");
        pd.setMax(100);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        h = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case Dowork.S_START:
                        pd.show();
                        break;
                    case Dowork.S_STEP:
                        //Log.d("de", "handleMessage: "+msg.obj);
                        pd.setProgress((Integer) msg.getData().getInt("progress"));
                        break;
                    case Dowork.S_STOP:
                        pd.dismiss();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        ExecutorService threadpool=Executors.newFixedThreadPool(1);
        threadpool.execute(new Dowork());
        }


        /*progressBar= (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setMax(1000);
        pd=new ProgressDialog(this);
        pd.setTitle("loading");
        //Thread thread=new Thread(new Dowork());
        //thread.start();
        ExecutorService es= Executors.newFixedThreadPool(5);
        es.execute(new Dowork(1,2));
       // h=new Handler(); */








}
class Dowork implements Runnable{
    //int id,name;
    static final int S_START=00;
    static final int S_STEP=01;
    static final int S_STOP=10;


    @Override
    public void run() {
        Message msg=new Message();
        msg.what=S_START;
        MainActivity.h.sendMessage(msg);

        //pd.show();
        for(int i=1;i<=100;i++){
            for(int j=1;j<=1000000000;j++){

            }
            msg=new Message();
            msg.what=S_STEP;
            //msg.obj=i;
            Bundle data=new Bundle();
            data.putInt("progress",i);
            msg.setData(data);

            MainActivity.h.sendMessage(msg);
        }
        msg=new Message();
        msg.what=S_STOP;
        MainActivity.h.sendMessage(msg);

    }

}






