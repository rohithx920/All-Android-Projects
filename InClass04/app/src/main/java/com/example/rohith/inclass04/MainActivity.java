package com.example.rohith.inclass04;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity   {

    //private View Spinner;
    int position;
    Handler handler;
    Spinner spinner;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Generating Passwords...");
        //Spinner sp=(Spinner)findViewById(R.id.spinner);
         //Spinner spinner = (Spinner) findViewById(R.id.spinner_id);
        final CheckBox cb= (CheckBox) findViewById(R.id.Numbers_id);
        final CheckBox cb1= (CheckBox) findViewById(R.id.Uppercase_id);
        final CheckBox cb2= (CheckBox) findViewById(R.id.Lowercase_id);
        final CheckBox cb3= (CheckBox) findViewById(R.id.Special_char_id);
        Button asyn=(Button)findViewById(R.id.Async_id);
        Button threads=(Button)findViewById(R.id.Thread_button);

        //new getPswd().execute();
        spinner = (Spinner) findViewById(R.id.spinner_id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.spinnerItems, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        spinner.setAdapter(adapter);
        // position=spinner.getSelectedItemPosition();
        asyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               position=spinner.getSelectedItemPosition();
                Log.d("demo",+position+"");

                        //position=position-1;
                        if(position == 0){
                            Toast.makeText(MainActivity.this, "Select pswd length", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            new getPswd().execute(position-1);
                        }







            }
        });
        Button thread=(Button)findViewById(R.id.Thread_button);
        thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position=spinner.getSelectedItemPosition();
                 Log.d("demo","num"+position);
                if(position>0) {
                    Thread thread1 = new Thread(new Worker());
                    thread1.start();
                }
                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {

                        switch(msg.what){
                            case Worker.STATUS_START:
                                progressDialog.show();
                                break;
                            case Worker.STATUS_STEP:
                            case Worker.STATUS_DONE:
                                if(msg.getData().containsKey("result")){
                                    TextView tv=(TextView)findViewById(R.id.Result_id);

                                    tv.setText(msg.getData().getString("result"));
                                }
                                String pwd = (String)msg.obj;
                                progressDialog.dismiss();
                        }



                        return false;
                    }
                });

            }
        });
    }


    public boolean checkbox(){

        final CheckBox cb= (CheckBox) findViewById(R.id.Numbers_id);
        int a;
        if(cb.isChecked()){

            return true;
        }
        else{
           return false;
        }
    }
    public boolean checkbox1(){

        final CheckBox cb1= (CheckBox) findViewById(R.id.Uppercase_id);
        int a;
        if(cb1.isChecked()){

            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkbox2(){

        final CheckBox cb2= (CheckBox) findViewById(R.id.Lowercase_id);
        int a;
        if(cb2.isChecked()){

            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkbox3(){

        final CheckBox cb3= (CheckBox) findViewById(R.id.Special_char_id);
        int a;
        if(cb3.isChecked()){

            return true;
        }
        else{
            return false;
        }
    }
    public class getPswd extends AsyncTask<Integer,Integer,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
            progressDialog.setMessage("Generating Passwords...");
            //textView = (TextView)findViewById(R.id.password);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView tv=(TextView)findViewById(R.id.Result_id);
            tv.setText(s);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... params) {
            Util util=new Util();
            Log.d("demo", "----"+params[0]);
            String result=util.getPassword(params[0],checkbox(),checkbox1(),checkbox2(),checkbox3());
            return result;

        }
    }

    class Worker implements Runnable{
        static  final int STATUS_START = 0x00;
        static  final int STATUS_STEP = 0x01;
        static  final int STATUS_DONE = 0x02;

        @Override
        public void run() {
            Message message = new Message();
            message.what = STATUS_START;
            handler.sendMessage(message);

            Log.d("demo","num2  "+ position);
            String s = Util.getPassword(position-1,checkbox(),checkbox1(),checkbox2(),checkbox3());
            Message m = new Message();
            m.what = STATUS_DONE;
            Bundle bundle = new Bundle();
            bundle.putString("result",s);
            m.setData(bundle);
            handler.sendMessage(m);

        }
    }


}
