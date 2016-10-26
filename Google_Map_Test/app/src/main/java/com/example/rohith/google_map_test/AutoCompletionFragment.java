package com.example.rohith.google_map_test;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AutoCompletionFragment extends Fragment {

    //MainActivity Activity1;
    public AutoCompletionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auto_completion,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText editText= (EditText) getView().findViewById(R.id.editText5);
                editText.setText("");
            }
        });
        getView().findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.mainActivity, ""+getView().toString(), Toast.LENGTH_SHORT).show();
                Log.d("demo", "onClick: "+getView().toString());
                mlistner.sendValues(getView());

            }
        });
        //getView().

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mlistner= (SendValuesBackToActivity) context;
        //Log.d(TAG, "onAttach: ");
    }
    SendValuesBackToActivity mlistner;
    //mlistner=(AutoCompletionFragment)MainActivity.mainActivity;
    interface SendValuesBackToActivity{
       void sendValues(View view);

    }

}
