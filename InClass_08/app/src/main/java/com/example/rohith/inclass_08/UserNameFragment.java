package com.example.rohith.inclass_08;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserNameFragment extends Fragment {
    private OnFragement onfragement;

    public interface OnFragement{
        void callNextFragment(int i);
    }
    public UserNameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onfragement = (OnFragement) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText uName= (EditText) getView().findViewById(R.id.editText2);
        final EditText pwd= (EditText) getView().findViewById(R.id.editText);
        Button login= (Button) getView().findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= uName.getText().toString();
                String pwd2=pwd.getText().toString();
                Log.d("", "onClick: "+user+pwd2);
                if(user.equals("admin") && pwd2.equals("test123")){
                    Toast.makeText(MainActivity.mainActivity, "login done", Toast.LENGTH_SHORT).show();
                    onfragement.callNextFragment(1);
                }
                else{
                    onfragement.callNextFragment(0);
                    Toast.makeText(MainActivity.mainActivity, "Wrong details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
