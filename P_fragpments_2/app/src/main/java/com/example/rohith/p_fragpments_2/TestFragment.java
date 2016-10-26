package com.example.rohith.p_fragpments_2;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {
    EditText et;

    public TestFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    SendValuesBack mlistner;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mlistner= (SendValuesBack) activity;
        }
        catch (Exception e){
            Log.d("exc", "onAttach: "+e);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //use getView() instead of getActivity() if we use same fragment multiple times
         et= (EditText) getView().findViewById(R.id.editText);
        final Button bt= (Button) getView().findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistner.getValues(et.getText().toString());
            }
        });

    }
    public void sendingFromActivityToFragment(String str){
        et.setText(str);
    }

    public interface SendValuesBack{
        void getValues(String string);
    }
}
